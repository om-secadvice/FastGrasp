/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author HARIOM
 */
@Embeddable
public class FeedbackPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "course_id")
    private int courseId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "student_id")
    private int studentId;

    public FeedbackPK() {
    }

    public FeedbackPK(int courseId, int studentId) {
        this.courseId = courseId;
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) courseId;
        hash += (int) studentId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FeedbackPK)) {
            return false;
        }
        FeedbackPK other = (FeedbackPK) object;
        if (this.courseId != other.courseId) {
            return false;
        }
        if (this.studentId != other.studentId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.FeedbackPK[ courseId=" + courseId + ", studentId=" + studentId + " ]";
    }
    
}
