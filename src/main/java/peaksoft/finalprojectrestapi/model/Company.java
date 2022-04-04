package peaksoft.finalprojectrestapi.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

import static javax.persistence.CascadeType.*;

@Entity
@RequiredArgsConstructor
@Getter @Setter
@Table(name = "companies")
public class Company {

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

    @Column(name ="company_name")
    private String companyName;

    @Column(name = "email")
    private String email;

    @Column(name = "located_country")
    private String locatedCountry;

    @OneToMany(cascade = {PERSIST, MERGE, REMOVE}, mappedBy = "company")
    private List<Course> courses;

    @Override
    public String toString() {
        return " Company " +
                " id " + id +
                " companyName " + companyName +
                " locatedCountry " + locatedCountry +
                ", courses=" + courses;
    }

}
