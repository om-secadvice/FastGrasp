/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author HARIOM
 */
@Entity
@Table(name = "issue_thread")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IssueThread.findAll", query = "SELECT i FROM IssueThread i")
    , @NamedQuery(name = "IssueThread.findById", query = "SELECT i FROM IssueThread i WHERE i.id = :id")
    , @NamedQuery(name = "IssueThread.findByStatus", query = "SELECT i FROM IssueThread i WHERE i.status = :status")
    , @NamedQuery(name = "IssueThread.findByDateCreated", query = "SELECT i FROM IssueThread i WHERE i.dateCreated = :dateCreated")
    , @NamedQuery(name = "IssueThread.findByDateTerminated", query = "SELECT i FROM IssueThread i WHERE i.dateTerminated = :dateTerminated")})
public class IssueThread implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private short status;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @Column(name = "date_terminated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTerminated;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "issueThreadId")
    private Collection<IssuePost> issuePostCollection;
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Course courseId;
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Student studentId;

    public IssueThread() {
    }

    public IssueThread(Integer id) {
        this.id = id;
    }

    public IssueThread(Integer id, short status, Date dateCreated) {
        this.id = id;
        this.status = status;
        this.dateCreated = dateCreated;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateTerminated() {
        return dateTerminated;
    }

    public void setDateTerminated(Date dateTerminated) {
        this.dateTerminated = dateTerminated;
    }

    @XmlTransient
    public Collection<IssuePost> getIssuePostCollection() {
        return issuePostCollection;
    }

    public void setIssuePostCollection(Collection<IssuePost> issuePostCollection) {
        this.issuePostCollection = issuePostCollection;
    }

    public Course getCourseId() {
        return courseId;
    }

    public void setCourseId(Course courseId) {
        this.courseId = courseId;
    }

    public Student getStudentId() {
        return studentId;
    }

    public void setStudentId(Student studentId) {
        this.studentId = studentId;
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
        if (!(object instanceof IssueThread)) {
            return false;
        }
        IssueThread other = (IssueThread) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.IssueThread[ id=" + id + " ]";
    }
    
}
