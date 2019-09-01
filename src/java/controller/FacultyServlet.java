/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Announcement;
import entity.Course;
import entity.CourseContent;
import entity.CourseFaculty;
import entity.Faculty;
import entity.Test;
import entity.TestQuestion;
import session.SigninManager;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.AnnouncementFacade;
import session.CourseCategoryFacade;
import session.CourseContentFacade;
import session.CourseFacade;
import session.CourseFacultyFacade;
import session.SignupManager;
import session.TestFacade;
import session.TestQuestionFacade;
import session.CourseManager;

/**
 *
 * @author HARIOM
 */
@WebServlet(name = "FacultyController",
        loadOnStartup = 2,
        urlPatterns = {
            "/faculty/addQuestion",
            "/faculty/courseLecture",
            "/faculty/createAnnouncement",
            "/faculty/createTest",
            "/faculty/dashboard",
            "/faculty/initiateCourse",
            "/faculty/resolveIssue",
            "/faculty/uploadContent",
            "/faculty/viewFeedback",
            "/faculty/login",
            "/faculty/profile",
            "/faculty/register",
            "/faculty/explore",
            "/faculty/explore/courseDetails"})
public class FacultyServlet extends HttpServlet {

    @EJB
    private CourseManager courseManager;
    
    @EJB
    private SignupManager signupManager;

    @EJB
    private SigninManager signinManager;

    @EJB
    private CourseFacultyFacade courseFacultyFacade;
    
    @EJB
    private CourseContentFacade courseContentFacade;
    
    @EJB
    private CourseFacade courseFacade;

    
    @EJB
    private TestQuestionFacade testQuestionFacade;
    
    @EJB
    private AnnouncementFacade announcementFacade;
     @EJB
    private CourseCategoryFacade categoryFacade;
    @EJB
    private TestFacade testFacade;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userPath = request.getServletPath();

