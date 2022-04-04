package peaksoft.finalprojectrestapi.api;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.finalprojectrestapi.dto.CompanyDto;
import peaksoft.finalprojectrestapi.exception.BadRequestException;
import peaksoft.finalprojectrestapi.model.Company;
import peaksoft.finalprojectrestapi.model.Response;
import peaksoft.finalprojectrestapi.service.CompanyService;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController
@RequestMapping("api/companies")
@AllArgsConstructor
public class CompanyApi {

    private final CompanyService companyService;

    @PostMapping("/register")
    public Response registerNewCompany(@RequestBody CompanyDto company){
        return companyService.register(company);

    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(BAD_REQUEST)
    public Response handlerBadRequestException(BadRequestException badRequestException) {
        return Response.builder().httpStatus(BAD_REQUEST)
                .message(badRequestException.getMessage())
                .build();

    }

    @GetMapping
    public List<Company> getAllCompany(){
        return companyService.getAllCompany();

    }

    @GetMapping("/getById/{id}")
    public Company getByCompanyId(@PathVariable Long id){
        return companyService.findByCompanyId(id);

    }

    @DeleteMapping("/delete/{id}")
    public Response deleteById(@PathVariable Long id){
        return companyService.deleteCompanyId(id);

    }

    @PutMapping("/update/{id}")
    public Response updateCompanyById(@PathVariable Long id, CompanyDto company){
        return companyService.updateCompanyById(id,company);
    }
}