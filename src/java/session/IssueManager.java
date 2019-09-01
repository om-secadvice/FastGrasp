/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;


import entity.Course;
import entity.IssuePost;
import entity.IssueThread;
import entity.Student;
import java.util.Date;
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

/**
 *
 * @author HARIOM
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class IssueManager{


    @PersistenceContext(unitName = "FastGraspPU")
    private EntityManager em;
    @Resource
    private SessionContext context;
    
    @EJB
    private IssueThreadFacade issueThreadFacade;
    
    @EJB
    private IssuePostFacade issuePostFacade;
   

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean createIssue(Student student, Course course, String title, String solution) {
        boolean res=false;
        try {
            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n");
            System.out.println(student.getId());
            System.out.println(course.getId());
            System.out.println("EM:="+em);
            IssueThread issueThread=addThread(student,course,title);
            
            addPost(student,solution,issueThread);
            res=true;
        } catch (Exception e) {
            e.printStackTrace();
            context.setRollbackOnly();
        }
        return res;
        
    }

    private IssueThread addThread(Student student, Course course, String title) {
        IssueThread issueThread=new IssueThread(0, Short.parseShort("0"), new Date(System.currentTimeMillis()));
        issueThread.setCourseId(course);
        issueThread.setStudentId(student);
        em.persist(issueThread);
        return issueThread;
    }

    private void addPost(Student student, String solution,IssueThread issueThread) {
        em.flush();
        IssuePost issuePost=new IssuePost(0, solution,student.getName(),new Date(System.currentTimeMillis()));
        issuePost.setIssueThreadId(issueThread);
        em.persist(issuePost);
    }
    
    
}
