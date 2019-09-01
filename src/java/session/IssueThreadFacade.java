/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.IssueThread;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author HARIOM
 */
@Stateless
public class IssueThreadFacade extends AbstractFacade<IssueThread> {

    @PersistenceContext(unitName = "FastGraspPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IssueThreadFacade() {
        super(IssueThread.class);
    }
    
}
