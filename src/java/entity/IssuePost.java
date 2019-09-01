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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "issue_post")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IssuePost.findAll", query = "SELECT i FROM IssuePost i")
    , @NamedQuery(name = "IssuePost.findById", query = "SELECT i FROM IssuePost i WHERE i.id = :id")
    , @NamedQuery(name = "IssuePost.findByAuthorName", query = "SELECT i FROM IssuePost i WHERE i.authorName = :authorName")
    , @NamedQuery(name = "IssuePost.findByDateCreated", query = "SELECT i FROM IssuePost i WHERE i.dateCreated = :dateCreated")})
public class IssuePost implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 32767)
    @Column(name = "content")
    private String content;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "author_name")
    private String authorName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @JoinColumn(name = "issue_thread_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private IssueThread issueThreadId;

    public IssuePost() {
    }

    public IssuePost(Integer id) {
        this.id = id;
    }

    public IssuePost(Integer id, String content, String authorName, Date dateCreated) {
        this.id = id;
        this.content = content;
        this.authorName = authorName;
        this.dateCreated = dateCreated;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public IssueThread getIssueThreadId() {
        return issueThreadId;
    }

    public void setIssueThreadId(IssueThread issueThreadId) {
        this.issueThreadId = issueThreadId;
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
        if (!(object instanceof IssuePost)) {
            return false;
        }
        IssuePost other = (IssuePost) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.IssuePost[ id=" + id + " ]";
    }
    
}
