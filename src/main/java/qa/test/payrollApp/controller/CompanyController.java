package qa.test.payrollApp.controller;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import qa.test.payrollApp.controller.exceptions.RecordAlreadyExistException;
import qa.test.payrollApp.controller.exceptions.ResourceNotFoundException;
import qa.test.payrollApp.entity.Company;
import qa.test.payrollApp.entity.dictionary.CompanyStatus;
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
        if(name == null || !name.isEmpty())
            return companyRepository.findAll();
        else
            return companyRepository.findCompaniesByNameStartsWith(name);
    }
    @GetMapping("/{taxId}")
    public Company getCompany(HttpServletRequest request,
                                        @PathVariable(value="taxId") int taxId){

        return companyRepository.findCompanyByTaxId(taxId)
                .orElseThrow(() -> new RecordAlreadyExistException("Company not found."));
    }

    @PostMapping("")
    public ResponseEntity<Company> addCompany(HttpServletRequest request,
                                              @Validated @RequestBody Company company){

        return new ResponseEntity<>(companyRepository.saveAndFlush(company), HttpStatus.CREATED);
    }

    @PutMapping("/{taxId}")
    public ResponseEntity<Company> editCompany(HttpServletRequest request,
                                               @PathVariable(value="taxId") int taxId,
                                               @Validated @RequestBody Company newCompany){
        Company company = companyRepository.findCompanyByTaxId(taxId)
                .orElseThrow(()-> new ResourceNotFoundException("Company with given tax Id not found"));

        return new ResponseEntity<>(company, HttpStatus.ACCEPTED);
    }

    @PatchMapping("/{taxId}")
    public ResponseEntity deleteCompany(HttpServletRequest request,
                                                 @PathVariable(value="taxId") int taxId){
        Company company = companyRepository.findCompanyByTaxId(taxId)
                .orElseThrow(()-> new RecordAlreadyExistException("Company with given tax Id not found"));

        company.setStatus(CompanyStatus.deleted);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
