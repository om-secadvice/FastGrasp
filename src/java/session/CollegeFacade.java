/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
*/
package session;

import entity.College;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author HARIOM
 */
@Stateless
public class CollegeFacade extends AbstractFacade<College> {

    @PersistenceContext(unitName = "FastGraspPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CollegeFacade() {
        super(College.class);
    }
    
    public List<College> findByNameAndState(String collegeName,String stateName) {
        
        CriteriaQuery<College> cq = getEntityManager().getCriteriaBuilder().createQuery(College.class); //CriteriaQuery object
        Root<College> college = cq.from(College.class); //Iterate through range of rows
        Predicate conditionName=em.getCriteriaBuilder().equal(college.get("state"), stateName); //Condition for where clause
        Predicate conditionState=em.getCriteriaBuilder().equal(college.get("collegeName"), collegeName); //Condition for where clause
        Predicate condition=em.getCriteriaBuilder().and(conditionName,conditionState); //Combine both Condition for where clause
        cq.where(condition); //Add condition to where clause.
        Query q=em.createQuery(cq); //Execute query
        
        return q.getResultList();
    }
    
}
