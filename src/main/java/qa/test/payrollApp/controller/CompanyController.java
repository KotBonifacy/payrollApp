package qa.test.payrollApp.controller;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import qa.test.payrollApp.entity.Company;
import qa.test.payrollApp.repository.CompanyRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyRepository companyRepository;

    public CompanyController(CompanyRepository companyRepository){
        this.companyRepository =  companyRepository;
    }

    @GetMapping("")
    public List<Company> getCompanies(HttpServletRequest request,
                                      @RequestParam(required = false) String name){
        if(name == null)
            return companyRepository.findAll();
        else
            return companyRepository.findCompaniesByNameStartsWith(name);
    }
    @GetMapping("/{taxId}")
    public Optional<Company> getCompany(HttpServletRequest request,
                                        @PathVariable(value="taxId") int taxId){
        return companyRepository.findCompanyByTaxId(taxId);
    }

    @PostMapping("")
    public ResponseEntity<Company> addCompany(HttpServletRequest request,
                                              @Validated @RequestBody Company company){
        return new ResponseEntity<>(companyRepository.saveAndFlush(company), HttpStatus.CREATED);
    }
}
