package peaksoft.finalprojectrestapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    private Long id;

    @Column(name = "group_name")
    private String groupName;

    private String dateOfStart;

    private String dateOfFinish;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JsonIgnore
    private List<Course> courses;

    @OneToMany(mappedBy="group", fetch=FetchType.EAGER, cascade = CascadeType.REMOVE)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Student> students;

    public Group(String groupName, String dateOfStart,String dateOfFinish,
                 List<Course> courses, List<Student> students) {
        this.groupName = groupName;
        this.dateOfStart = dateOfStart;
        this.dateOfFinish = dateOfFinish;
        this.courses = courses;
        this.students = students;
    }

    @Override
    public String toString() {
        return groupName;
    }

    @JsonIgnore
    public void setCourse(Course courseId){
        if (courses == null){
            courses = new ArrayList<>();
        }
        courses.add(courseId);
        courseId.setGroup(this);

    }
}
