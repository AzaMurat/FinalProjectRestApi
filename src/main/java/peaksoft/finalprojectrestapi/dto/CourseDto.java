package peaksoft.finalprojectrestapi.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CourseDto {

    @NotBlank
    private String courseName;

    @NotBlank
    private String durationInMonth;
}
