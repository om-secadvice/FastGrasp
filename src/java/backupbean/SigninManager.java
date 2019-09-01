/*
package session;

import entity.*;
import java.util.*;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class SigninManager {
    
    @PersistenceContext(unitName = "FastGraspPU")
    private EntityManager em;
    @Resource
    private SessionContext context;
    @EJB
    private StudentFacade studentFacade;
    @EJB
    private FacultyFacade facultyFacade;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Student signinStudent(String email, String password) {
       
        Student student;
        List<Student> studentList = studentFacade.findByEmailAndPassword(email, password);
        if(studentList.isEmpty()) {
            student = null;
        }
        else {
         student = studentList.get(0);
         System.out.println(student.toString());
        }
        return student;
    }
    
  
     public Faculty signinFaculty(String email, String password) {
       
        Faculty faculty;        
        List<Faculty> facultyList = facultyFacade.findByEmailAndPassword(email, password);
        if(facultyList.isEmpty()) {
            faculty = null;
        }
        else {
         faculty = facultyList.get(0);
         System.out.println(faculty.toString());
        }
        return faculty;
    }

}
*/