package peaksoft.finalprojectrestapi.service;

import peaksoft.finalprojectrestapi.dto.CompanyDto;
import peaksoft.finalprojectrestapi.model.Company;
import peaksoft.finalprojectrestapi.model.Response;
import java.util.List;

public interface CompanyService {

    Response register(CompanyDto company);

    List<Company> getAllCompany();

    Company findByCompanyId(Long id);

    Response deleteCompanyId(Long id);

    Response updateCompanyById(Long id, CompanyDto company);

}
