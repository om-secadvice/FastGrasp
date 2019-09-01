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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author HARIOM
 */
@Entity
@Table(name = "course")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Course.findAll", query = "SELECT c FROM Course c")
    , @NamedQuery(name = "Course.findById", query = "SELECT c FROM Course c WHERE c.id = :id")
    , @NamedQuery(name = "Course.findByTitle", query = "SELECT c FROM Course c WHERE c.title = :title")
    , @NamedQuery(name = "Course.findByDateCreated", query = "SELECT c FROM Course c WHERE c.dateCreated = :dateCreated")
    , @NamedQuery(name = "Course.findByStartDate", query = "SELECT c FROM Course c WHERE c.startDate = :startDate")
    , @NamedQuery(name = "Course.findByEndDate", query = "SELECT c FROM Course c WHERE c.endDate = :endDate")
    , @NamedQuery(name = "Course.findByNumberOfHours", query = "SELECT c FROM Course c WHERE c.numberOfHours = :numberOfHours")
    , @NamedQuery(name = "Course.findByNumberOfTest", query = "SELECT c FROM Course c WHERE c.numberOfTest = :numberOfTest")
    , @NamedQuery(name = "Course.findByInitiated", query = "SELECT c FROM Course c WHERE c.initiated = :initiated")
    , @NamedQuery(name = "Course.findByIntendedAudience", query = "SELECT c FROM Course c WHERE c.intendedAudience = :intendedAudience")})
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 32767)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @Basic(optional = false)
    @NotNull
    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "number_of_hours")
    private int numberOfHours;
    @Basic(optional = false)
    @NotNull
    @Column(name = "number_of_test")
    private int numberOfTest;
    @Basic(optional = false)
    @NotNull
    @Column(name = "initiated")
    private short initiated;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 32767)
    @Column(name = "prerequisite")
    private String prerequisite;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "intended_audience")
    private String intendedAudience;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 32767)
    @Column(name = "syllabus")
    private String syllabus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "courseId")
    private Collection<Test> testCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private Collection<RegisteredStudent> registeredStudentCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "courseId")
    private Collection<CourseContent> courseContentCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private Collection<CourseFaculty> courseFacultyCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "courseId")
    private Collection<IssueThread> issueThreadCollection;
    @JoinColumn(name = "course_category_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CourseCategory courseCategoryId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "courseId")
    private Collection<Announcement> announcementCollection;

    public Course() {
    }

    public Course(Integer id) {
        this.id = id;
    }

    public Course(Integer id, String title, String description, Date dateCreated, Date startDate, Date endDate, int numberOfHours, int numberOfTest, short initiated, String prerequisite, String intendedAudience, String syllabus) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dateCreated = dateCreated;
        this.startDate = startDate;
        this.endDate = endDate;
        this.numberOfHours = numberOfHours;
        this.numberOfTest = numberOfTest;
        this.initiated = initiated;
        this.prerequisite = prerequisite;
        this.intendedAudience = intendedAudience;
        this.syllabus = syllabus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getNumberOfHours() {
        return numberOfHours;
    }

    public void setNumberOfHours(int numberOfHours) {
        this.numberOfHours = numberOfHours;
    }

    public int getNumberOfTest() {
        return numberOfTest;
    }

    public void setNumberOfTest(int numberOfTest) {
        this.numberOfTest = numberOfTest;
    }

    public short getInitiated() {
        return initiated;
    }

    public void setInitiated(short initiated) {
        this.initiated = initiated;
    }

    public String getPrerequisite() {
        return prerequisite;
    }

    public void setPrerequisite(String prerequisite) {
        this.prerequisite = prerequisite;
    }

    public String getIntendedAudience() {
        return intendedAudience;
    }

    public void setIntendedAudience(String intendedAudience) {
        this.intendedAudience = intendedAudience;
    }

    public String getSyllabus() {
        return syllabus;
    }

    public void setSyllabus(String syllabus) {
        this.syllabus = syllabus;
    }

    @XmlTransient
    public Collection<Test> getTestCollection() {
        return testCollection;
    }

    public void setTestCollection(Collection<Test> testCollection) {
        this.testCollection = testCollection;
    }

    @XmlTransient
    public Collection<RegisteredStudent> getRegisteredStudentCollection() {
        return registeredStudentCollection;
    }

    public void setRegisteredStudentCollection(Collection<RegisteredStudent> registeredStudentCollection) {
        this.registeredStudentCollection = registeredStudentCollection;
    }

    @XmlTransient
    public Collection<CourseContent> getCourseContentCollection() {
        return courseContentCollection;
    }

    public void setCourseContentCollection(Collection<CourseContent> courseContentCollection) {
        this.courseContentCollection = courseContentCollection;
    }

    @XmlTransient
    public Collection<CourseFaculty> getCourseFacultyCollection() {
        return courseFacultyCollection;
    }

    public void setCourseFacultyCollection(Collection<CourseFaculty> courseFacultyCollection) {
        this.courseFacultyCollection = courseFacultyCollection;
    }

    @XmlTransient
    public Collection<IssueThread> getIssueThreadCollection() {
        return issueThreadCollection;
    }

    public void setIssueThreadCollection(Collection<IssueThread> issueThreadCollection) {
        this.issueThreadCollection = issueThreadCollection;
    }

    public CourseCategory getCourseCategoryId() {
        return courseCategoryId;
    }

    public void setCourseCategoryId(CourseCategory courseCategoryId) {
        this.courseCategoryId = courseCategoryId;
    }

    @XmlTransient
    public Collection<Announcement> getAnnouncementCollection() {
        return announcementCollection;
    }

    public void setAnnouncementCollection(Collection<Announcement> announcementCollection) {
        this.announcementCollection = announcementCollection;
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
        if (!(object instanceof Course)) {
            return false;
        }
        Course other = (Course) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Course[ id=" + id + " ]";
    }
    
}
