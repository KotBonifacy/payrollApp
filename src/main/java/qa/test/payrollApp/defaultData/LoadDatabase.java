package qa.test.payrollApp.defaultData;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import qa.test.payrollApp.entity.Company;
import qa.test.payrollApp.entity.Employee;
import qa.test.payrollApp.entity.dictionary.Role;
import qa.test.payrollApp.entity.dictionary.CompanyStatus;
import qa.test.payrollApp.repository.CompanyRepository;
import qa.test.payrollApp.repository.EmployeeRepository;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner intiDatabase(CompanyRepository companyRepository, EmployeeRepository employeeRepository){
        Company apple = new Company("Apple",1112345, CompanyStatus.active);
        Company amazon = new Company("Amazon",1112346, CompanyStatus.active);
        Employee john = new Employee(apple, "John", "Doe", "ABC123456", Role.driver);
        Employee carl = new Employee(apple, "Carl", "Doe", "XYZ123456", Role.manager);
        Employee johnFromAmazon = new Employee(amazon, "John", "Doe", "AAA123456", Role.seller);
        return args -> {
            log.info("Preloading " + companyRepository.save(apple));
            log.info("Preloading "+ companyRepository.save(amazon));
            log.info("Preloading "+ employeeRepository.save(john));
            log.info("Preloading " + employeeRepository.save(carl));
            log.info("Preloading " + employeeRepository.save(johnFromAmazon));
        };
    }
}
