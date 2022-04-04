package peaksoft.finalprojectrestapi.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import peaksoft.finalprojectrestapi.model.enums.StudyFormat;
import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter @Setter
@Table(name = "students")
public class Student {

    @Id
    @SequenceGenerator(
            name = "company_sequence",
            sequenceName = "company_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "company_sequence"
    )
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

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
