package peaksoft.finalprojectrestapi.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.EAGER;

@Entity
@NoArgsConstructor
@Getter @Setter
@Table(name = "courses")
public class Course {

    @Id
    @SequenceGenerator(
            name = "course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_sequence"
    )
    private Long id;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "duration_in_month")
    private String durationInMonth;

    @ManyToOne
    @JsonIgnore
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

    @JsonIgnore
    public void setGroup(Group group) {
        if (groups == null) {
            groups = new ArrayList<>();
        }
        groups.add(group);
    }
}
