
package session;

import entity.Announcement;
import entity.Course;
import java.util.Collection;
import java.util.Date;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

/**
 *
 * @author HARIOM
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class CourseManager {
    
    @PersistenceContext(unitName = "FastGraspPU")
    private EntityManager em;
    @Resource
    SessionContext context;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Course addAnnouncement(Course course, String title, String content) {
        
        boolean flag=false;
        try{
        Announcement announcement = new Announcement(0, title, content,new Date(System.currentTimeMillis()));
        announcement.setCourseId(course);
        em.persist(announcement);
        em.merge(course);
        em.clear();
        //flag=true;
        
        }catch(Exception e){
            context.setRollbackOnly();
            e.printStackTrace();
        }
        //return flag;
        return course;
        
    }
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Collection<Announcement> getAnnouncements(Course course) {
        
        
        Collection<Announcement> courseAnnouncements = course.getAnnouncementCollection();
        return courseAnnouncements;
    }
    
    

    
}
