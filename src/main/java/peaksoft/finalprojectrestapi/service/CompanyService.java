package peaksoft.finalprojectrestapi.service;

import peaksoft.finalprojectrestapi.model.Company;
import peaksoft.finalprojectrestapi.model.Response;
import java.util.List;
import java.util.UUID;

public interface CompanyService {

    Response register(Company company);

    List<Company> getAllCompany();

    Company findByCompanyId(UUID id);

    Response deleteCompanyId(UUID id);

    Response updateCompanyById(UUID id, Company company);

}
