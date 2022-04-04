package peaksoft.finalprojectrestapi.service.impl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.finalprojectrestapi.dto.CompanyDto;
import peaksoft.finalprojectrestapi.maper.CompanyMapper;
import peaksoft.finalprojectrestapi.exception.BadRequestException;
import peaksoft.finalprojectrestapi.exception.NotFountException;
import peaksoft.finalprojectrestapi.model.Company;
import peaksoft.finalprojectrestapi.model.Response;
import peaksoft.finalprojectrestapi.repository.CompanyRepository;
import peaksoft.finalprojectrestapi.service.CompanyService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import static org.springframework.http.HttpStatus.*;

@Service
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper mapper;

    @Override
    public Response register(CompanyDto company) {
        String email = company.getEmail();
        Optional<Company> byEmail = companyRepository.findByEmail(email);

        if (byEmail.isPresent()) {
            throw new BadRequestException("Company with email=" + email + "already exists");
        }
        Company company1 = mapper.create(company);
        Company saveCompany = companyRepository.save(company1);

        return Response.builder()
                .httpStatus(CREATED)
                .message(String.format("Company with email = %s successfully registered",
                        saveCompany.getEmail())).build();
    }

    @Override
    public List<Company> getAllCompany() {
        return companyRepository.findAll().stream().toList();
    }

    @Override
    public Company findByCompanyId(Long id) {

        return companyRepository.findById(id)
                .orElseThrow(() -> {
                            throw new NotFountException("Company with id not found from data base");
                        }
                );
    }

    @Override
    public Response deleteCompanyId(Long id) {
        companyRepository.deleteById(id);
        return Response.builder().httpStatus(OK).build();
    }

    @Transactional
    @Override
    public Response updateCompanyById(Long id, CompanyDto newCompany) {
        Company oldCompany = companyRepository.findById(id)
                .orElseThrow(() -> {
                            throw new NotFountException("Company with id not found from data base");
                        }
                );
        String currentName = oldCompany.getCompanyName();
        String newCompanyName = newCompany.getCompanyName();
        if (!Objects.equals(currentName, newCompanyName)) {
            oldCompany.setCompanyName(newCompanyName);
        }
        String currentEmail = oldCompany.getEmail();
        String newCompanyEmail = newCompany.getEmail();
        if (!Objects.equals(currentEmail, newCompanyEmail)) {
            oldCompany.setEmail(newCompanyEmail);
        }

        String currentLocatedCountry = oldCompany.getLocatedCountry();
        String newCompanyLocatedCountry = newCompany.getLocatedCountry();
        if (!Objects.equals(currentLocatedCountry, newCompanyLocatedCountry)) {
            oldCompany.setLocatedCountry(newCompanyLocatedCountry);
        }
        String message = String.format("Company with id = %s successfully updated",id);
        return Response.builder().httpStatus(RESET_CONTENT).message(message).build();
    }
}
