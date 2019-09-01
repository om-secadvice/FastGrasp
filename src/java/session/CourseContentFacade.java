/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.CourseContent;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author HARIOM
 */
@Stateless
public class CourseContentFacade extends AbstractFacade<CourseContent> {

    @PersistenceContext(unitName = "FastGraspPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CourseContentFacade() {
        super(CourseContent.class);
    }
    
}
