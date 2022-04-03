package peaksoft.finalprojectrestapi.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CompanyDto {

    @NotBlank
    private String companyName;

    @Email
    private String email;

    @NotBlank
    private String locatedCountry;
}
