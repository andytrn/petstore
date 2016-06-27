package petstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * Main application entry.
 * 
 * @author atran
 *
 */
@SpringBootApplication
@ImportResource("classpath:spring-beans.xml")
public class Application {
	
	public static void main(String[] args) {
		
		SpringApplication.run(Application.class, args);
		
	}
	
}
