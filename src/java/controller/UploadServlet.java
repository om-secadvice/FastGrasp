/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Course;
import entity.CourseContent;
import entity.Faculty;
import entity.Student;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import session.CourseContentFacade;
import session.FacultyFacade;
import session.StudentFacade;

@MultipartConfig(maxFileSize = 16177215) //16MB
@WebServlet(
        name = "UploadServlet",
        urlPatterns = {
            "/upload/profile",
            "/upload/file",
            "/resources"
        }
)

public class UploadServlet extends HttpServlet {

    @EJB
    StudentFacade studentFacade;

    @EJB
    FacultyFacade facultyFacade;

    @EJB
    private CourseContentFacade courseContentFacade;


@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getServletPath().equals("/resources")){
            CourseContent courseContent=courseContentFacade.find(Integer.parseInt(request.getParameter("courseContent")));
            response.setContentType(courseContent.getType());
            String url="/WEB-INF/resources"+courseContent.getLink();
            System.out.println("#######################################"+url);
            response.setHeader("Content-disposition", "attachment; filename="+courseContent.getTitle().replace(' ', '_'));
            request.getRequestDispatcher(url).forward(request, response);
            
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userPath = request.getServletPath();
        HttpSession session = request.getSession();

        if (userPath.equals("/upload/profile")) {
            Part profile = request.getPart("profilepic");
            System.out.println("MIME Type:" + profile.getContentType());
            System.out.println("File name:" + profile.getName());
            System.out.println("File Size:" + profile.getSize());
            if (session.getAttribute("currentUser") instanceof Student) {
                Student student = (Student) session.getAttribute("currentUser");
                try {
                    String pic = "data:" + profile.getContentType() + ";base64," + Base64.getEncoder().encodeToString(getByteArray(profile.getInputStream()));
                    student.setProfilePic(pic);
                    System.out.println("IMAGE:---" + pic);
                    studentFacade.edit(student);
                    session.setAttribute("currentUser", student);

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    response.sendRedirect("../student/profile");
                }

            } else if (session.getAttribute("currentUser") instanceof Faculty) {
                Faculty faculty = (Faculty) session.getAttribute("currentUser");
                try {
                    String pic = "data:" + profile.getContentType() + ";base64," + Base64.getEncoder().encodeToString(getByteArray(profile.getInputStream()));
                    faculty.setProfilePic(pic);
                    facultyFacade.edit(faculty);
                    session.setAttribute("currentUser", faculty);
                } catch (Exception e) {
                } finally {
                    response.sendRedirect("../faculty/profile");
                }

            }
        } else if (userPath.equals("/upload/file")) {

            CourseContent courseContent = new CourseContent(0);
            String fileType = request.getParameter("fileType");
            String contentTitle = request.getParameter("title");
            Course course = (Course) session.getAttribute("selectedCourse");

            if (fileType.equals("video")) {
                String uploadLink = request.getParameter("video");
                if (uploadLink.contains("youtube.com")) {
                    String videoId[] = uploadLink.split("[=]");

                    courseContent.setTitle(contentTitle);
                    courseContent.setType(fileType);
                    courseContent.setLink(videoId[1]);
                    courseContent.setCourseId(course);
                    courseContent.setDateCreated(new Date(System.currentTimeMillis()));

                    courseContentFacade.create(courseContent);
                    
                } else {
                    System.out.println("Upload youtube video only");
                }
            } else if (fileType.equals("PDF") || fileType.equals("msword") || fileType.equals("text")) {

                // Create path components to save the file
                Part filePart = request.getPart("file");

                String directoryName = "course" + course.getId();
                String directoryPath = getServletConfig().getServletContext().getRealPath(
                        getServletConfig().getServletContext().getInitParameter("resourcePath")) 
                        + File.separator + directoryName + File.separator;
                String relativeDirectoryPath = "/" + directoryName + "/";;
//                String relativeDirectoryPath = getServletConfig().getServletContext().getInitParameter("resourcePath") 
//                        + File.separator + directoryName + File.separator;;
               
                System.out.println(directoryPath);

                String fileName = System.currentTimeMillis() + "_" + filePart.getSubmittedFileName();
                String filePath = directoryPath + fileName;
                
                String relativeFilePath = relativeDirectoryPath + fileName; 
                System.out.println(filePath);

                InputStream fileContent = null;
                File file;
                FileOutputStream fout = null;

                try {

                    file = new File(directoryPath);
                    file.mkdir();
                    
                    
                    file = new File(filePath);

                    fout = new FileOutputStream(file);
                    fileContent = filePart.getInputStream();
                    
                    file.createNewFile();
                    fout.write(getByteArray(fileContent));
                    
                    fout.flush();   
                    
                    courseContent.setTitle(contentTitle);
                    courseContent.setType(filePart.getContentType());
                    courseContent.setLink(relativeFilePath);
                    courseContent.setCourseId(course);
                    courseContent.setDateCreated(new Date(System.currentTimeMillis()));
                    try{
                    courseContentFacade.create(courseContent);
                    }catch(EJBException ejb){
                        session.setAttribute("alert", "File Size Too Big! Upload Failed...");
                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
  

                } finally {
                    if (fout != null) {
                        fout.close();
                    }
                    if (fileContent != null) {
                        fileContent.close();
                    }
                    
                }
               
            }
            response.sendRedirect("../faculty/courseLecture");
        }

    }

    public byte[] getByteArray(InputStream input) throws IOException {
        byte[] buffer = new byte[8192];
        int bytesRead;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        while ((bytesRead = input.read(buffer)) != -1) {
            output.write(buffer, 0, bytesRead);
        }
        return output.toByteArray();
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
