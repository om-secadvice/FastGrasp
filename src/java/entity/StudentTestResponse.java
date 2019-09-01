/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author HARIOM
 */
@Entity
@Table(name = "student_test_response")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StudentTestResponse.findAll", query = "SELECT s FROM StudentTestResponse s")
    , @NamedQuery(name = "StudentTestResponse.findById", query = "SELECT s FROM StudentTestResponse s WHERE s.id = :id")
    , @NamedQuery(name = "StudentTestResponse.findByResponse", query = "SELECT s FROM StudentTestResponse s WHERE s.response = :response")})
public class StudentTestResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "response")
    private int response;
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Student studentId;
    @JoinColumn(name = "test_question_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TestQuestion testQuestionId;

    public StudentTestResponse() {
    }

    public StudentTestResponse(Integer id) {
        this.id = id;
    }

    public StudentTestResponse(Integer id, int response) {
        this.id = id;
        this.response = response;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getResponse() {
        return response;
    }

    public void setResponse(int response) {
        this.response = response;
    }

    public Student getStudentId() {
        return studentId;
    }

    public void setStudentId(Student studentId) {
        this.studentId = studentId;
    }

    public TestQuestion getTestQuestionId() {
        return testQuestionId;
    }

    public void setTestQuestionId(TestQuestion testQuestionId) {
        this.testQuestionId = testQuestionId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StudentTestResponse)) {
            return false;
        }
        StudentTestResponse other = (StudentTestResponse) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.StudentTestResponse[ id=" + id + " ]";
    }
    
}
