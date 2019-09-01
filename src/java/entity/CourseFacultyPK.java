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
public class CourseFacultyPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "course_id")
    private int courseId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "faculty_id")
    private int facultyId;

    public CourseFacultyPK() {
    }

    public CourseFacultyPK(int courseId, int facultyId) {
        this.courseId = courseId;
        this.facultyId = facultyId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(int facultyId) {
        this.facultyId = facultyId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) courseId;
        hash += (int) facultyId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CourseFacultyPK)) {
            return false;
        }
        CourseFacultyPK other = (CourseFacultyPK) object;
        if (this.courseId != other.courseId) {
            return false;
        }
        if (this.facultyId != other.facultyId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.CourseFacultyPK[ courseId=" + courseId + ", facultyId=" + facultyId + " ]";
    }
    
}
