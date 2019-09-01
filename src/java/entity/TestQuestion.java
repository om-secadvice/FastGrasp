/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author HARIOM
 */
@Entity
@Table(name = "test_question")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TestQuestion.findAll", query = "SELECT t FROM TestQuestion t")
    , @NamedQuery(name = "TestQuestion.findById", query = "SELECT t FROM TestQuestion t WHERE t.id = :id")
    , @NamedQuery(name = "TestQuestion.findByCorrectChoice", query = "SELECT t FROM TestQuestion t WHERE t.correctChoice = :correctChoice")
    , @NamedQuery(name = "TestQuestion.findByMarks", query = "SELECT t FROM TestQuestion t WHERE t.marks = :marks")})
public class TestQuestion implements Serializable {

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
    @Column(name = "question")
    private String question;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 32767)
    @Column(name = "choice_one")
    private String choiceOne;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 32767)
    @Column(name = "choice_two")
    private String choiceTwo;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 32767)
    @Column(name = "choice_three")
    private String choiceThree;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 32767)
    @Column(name = "choice_four")
    private String choiceFour;
    @Basic(optional = false)
    @NotNull
    @Column(name = "correct_choice")
    private short correctChoice;
    @Basic(optional = false)
    @NotNull
    @Column(name = "marks")
    private int marks;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "testQuestionId")
    private Collection<StudentTestResponse> studentTestResponseCollection;
    @JoinColumn(name = "test_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Test testId;

    public TestQuestion() {
    }

    public TestQuestion(Integer id) {
        this.id = id;
    }

    public TestQuestion(Integer id, String question, String choiceOne, String choiceTwo, String choiceThree, String choiceFour, short correctChoice, int marks) {
        this.id = id;
        this.question = question;
        this.choiceOne = choiceOne;
        this.choiceTwo = choiceTwo;
        this.choiceThree = choiceThree;
        this.choiceFour = choiceFour;
        this.correctChoice = correctChoice;
        this.marks = marks;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getChoiceOne() {
        return choiceOne;
    }

    public void setChoiceOne(String choiceOne) {
        this.choiceOne = choiceOne;
    }

    public String getChoiceTwo() {
        return choiceTwo;
    }

    public void setChoiceTwo(String choiceTwo) {
        this.choiceTwo = choiceTwo;
    }

    public String getChoiceThree() {
        return choiceThree;
    }

    public void setChoiceThree(String choiceThree) {
        this.choiceThree = choiceThree;
    }

    public String getChoiceFour() {
        return choiceFour;
    }

    public void setChoiceFour(String choiceFour) {
        this.choiceFour = choiceFour;
    }

    public short getCorrectChoice() {
        return correctChoice;
    }

    public void setCorrectChoice(short correctChoice) {
        this.correctChoice = correctChoice;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    @XmlTransient
    public Collection<StudentTestResponse> getStudentTestResponseCollection() {
        return studentTestResponseCollection;
    }

    public void setStudentTestResponseCollection(Collection<StudentTestResponse> studentTestResponseCollection) {
        this.studentTestResponseCollection = studentTestResponseCollection;
    }

    public Test getTestId() {
        return testId;
    }

    public void setTestId(Test testId) {
        this.testId = testId;
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
        if (!(object instanceof TestQuestion)) {
            return false;
        }
        TestQuestion other = (TestQuestion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.TestQuestion[ id=" + id + " ]";
    }
    
}
