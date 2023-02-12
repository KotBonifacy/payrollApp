package qa.test.payrollApp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import qa.test.payrollApp.entity.Company;
import qa.test.payrollApp.entity.Employee;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Optional<Employee> findEmployeeByDocumentId(String documentId);

    List<Employee> findAllByDocumentIdStartingWith(String documentId);
    List<Employee> findAllByNameStartingWithOrSurnameStartingWith(String namePattern, String surnamePattern);
    List<Employee> findAllByCompany(Company company);
}
