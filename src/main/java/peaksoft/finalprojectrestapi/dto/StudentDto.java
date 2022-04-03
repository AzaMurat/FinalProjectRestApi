package peaksoft.finalprojectrestapi.dto;

import lombok.Getter;
import lombok.Setter;
import peaksoft.finalprojectrestapi.model.enums.StudyFormat;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class StudentDto {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private StudyFormat studyFormat;
}
