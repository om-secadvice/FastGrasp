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
@Table(name = "student")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Student.findAll", query = "SELECT s FROM Student s")
    , @NamedQuery(name = "Student.findById", query = "SELECT s FROM Student s WHERE s.id = :id")
    , @NamedQuery(name = "Student.findByName", query = "SELECT s FROM Student s WHERE s.name = :name")
    , @NamedQuery(name = "Student.findByCollegeRoll", query = "SELECT s FROM Student s WHERE s.collegeRoll = :collegeRoll")
    , @NamedQuery(name = "Student.findByContact", query = "SELECT s FROM Student s WHERE s.contact = :contact")
    , @NamedQuery(name = "Student.findByEmail", query = "SELECT s FROM Student s WHERE s.email = :email")
    , @NamedQuery(name = "Student.findByDateCreated", query = "SELECT s FROM Student s WHERE s.dateCreated = :dateCreated")
    , @NamedQuery(name = "Student.findBySemester", query = "SELECT s FROM Student s WHERE s.semester = :semester")})
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "college_roll")
    private String collegeRoll;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 13)
    @Column(name = "contact")
    private String contact;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 32767)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Column(name = "semester")
    private int semester;
    @Lob
    @Size(max = 32767)
    @Column(name = "about_me")
    private String aboutMe;
    @Lob
    @Size(max = 32767)
    @Column(name = "profile_pic")
    private String profilePic;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student")
    private Collection<RegisteredStudent> registeredStudentCollection;
    @JoinColumn(name = "college_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private College collegeId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studentId")
    private Collection<StudentTestResponse> studentTestResponseCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studentId")
    private Collection<IssueThread> issueThreadCollection;

    public Student() {
    }

    public Student(Integer id) {
        this.id = id;
    }

    public Student(Integer id, String name, String collegeRoll, String contact, String email, Date dateCreated, String password, int semester) {
        this.id = id;
        this.name = name;
        this.collegeRoll = collegeRoll;
        this.contact = contact;
        this.email = email;
        this.dateCreated = dateCreated;
        this.password = password;
        this.semester = semester;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCollegeRoll() {
        return collegeRoll;
    }

    public void setCollegeRoll(String collegeRoll) {
        this.collegeRoll = collegeRoll;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    @XmlTransient
    public Collection<RegisteredStudent> getRegisteredStudentCollection() {
        return registeredStudentCollection;
    }

    public void setRegisteredStudentCollection(Collection<RegisteredStudent> registeredStudentCollection) {
        this.registeredStudentCollection = registeredStudentCollection;
    }

    public College getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(College collegeId) {
        this.collegeId = collegeId;
    }

    @XmlTransient
    public Collection<StudentTestResponse> getStudentTestResponseCollection() {
        return studentTestResponseCollection;
    }

    public void setStudentTestResponseCollection(Collection<StudentTestResponse> studentTestResponseCollection) {
        this.studentTestResponseCollection = studentTestResponseCollection;
    }

    @XmlTransient
    public Collection<IssueThread> getIssueThreadCollection() {
        return issueThreadCollection;
    }

    public void setIssueThreadCollection(Collection<IssueThread> issueThreadCollection) {
        this.issueThreadCollection = issueThreadCollection;
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
        if (!(object instanceof Student)) {
            return false;
        }
        Student other = (Student) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Student[ id=" + id + " ]";
    }
    
}
