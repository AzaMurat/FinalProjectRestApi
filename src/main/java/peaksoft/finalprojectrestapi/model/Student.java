package peaksoft.finalprojectrestapi.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import peaksoft.finalprojectrestapi.model.enums.StudyFormat;
import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@NoArgsConstructor
@Getter @Setter
@Table(name = "students")
public class Student {

    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Email
    private String email;

    private StudyFormat studyFormat;

    @ManyToOne
    @JsonIgnore
    private Group group;

    public Student(String firstName, String lastName, StudyFormat studyFormat, Group group) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studyFormat = studyFormat;
        this.group = group;
    }
}
