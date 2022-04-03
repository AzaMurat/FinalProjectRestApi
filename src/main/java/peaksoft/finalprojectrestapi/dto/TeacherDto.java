package peaksoft.finalprojectrestapi.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class TeacherDto {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastNAme;

}
