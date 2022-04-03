package peaksoft.finalprojectrestapi.model;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import javax.persistence.*;
import java.util.List;
import java.util.UUID;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.EAGER;

@Entity
@NoArgsConstructor
@Getter @Setter
@Table(name = "courses")
public class Course {

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
    private UUID id;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "duration_in_month")
    private String durationInMonth;


    @ManyToOne
    private Company company;

    @ManyToMany(fetch = EAGER,mappedBy = "courses", cascade = {PERSIST,MERGE, REMOVE} )
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Group> groups;

    @OneToOne(mappedBy = "course", cascade = {REMOVE})
    private Teacher teacher;

    public Course(String courseName, String durationInMonth,Company company, List<Group> groups, Teacher teacher) {
        this.courseName = courseName;
        this.durationInMonth = durationInMonth;
        this.company = company;
        this.groups = groups;
        this.teacher = teacher;
    }
    @Override
    public String toString() {
        return " Course " +
                " id " + id +
                " courseName " + courseName +
                " durationInMonth " + durationInMonth +
                " company " + company +
                " groups " + groups +
                " teacher " + teacher;
    }
}
