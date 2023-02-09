package qa.test.payrollApp.defaultData;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import qa.test.payrollApp.entity.Company;
import qa.test.payrollApp.repository.CompanyRepository;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner intiDatabase(CompanyRepository repository){
        return args -> {
            log.info("Preloading " + repository.save(new Company("Apple",1112345)));
            log.info("Preloading "+ repository.save(new Company("Amazon",1112346)));
        };
    }
}
