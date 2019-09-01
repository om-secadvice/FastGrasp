
package session;


import entity.*;
import java.util.List;
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
public class SignupManager {
    
    @PersistenceContext(unitName = "FastGraspPU")
    private EntityManager em;
    @Resource
    private SessionContext context;
    @EJB
    private CollegeFacade collegeFacade;
    @EJB
    private StudentFacade studentFacade;
    @EJB
    private FacultyFacade facultyFacade;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean signupStudent(String name, String password, String collegeRoll, String collegeName, String contact, String email, int semester, String state, String aboutMe) {
        
        
        try {
            
            College college = addCollege(collegeName, state);
            
            Student student = new Student(0, name, collegeRoll, contact, email,new java.util.Date(System.currentTimeMillis()), password, semester);
            if(aboutMe!=null)
                student.setAboutMe(aboutMe);
            addStudent(student, college);
        } catch (Exception e) {
            context.setRollbackOnly();
            return false;
        }
        
        return true;
    }
     


    private College addCollege(String collegeName, String state) {

        List<College> colleges=collegeFacade.findByNameAndState(collegeName, state);
        College college=null;
        System.out.println("Elements:"+colleges.isEmpty());
        
        if(colleges.isEmpty()) {
            college = new College(0, collegeName, state);
        }
        else{
            college=colleges.remove(0);
        }
        em.persist(college);
        return college;     
    }

    private void addStudent(Student student, College college) {
        student.setCollegeId(college);
        em.persist(student);  
    }


   
    public boolean signupFaculty(String name, String password, String designation, String institution, String contact, String email, String state, String aboutMe) {
    
        try {
            
            Faculty faculty=new Faculty(0,name, designation, institution, contact, email,new java.util.Date(System.currentTimeMillis()), password,state);
            if(aboutMe!=null)
                faculty.setAboutMe(aboutMe);
            em.persist(faculty);
            
        } catch (Exception e) {
            e.printStackTrace();
            context.setRollbackOnly();
            return false;
        }
        return true;
    }

    

}

