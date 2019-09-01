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
@Table(name = "course_content")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CourseContent.findAll", query = "SELECT c FROM CourseContent c")
    , @NamedQuery(name = "CourseContent.findById", query = "SELECT c FROM CourseContent c WHERE c.id = :id")
    , @NamedQuery(name = "CourseContent.findByLink", query = "SELECT c FROM CourseContent c WHERE c.link = :link")
    , @NamedQuery(name = "CourseContent.findByType", query = "SELECT c FROM CourseContent c WHERE c.type = :type")
    , @NamedQuery(name = "CourseContent.findByTitle", query = "SELECT c FROM CourseContent c WHERE c.title = :title")
    , @NamedQuery(name = "CourseContent.findByDateCreated", query = "SELECT c FROM CourseContent c WHERE c.dateCreated = :dateCreated")})
public class CourseContent implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "link")
    private String link;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "type")
    private String type;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Course courseId;

    public CourseContent() {
    }

    public CourseContent(Integer id) {
        this.id = id;
    }

    public CourseContent(Integer id, String link, String type, String title, Date dateCreated) {
        this.id = id;
        this.link = link;
        this.type = type;
        this.title = title;
        this.dateCreated = dateCreated;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Course getCourseId() {
        return courseId;
    }

    public void setCourseId(Course courseId) {
        this.courseId = courseId;
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
        if (!(object instanceof CourseContent)) {
            return false;
        }
        CourseContent other = (CourseContent) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.CourseContent[ id=" + id + " ]";
    }
    
}
