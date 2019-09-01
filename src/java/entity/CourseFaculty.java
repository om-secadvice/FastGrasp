/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author HARIOM
 */
@Entity
@Table(name = "course_faculty")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CourseFaculty.findAll", query = "SELECT c FROM CourseFaculty c")
    , @NamedQuery(name = "CourseFaculty.findByCourseId", query = "SELECT c FROM CourseFaculty c WHERE c.courseFacultyPK.courseId = :courseId")
    , @NamedQuery(name = "CourseFaculty.findByFacultyId", query = "SELECT c FROM CourseFaculty c WHERE c.courseFacultyPK.facultyId = :facultyId")
    , @NamedQuery(name = "CourseFaculty.findByApproval", query = "SELECT c FROM CourseFaculty c WHERE c.approval = :approval")})
public class CourseFaculty implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CourseFacultyPK courseFacultyPK;
    @Column(name = "approval")
    private Boolean approval;
    @JoinColumn(name = "course_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Course course;
    @JoinColumn(name = "faculty_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Faculty faculty;

    public CourseFaculty() {
    }

    public CourseFaculty(CourseFacultyPK courseFacultyPK) {
        this.courseFacultyPK = courseFacultyPK;
    }

    public CourseFaculty(int courseId, int facultyId) {
        this.courseFacultyPK = new CourseFacultyPK(courseId, facultyId);
    }

    public CourseFacultyPK getCourseFacultyPK() {
        return courseFacultyPK;
    }

    public void setCourseFacultyPK(CourseFacultyPK courseFacultyPK) {
        this.courseFacultyPK = courseFacultyPK;
    }

    public Boolean getApproval() {
        return approval;
    }

    public void setApproval(Boolean approval) {
        this.approval = approval;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (courseFacultyPK != null ? courseFacultyPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CourseFaculty)) {
            return false;
        }
        CourseFaculty other = (CourseFaculty) object;
        if ((this.courseFacultyPK == null && other.courseFacultyPK != null) || (this.courseFacultyPK != null && !this.courseFacultyPK.equals(other.courseFacultyPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.CourseFaculty[ courseFacultyPK=" + courseFacultyPK + " ]";
    }
    
}
