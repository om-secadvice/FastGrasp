package controller;

import entity.*;
import java.io.IOException;
import java.util.*;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.*;
import session.*;

@WebServlet(
        name = "StudentController",
        loadOnStartup = 1,
        urlPatterns = {
            "/student/login",
            "/student/register",
            "/student/dashboard",
            "/student/explore",
            "/student/myCourses",
            "/student/myTests",
            "/student/profile",
            "/student/takeTest",
            "/student/discussionForum",
            "/student/courseLecture",
            "/student/explore/courseDetails",
            "/student/createIssue",
            "/student/submitSolution",
            "/student/submitResponse"
        }
)

public class StudentServlet extends HttpServlet {

    @EJB
    private IssueThreadFacade issueThreadFacade;

    @EJB
    private IssuePostFacade issuePostFacade;
    @EJB
    private CourseCategoryFacade categoryFacade;

    @EJB
    private CourseFacade courseFacade;

    @EJB
    private StudentFacade studentFacade;

    @EJB
    private SignupManager signupManager;

    @EJB
    private SigninManager signinManager;

    @EJB
    private TestFacade testFacade;
    @EJB
    private StudentTestResponseFacade studentTestResponseFacade;

    @EJB
    IssueManager issueManager;
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
        //Initialise any context-param or init-param

        //Set course category to apllication context
        getServletContext().setAttribute("categories", categoryFacade.findAll());

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userPath = request.getServletPath();
        HttpSession session = request.getSession();
        CourseCategory selectedCategory;
        Collection<Course> categoryCourses;
        Course selectedCourse;