        HttpSession session = request.getSession();
        Course selectedCourse;
        
        
        if (userPath.equals("/faculty/courseLecture")) {
            String videoId=request.getParameter("videoId");
            selectedCourse=(Course)session.getAttribute("selectedCourse");
            Collection<CourseContent> courseContents=selectedCourse.getCourseContentCollection();
            System.out.println("#######################################\n");
            System.out.println("Size="+courseContents.size());
            session.setAttribute("courseContents",courseContents);
            if(courseContents==null)System.out.println("KKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK");
            if(videoId==null){
                session.setAttribute("courseContentsIterator", courseContents.iterator());
            }
            else{
                
                request.setAttribute("selectedCourseContent", courseContentFacade.find(Integer.parseInt(videoId)));
                
            }
            userPath = "/faculty/courseLecture";
        } else if (userPath.equals("/faculty/explore")) {
            String selectedCategory=request.getQueryString();
            
            if(selectedCategory!=null)
            session.setAttribute("selectedCategory", categoryFacade.find((Integer.parseInt(selectedCategory))));
            userPath = "/student/explore";
        } 
        
        
        
        
        else if (userPath.equals("/faculty/createAnnouncement")) {
            String courseId = request.getParameter("courseId");
            if (courseId != null) {
                session.setAttribute("selectedCourse", (Course) courseFacade.find(Integer.parseInt(courseId)));
            }  
                    selectedCourse = (Course) session.getAttribute("selectedCourse");
                    System.out.println("");
                    try{
                    Collection<Announcement> courseAnnouncements = courseManager.getAnnouncements(selectedCourse);//selectedCourse.getAnnouncementCollection();
                        System.out.println("######################"+selectedCourse+"#########################CourseAnnSize:"+courseAnnouncements.size());
                        session.setAttribute("courseAnnouncements", courseAnnouncements);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            userPath = "/faculty/createAnnouncement";


        } 
        
        
        
        
        
        
        else if (userPath.equals("/faculty/createTest")) {
            String newTest=request.getParameter("doneAddingData");
            if(newTest!=null && newTest.equalsIgnoreCase("true")){
                session.removeAttribute("newTest");
            }
            userPath = "/faculty/createTest";
        } else if (userPath.equals("/faculty/dashboard")) {
            String courseId=request.getParameter("selected");
            if(courseId!=null){
                selectedCourse=courseFacade.find(Integer.parseInt(courseId));
                session.setAttribute("selectedCourse", selectedCourse);
            }
            else{
                session.removeAttribute("selectedCourse");
            }
            
            
            userPath = "/faculty/dashboard";
        } else if (userPath.equals("/faculty/initiateCourse")) {
            userPath = "/faculty/initiateCourse";
        } else if (userPath.equals("/faculty/resolveIssue")) {
            userPath = "/faculty/resolveIssue";
        } else if (userPath.equals("/faculty/uploadContent")) {
            userPath = "/faculty/uploadContent";
        } else if (userPath.equals("/faculty/viewFeedback")) {
            userPath = "/faculty/viewFeedback";
        } else if (userPath.equals("/faculty/login")) {
            //TODO business logic
            session.setAttribute("userType", "faculty");
            userPath = "/student/login";
        } else if (userPath.equals("/faculty/register")) {
            //TODO business logic
            session.setAttribute("userType", "faculty");
            userPath = "/student/register";
        }
        else if (userPath.equals("/faculty/profile")) {
            
            userPath = "/student/profile";
        } else if (userPath.equals("/faculty/explore/courseDetails")) {

            userPath = "/student/courseDetails";
        }
        

        String url = "/WEB-INF/view" + userPath + ".jsp";
        
        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //POST methods for fastgrasp
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String userPath = request.getServletPath();
        HttpSession session = request.getSession();

        String name, password, contact, email, state, aboutMe, institution, designation;
        Collection<CourseFaculty> facultyCourses;
        Collection<Course> myCourses = new LinkedList();

        if (userPath.equals("/faculty/login")) {
            name = request.getParameter("username");
            password = request.getParameter("password");
            institution = request.getParameter("institution");
            contact = request.getParameter("contact");
            email = request.getParameter("email");
            designation = request.getParameter("designation");
            state = request.getParameter("state");
            aboutMe = request.getParameter("aboutMe");

            if (signupManager.signupFaculty(name, password, designation, institution, contact, email, state, aboutMe)) {
                userPath = "/student/login";
            } else {
                userPath = "/faculty/register";
            }
        } else if (userPath.equals("/faculty/dashboard")) {
            email = request.getParameter("email");
            password = request.getParameter("password");

            Faculty faculty = signinManager.signinFaculty(email, password);
            if (faculty != null) {
                session.setAttribute("currentUser", faculty);

                facultyCourses = faculty.getCourseFacultyCollection();
                Iterator<CourseFaculty> courseFaculty = facultyCourses.iterator();
                while (courseFaculty.hasNext()) {
                    CourseFaculty cf = courseFaculty.next();
                    if (cf.getApproval()) {
                        myCourses.add(cf.getCourse());
                    }
                }

                session.setAttribute("myCourses", myCourses);

                userPath = "/faculty/dashboard";
            }else {

                response.sendRedirect("login");
                return;
            }
        }else if (userPath.equals("/faculty/createAnnouncement")) {
            try {
                Course course = (Course) session.getAttribute("selectedCourse");
                String title = request.getParameter("title");
                String content = request.getParameter("content");
                
                
                course=courseManager.addAnnouncement(course,title,content);
                session.setAttribute("selectedCourse", course);
                System.out.println("Size$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ "+courseManager.getAnnouncements(course).size());
                session.setAttribute("courseAnnouncements", courseManager.getAnnouncements(course));
               // session.setAttribute("courseAnnouncements", courseAnnouncements);

            } catch (Exception e) {
                e.printStackTrace();
            }

            userPath = "/faculty/createAnnouncement";

        } else if (userPath.equals("/faculty/createTest")) {
            try {
                String title = request.getParameter("title");
                int fullMarks = Integer.parseInt(request.getParameter("fullMarks"));
                String dueDateParam = request.getParameter("dueDate");
                java.util.Date dueDate = new java.util.Date(dueDateParam);
                int noOfQuestion = Integer.parseInt(request.getParameter("noOfQuestions"));

                Course course = (Course) session.getAttribute("selectedCourse");
                Test newTest = new Test(0, title,  fullMarks,dueDate, noOfQuestion);
                newTest.setCourseId(course);
                testFacade.create(newTest);
                session.setAttribute("newTest", newTest);

            } catch (Exception e) {
                e.printStackTrace();
            }
            userPath = "/faculty/createTest";

        } else if (userPath.equals("/faculty/addQuestion")) {
            Test test = (Test) session.getAttribute("newTest");
            if (test != null) {
                try {
                    String question = request.getParameter("question");
                    String choiceOne = request.getParameter("choiceOne");
                    String choiceTwo = request.getParameter("choiceTwo");
                    String choiceThree = request.getParameter("choiceThree");
                    String choiceFour = request.getParameter("choiceFour");
                    short correctChoice = Short.parseShort(request.getParameter("correctChoice"));
                    int marks = Integer.parseInt(request.getParameter("marks"));
                    TestQuestion testQuestion = new TestQuestion(0, question, choiceOne, choiceTwo, choiceThree, choiceFour, correctChoice, marks);
                    testQuestion.setTestId(test);
                    testQuestionFacade.create(testQuestion);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            userPath = "/faculty/createTest";
        } 
            
            else {
                response.sendRedirect("login");
                return;
            }

        

        // use RequestDispatcher to forward request internally
        String url = "/WEB-INF/view/" + userPath + ".jsp";

        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public String getServletInfo() {
        return "Faculty Servlet";
    }

}
