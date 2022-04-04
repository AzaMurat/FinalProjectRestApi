package peaksoft.finalprojectrestapi.maper;

import org.springframework.stereotype.Component;
import peaksoft.finalprojectrestapi.dto.CompanyDto;
import peaksoft.finalprojectrestapi.model.Company;

@Component
public class CompanyMapper {

    public Company create(CompanyDto companyDto){
        if (companyDto==null){
            return null;
        }

        Company company = new Company();
        company.setCompanyName(companyDto.getCompanyName());
        company.setEmail(companyDto.getEmail());
        company.setLocatedCountry(companyDto.getLocatedCountry());

        return company;
    }
}
