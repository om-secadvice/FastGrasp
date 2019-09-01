/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.Lob;
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
@Table(name = "feedback")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Feedback.findAll", query = "SELECT f FROM Feedback f")
    , @NamedQuery(name = "Feedback.findByTitle", query = "SELECT f FROM Feedback f WHERE f.title = :title")
    , @NamedQuery(name = "Feedback.findByDateCreated", query = "SELECT f FROM Feedback f WHERE f.dateCreated = :dateCreated")
    , @NamedQuery(name = "Feedback.findByFeedbackPoint", query = "SELECT f FROM Feedback f WHERE f.feedbackPoint = :feedbackPoint")
    , @NamedQuery(name = "Feedback.findByCourseId", query = "SELECT f FROM Feedback f WHERE f.feedbackPK.courseId = :courseId")
    , @NamedQuery(name = "Feedback.findByStudentId", query = "SELECT f FROM Feedback f WHERE f.feedbackPK.studentId = :studentId")})
public class Feedback implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected FeedbackPK feedbackPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 32767)
    @Column(name = "content")
    private String content;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @Basic(optional = false)
    @NotNull
    @Column(name = "feedback_point")
    private int feedbackPoint;
    @JoinColumns({
        @JoinColumn(name = "course_id", referencedColumnName = "course_id", insertable = false, updatable = false)
        , @JoinColumn(name = "student_id", referencedColumnName = "student_id", insertable = false, updatable = false)})
    @OneToOne(optional = false)
    private RegisteredStudent registeredStudent;

    public Feedback() {
    }

    public Feedback(FeedbackPK feedbackPK) {
        this.feedbackPK = feedbackPK;
    }

    public Feedback(FeedbackPK feedbackPK, String title, String content, Date dateCreated, int feedbackPoint) {
        this.feedbackPK = feedbackPK;
        this.title = title;
        this.content = content;
        this.dateCreated = dateCreated;
        this.feedbackPoint = feedbackPoint;
    }

    public Feedback(int courseId, int studentId) {
        this.feedbackPK = new FeedbackPK(courseId, studentId);
    }

    public FeedbackPK getFeedbackPK() {
        return feedbackPK;
    }

    public void setFeedbackPK(FeedbackPK feedbackPK) {
        this.feedbackPK = feedbackPK;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public int getFeedbackPoint() {
        return feedbackPoint;
    }

    public void setFeedbackPoint(int feedbackPoint) {
        this.feedbackPoint = feedbackPoint;
    }

    public RegisteredStudent getRegisteredStudent() {
        return registeredStudent;
    }

    public void setRegisteredStudent(RegisteredStudent registeredStudent) {
        this.registeredStudent = registeredStudent;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (feedbackPK != null ? feedbackPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Feedback)) {
            return false;
        }
        Feedback other = (Feedback) object;
        if ((this.feedbackPK == null && other.feedbackPK != null) || (this.feedbackPK != null && !this.feedbackPK.equals(other.feedbackPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Feedback[ feedbackPK=" + feedbackPK + " ]";
    }
    
}
