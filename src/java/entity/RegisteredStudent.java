/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author HARIOM
 */
@Entity
@Table(name = "registered_student")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RegisteredStudent.findAll", query = "SELECT r FROM RegisteredStudent r")
    , @NamedQuery(name = "RegisteredStudent.findByCourseId", query = "SELECT r FROM RegisteredStudent r WHERE r.registeredStudentPK.courseId = :courseId")
    , @NamedQuery(name = "RegisteredStudent.findByStudentId", query = "SELECT r FROM RegisteredStudent r WHERE r.registeredStudentPK.studentId = :studentId")
    , @NamedQuery(name = "RegisteredStudent.findByCourseTitle", query = "SELECT r FROM RegisteredStudent r WHERE r.courseTitle = :courseTitle")
    , @NamedQuery(name = "RegisteredStudent.findByFinalResult", query = "SELECT r FROM RegisteredStudent r WHERE r.finalResult = :finalResult")
    , @NamedQuery(name = "RegisteredStudent.findByDateOfJoining", query = "SELECT r FROM RegisteredStudent r WHERE r.dateOfJoining = :dateOfJoining")
    , @NamedQuery(name = "RegisteredStudent.findByNumberOfHours", query = "SELECT r FROM RegisteredStudent r WHERE r.numberOfHours = :numberOfHours")})
public class RegisteredStudent implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RegisteredStudentPK registeredStudentPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "course_title")
    private String courseTitle;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "final_result")
    private BigDecimal finalResult;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_of_joining")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfJoining;
    @Basic(optional = false)
    @NotNull
    @Column(name = "number_of_hours")
    private int numberOfHours;
    @JoinColumn(name = "course_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Course course;
    @JoinColumn(name = "student_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Student student;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "registeredStudent")
    private Feedback feedback;

    public RegisteredStudent() {
    }

    public RegisteredStudent(RegisteredStudentPK registeredStudentPK) {
        this.registeredStudentPK = registeredStudentPK;
    }

    public RegisteredStudent(RegisteredStudentPK registeredStudentPK, String courseTitle, BigDecimal finalResult, Date dateOfJoining, int numberOfHours) {
        this.registeredStudentPK = registeredStudentPK;
        this.courseTitle = courseTitle;
        this.finalResult = finalResult;
        this.dateOfJoining = dateOfJoining;
        this.numberOfHours = numberOfHours;
    }

    public RegisteredStudent(int courseId, int studentId) {
        this.registeredStudentPK = new RegisteredStudentPK(courseId, studentId);
    }

    public RegisteredStudentPK getRegisteredStudentPK() {
        return registeredStudentPK;
    }

    public void setRegisteredStudentPK(RegisteredStudentPK registeredStudentPK) {
        this.registeredStudentPK = registeredStudentPK;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public BigDecimal getFinalResult() {
        return finalResult;
    }

    public void setFinalResult(BigDecimal finalResult) {
        this.finalResult = finalResult;
    }

    public Date getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(Date dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public int getNumberOfHours() {
        return numberOfHours;
    }

    public void setNumberOfHours(int numberOfHours) {
        this.numberOfHours = numberOfHours;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Feedback getFeedback() {
        return feedback;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (registeredStudentPK != null ? registeredStudentPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RegisteredStudent)) {
            return false;
        }
        RegisteredStudent other = (RegisteredStudent) object;
        if ((this.registeredStudentPK == null && other.registeredStudentPK != null) || (this.registeredStudentPK != null && !this.registeredStudentPK.equals(other.registeredStudentPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.RegisteredStudent[ registeredStudentPK=" + registeredStudentPK + " ]";
    }
    
}
