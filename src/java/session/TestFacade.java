/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Student;
import entity.StudentTestResponse;
import entity.Test;
import entity.TestQuestion;
import java.util.Collection;
import java.util.Iterator;
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
public class TestFacade extends AbstractFacade<Test> {

    @PersistenceContext(unitName = "FastGraspPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TestFacade() {
        super(Test.class);
    }
    

    public int findResponseByTestQuestionAndStudent(Test test, Student studentId) {
        Collection<TestQuestion> testQuestions = test.getTestQuestionCollection();
        int obtained = 0;
        Iterator<TestQuestion> tQItr = testQuestions.iterator();
        while (tQItr.hasNext()) {
            TestQuestion tq = tQItr.next();
            short cc = tq.getCorrectChoice();
            CriteriaQuery<StudentTestResponse> cq = getEntityManager().getCriteriaBuilder().createQuery(StudentTestResponse.class); //CriteriaQuery object
            Root<StudentTestResponse> studentTestResponse = cq.from(StudentTestResponse.class); //Iterate through range of rows
            Predicate conditionStudent = em.getCriteriaBuilder().equal(studentTestResponse.get("studentId"), studentId); //Condition for where clause
            Predicate conditionTestQuestion = em.getCriteriaBuilder().equal(studentTestResponse.get("testQuestionId"), tq); //Condition for where clause
            Predicate condition = em.getCriteriaBuilder().and(conditionStudent, conditionTestQuestion); //Combine both Condition for where clause
            cq.where(condition); //Add condition to where clause.
            Query q = em.createQuery(cq); //Execute query
            List<StudentTestResponse> strc = q.getResultList();
            StudentTestResponse str;
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>  ");
            if (!strc.isEmpty()) {
                str = strc.get(1);
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>  ");

            if (str.getResponse() == cc) {
                
                obtained += tq.getMarks();
            }
            }
        }

        return obtained;
    }

    
}