        if (userPath.equals("/student/login")) {
            session.setAttribute("userType", "student");
            userPath = "/login";
        } else if (userPath.equals("/student/register")) {
            session.setAttribute("userType", "student");
            userPath = "/register";
        } else if (userPath.equals("/student/dashboard")) {
            if (session.getAttribute("currentUser") != null) {
                userPath = "/dashboard";
            } else {
                response.sendRedirect("/FastGrasp");
                return;
            }

        } else if (userPath.equals("/student/explore")) {
            String categoryId = request.getQueryString();

            if (categoryId != null) {

                selectedCategory = categoryFacade.find(Integer.parseInt(categoryId));

                session.setAttribute("selectedCategory", selectedCategory);

                categoryCourses = selectedCategory.getCourseCollection();

                session.setAttribute("categoryCourses", categoryCourses);
            } else {
                session.removeAttribute("selectedCategory");
                session.removeAttribute("categoryCourses");

                session.setAttribute("categoryCourses", courseFacade.findAll());

            }

            userPath = "/explore";
        } else if (userPath.equals("/student/explore/courseDetails")) {
            String courseId = request.getParameter("courseId");
            if (courseId != null) {
                selectedCourse = courseFacade.find(Integer.parseInt(courseId));
                session.setAttribute("selectedCourse", selectedCourse);
                userPath = "/courseDetails";
            } else {
                userPath = "/explore";
            }

        } else if (userPath.equals("/student/myCourses")) {
            if (session.getAttribute("currentUser") != null) {
                Student student = (Student) session.getAttribute("currentUser");
                try {
                    Collection<RegisteredStudent> registeredStudents = student.getRegisteredStudentCollection();
                    Iterator<RegisteredStudent> itr = registeredStudents.iterator();
                    Collection<Course> myCourses = new LinkedList();
                    while (itr.hasNext()) {
                        Course course = itr.next().getCourse();
                        myCourses.add(course);

                    }
                    System.out.println("###########################################\nSize of coursecol:-" + myCourses.size());
                    session.setAttribute("myCourses", myCourses);

                    userPath = "/myCourses";
                } catch (Exception e) {
                    userPath = "/dashboard";
                }

            } else {
                response.sendRedirect("/FastGrasp");
                return;
            }
        } else if (userPath.equals("/student/myTests")) {
            Collection<Course> myCourses = new LinkedList();
            Collection<Test> myTests;
            if (session.getAttribute("currentUser") != null) {
                try {
                    Student student = (Student) session.getAttribute("currentUser");
                    String courseId = request.getParameter("courseId");
                    //try {
                        Collection<RegisteredStudent> registeredStudents = student.getRegisteredStudentCollection();
                        Iterator<RegisteredStudent> itr = registeredStudents.iterator();
                        
                        while (itr.hasNext()) {
                            Course course = itr.next().getCourse();
                            myCourses.add(course);

                        }

                        session.setAttribute("myCourses", myCourses);

                   /* } catch (Exception e) {
                        e.printStackTrace();
                    }*/
                    if (courseId != null) {
                        selectedCourse = courseFacade.find(Integer.parseInt(courseId));

                    } else {
                        selectedCourse = courseFacade.find(myCourses.iterator().next().getId());
                    }
                    session.setAttribute("selectedCourse", selectedCourse);
                    myTests = selectedCourse.getTestCollection();
                    Iterator<Test> titr = myTests.iterator();
                    while (titr.hasNext()) {
                        
                        Test test = titr.next();
                        int obtained = testFacade.findResponseByTestQuestionAndStudent(test, student);
                        test.setObtained(obtained);
                    }

                    session.setAttribute("myTests", myTests);

                    userPath = "/myTests";
                } catch (Exception e) {
                    e.printStackTrace();
                    userPath = "/dashboard";
                }
            } else {
                response.sendRedirect("/FastGrasp");
                return;
            }

        } else if (userPath.equals("/student/profile")) {
            if (session.getAttribute("currentUser") != null) {
                userPath = "/profile";
            } else {
                response.sendRedirect("/FastGrasp");
                return;
            }
        } else if (userPath.equals("/student/takeTest")) {
            if (session.getAttribute("currentUser") != null) {
                String testId=request.getParameter("testId");
                Test test=testFacade.find(Integer.parseInt(testId));
                session.setAttribute("testQuestions",test.getTestQuestionCollection());
                session.setAttribute("testId",testFacade.find(Integer.parseInt(testId)));
                
                userPath = "/takeTest";
            } else {
                response.sendRedirect("/FastGrasp");
                return;
            }

        } else if (userPath.equals("/student/createIssue")) {
            if (session.getAttribute("currentUser") != null) {
                String courseId=request.getParameter("courseId");
                System.out.println("############################################\n"+courseId);
                if(courseId!=null){
                    try {
                    selectedCourse=courseFacade.find(Integer.parseInt(courseId));
                    session.setAttribute("selectedCourse",selectedCourse);
                    } catch (Exception e) {
                        e.printStackTrace();
                        response.sendRedirect("dashboard");
                        return;
                    }
                    
                }
                userPath = "/createIssue";
            } else {
                response.sendRedirect("/FastGrasp");
                return;
            }

        } else if (userPath.equals("/student/discussionForum")) {
            if (session.getAttribute("currentUser") != null) {
                userPath = "/discussionForum";
            } else {
                response.sendRedirect("/FastGrasp");
                return;
            }
        }

        String url = "/WEB-INF/view/student" + userPath + ".jsp";

