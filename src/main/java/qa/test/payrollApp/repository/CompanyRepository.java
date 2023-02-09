package qa.test.payrollApp.repository;

import qa.test.payrollApp.entity.Company;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

    List<Company> findCompaniesByNameStartsWith(String name);

    Optional<Company> findCompanyByTaxId(int taxId);
}
