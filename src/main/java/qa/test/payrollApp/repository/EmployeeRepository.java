package qa.test.payrollApp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import qa.test.payrollApp.entity.Employee;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
//    List<Employee> findEmployeesByCompanyId(int companyId);
}
