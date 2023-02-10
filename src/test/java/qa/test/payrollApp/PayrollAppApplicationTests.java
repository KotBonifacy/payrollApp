package qa.test.payrollApp;

import org.junit.runner.RunWith;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = PayrollAppApplicationTests.class)
@ImportAutoConfiguration
@ComponentScan
class PayrollAppApplicationTests {

	@Autowired
	private WebTestClient testClient;

	@Test
	void getAllCompanies() {
		testClient
				.get()
				.uri("/companies")
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.jsonPath("$[0].name").isEqualTo("Apple")
				.jsonPath("$[1].name").isEqualTo("Amazon");
	}

}
