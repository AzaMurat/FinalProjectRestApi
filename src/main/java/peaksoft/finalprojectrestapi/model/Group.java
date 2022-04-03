package peaksoft.finalprojectrestapi.model;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter @Setter
@Table(name = "groups")
public class Group {

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

    @Column(name = "group_name")
    private String groupName;

    private String dateOfStart;

    private String dateOfFinish;

    @ManyToMany(cascade = CascadeType.MERGE)
    private List<Course> courses;

    @OneToMany(mappedBy="group", fetch=FetchType.EAGER, cascade = CascadeType.REMOVE)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Student> students;

    @ManyToOne
    private Company company;

    public Group(String groupName, String dateOfStart,String dateOfFinish, List<Course> courses, List<Student> students, Company company) {
        this.groupName = groupName;
        this.dateOfStart = dateOfStart;
        this.dateOfFinish = dateOfFinish;
        this.courses = courses;
        this.students = students;
        this.company = company;
    }

    @Override
    public String toString() {
        return groupName;
    }
}
