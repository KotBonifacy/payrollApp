package qa.test.payrollApp.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import qa.test.payrollApp.controller.exceptions.RecordAlreadyExistException;
import qa.test.payrollApp.controller.exceptions.ResourceNotFoundException;
import qa.test.payrollApp.entity.Company;
import qa.test.payrollApp.entity.Employee;
import qa.test.payrollApp.repository.CompanyRepository;
import qa.test.payrollApp.repository.EmployeeRepository;

import java.util.List;

@RestController
@RequestMapping("/companies/{taxId}/employees")
public class EmployeeController {

    private final CompanyRepository companyRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeController(CompanyRepository companyRepository, EmployeeRepository employeeRepository){
        this.companyRepository = companyRepository;
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("")
    public List<Employee> getEmployees(HttpServletRequest request,
                                @PathVariable(name = "taxId") int taxId,
                                @RequestParam(required = false) String pattern){
        if(pattern == null){
            Company company = companyRepository.findCompanyByTaxId(taxId)
                    .orElseThrow(() -> new ResourceNotFoundException("Company not found"));
            return employeeRepository.findAllByCompany(company);
        }

        return employeeRepository.findAllByNameStartingWithOrSurnameStartingWith(pattern, pattern);
    }

    @GetMapping("/{documentId}")
    public List<Employee> getEmployeesByDocumentId(HttpServletRequest request,
                                @PathVariable(value="documentId") String documentId){
        return employeeRepository.findAllByDocumentIdStartingWith(documentId);
    }

    @PostMapping("")
    public ResponseEntity<Employee> addEmployee(HttpServletRequest request,
                                            @Validated @RequestBody Employee newEmployee){
        if(employeeRepository.findEmployeeByDocumentId(newEmployee.getDocumentId()).isPresent()){
            throw new RecordAlreadyExistException("Employee with given document id already exists");
        }

        return new ResponseEntity<>(employeeRepository.saveAndFlush(newEmployee), HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{documentId}")
    public ResponseEntity<Employee> editEmployee(HttpServletRequest request,
                                                 @PathVariable(name="documentId")String documentId,
                                                 @Validated @RequestBody Employee newEmployee){

        Employee employee = employeeRepository.findEmployeeByDocumentId(documentId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        employee.setName(newEmployee.getSurname());
        employee.setSurname(newEmployee.getName());
        employee.setRole(newEmployee.getRole());

        return new ResponseEntity<>(employee, HttpStatus.ACCEPTED);
    }

    @PatchMapping("/{documentId}")
    public HttpStatus fireEmployee(HttpServletRequest request,
                                   @PathVariable(name = "documentId") String documentId){
        
        Employee employee = employeeRepository.findEmployeeByDocumentId(documentId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        employee.fireEmployee();

        return HttpStatus.BAD_REQUEST;
    }
}
