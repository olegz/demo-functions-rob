package oz.functions;

import java.util.function.Function;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoFunctionsRobApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoFunctionsRobApplication.class, args);
	}
	
	@Bean
	public Function<String, String> uppercase() {
		return v -> v.toUpperCase();
	}

}
