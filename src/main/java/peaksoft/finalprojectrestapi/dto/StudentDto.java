package peaksoft.finalprojectrestapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.finalprojectrestapi.model.enums.StudyFormat;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {

    private String firstName;

    private String lastName;

    private String email;
    private StudyFormat studyFormat;
}
