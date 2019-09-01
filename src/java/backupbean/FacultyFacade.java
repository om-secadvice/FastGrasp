/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

package session;

import entity.Faculty;
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

@Stateless
public class FacultyFacade extends AbstractFacade<Faculty> {

    @PersistenceContext(unitName = "FastGraspPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FacultyFacade() {
        super(Faculty.class);
    }
    
    
    public List<Faculty> findByEmailAndPassword(String email, String password) {
        CriteriaQuery<Faculty> cq = getEntityManager().getCriteriaBuilder().createQuery(Faculty.class); //CriteriaQuery object
        Root<Faculty> faculty = cq.from(Faculty.class); //Iterate through range of rows
        Predicate conditionEmail=em.getCriteriaBuilder().equal(faculty.get("email"), email); //Condition for where clause
        Predicate conditionPassword=em.getCriteriaBuilder().equal(faculty.get("password"), password); //Condition for where clause
        Predicate condition=em.getCriteriaBuilder().and(conditionEmail,conditionPassword); //Combine both Condition for where clause
        cq.where(condition); //Add condition to where clause.
        Query q=em.createQuery(cq); //Execute query
        return q.getResultList();
    }
    
}
*/