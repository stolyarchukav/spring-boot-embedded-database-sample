package test.measure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class MeasureApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeasureApplication.class, args);
	}

}