        if (userPath.equals("/student/courseLecture")) {
            
            if (session.getAttribute("currentUser") != null) {
               try {
                 String courseId=request.getParameter("courseId");
                Course course = courseFacade.find(Integer.parseInt(courseId));
                session.setAttribute("selectedCourse", course);
                //System.out.println("$#######################################Session:"+session.getAttribute("currentUser")+":"+request.getParameter("courseId"));
                url = "/faculty/courseLecture";
                session.setAttribute("selectedCourse", course);
            } catch (Exception e) {
                e.printStackTrace();
                url = "/WEB-INF/view/student/dashboard.jsp";
            }

            } else {
                response.sendRedirect("/FastGrasp");
                return;
            }
        }
        try {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String userPath = request.getServletPath();
        HttpSession session = request.getSession();

        if (userPath.equals("/student/login")) {

            //if(name!=null){ 
            String name, password, collegeRoll, collegeName, contact, email, state, aboutMe;
            name = request.getParameter("username");
            password = request.getParameter("password");
            collegeRoll = request.getParameter("collegeRollNo");
            collegeName = request.getParameter("collegeName");
            contact = request.getParameter("contact");
            email = request.getParameter("email");
            int semester = Integer.parseInt(request.getParameter("semester"));
            state = request.getParameter("state");
            aboutMe = request.getParameter("aboutMe");

            if (signupManager.signupStudent(name, password, collegeRoll, collegeName, contact, email, semester, state, aboutMe)) {
                userPath = "/login";
            } else {
                userPath = "/register";
            }

        } else if (userPath.equals("/student/dashboard")) {
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            Student student = signinManager.signinStudent(email, password);
            if (student != null) {

                session.setAttribute("currentUser", student);
                session.setAttribute("college", student.getCollegeId());
                //String profilepic="data:image/jpeg;base64," + Base64.getEncoder().encodeToString(student.getProfilePic());
                //session.setAttribute("profilePic", profilepic);
                userPath = "/dashboard";
            } else {

                response.sendRedirect("login");
                return;
            }
        } else if (userPath.equals("/student/profile")) {
            String name, password, collegeRoll, collegeName, contact, email, state, aboutMe;
            Student student = (Student) session.getAttribute("currentUser");

            name = request.getParameter("name");
            collegeRoll = request.getParameter("collegeRoll");
            int semester = Integer.parseInt(request.getParameter("semester"));
            contact = request.getParameter("contact");
            aboutMe = request.getParameter("aboutMe");
            password = request.getParameter("password");

            student.setName(name);
            student.setCollegeRoll(collegeRoll);
            student.setSemester(semester);
            student.setContact(contact);
            student.setAboutMe(aboutMe);
            student.setPassword(password);

            studentFacade.edit(student);
            session.setAttribute("currentUser", student);

            userPath = "/profile";

        }

        if (userPath.equals("/student/createIssue")) {
            String title = request.getParameter("title");
            int courseId = Integer.parseInt(request.getParameter("courseId"));
            Course course=courseFacade.find(courseId);
            String solution = request.getParameter("content");
            Student student = (Student) session.getAttribute("currentUser");
         
            System.out.println(solution+student+course+title);
            if(issueManager.createIssue(student,course,title,solution)){
                System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
            }
            /*IssueThread issueThread = new IssueThread(course, Short.MIN_VALUE,new Date(System.currentTimeMillis()));
            issueThread.setStudentId(student);
            issueThread.setStatus(Short.parseShort("0"));
            issueThreadFacade.create(issueThread);
            System.out.println("########################################id="+issueThread.getId());
            IssuePost issuePost = new IssuePost(courseId, solution,student.getName(),new Date(System.currentTimeMillis()));
            issuePost.setIssueThreadId(issueThread);
            issuePostFacade.create(issuePost);*/
            userPath = "/dashboard";
        }

        if (userPath.equals("/student/submitSolution")) {
            String solution = request.getParameter("solution");
            Student student = (Student) session.getAttribute("currentUser");
            int issueThreadId = Integer.parseInt(request.getParameter("issueThreadId"));
            IssueThread issueThread = issueThreadFacade.find(issueThreadId);
            IssuePost issuePost = new IssuePost();
            issuePost.setAuthorName(student.getName());
            issuePost.setContent(solution);
            issuePost.setIssueThreadId(issueThread);
            issuePostFacade.create(issuePost);
            userPath = "/dashboard";
        }

        if (userPath.equals("/student/submitResponse")) {
            if ((session.getAttribute("currentUser") != null)) {
                try {
                    
                } catch (Exception e) {
                }
                Test test = (Test) session.getAttribute("testId");
                StudentTestResponse studentTestResponse;
               
                
                Iterator<TestQuestion> itr=((Collection<TestQuestion>)session.getAttribute("testQuestions")).iterator();
                
                while(itr.hasNext()){
                    TestQuestion tq=itr.next();
                    int res=Integer.parseInt(request.getParameter(String.valueOf(tq.getId())));
                    studentTestResponse = new StudentTestResponse(0,res);
                    studentTestResponse.setTestQuestionId(tq);
                    studentTestResponse.setStudentId((Student)session.getAttribute("currentUser"));
                    studentTestResponseFacade.create(studentTestResponse);
                }
               
                session.removeAttribute("testId");
                session.removeAttribute("testQuestions");
                response.sendRedirect("../student/myTests");
            } else if (session.getAttribute("currentUser") == null) {
                response.sendRedirect("/FastGrasp");
                return;
            }
            

        }

        String url = "/WEB-INF/view/student" + userPath + ".jsp";

        try {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
