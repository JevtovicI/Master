package jwd.apoteka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;

@SpringBootApplication
public class ApotekaApp extends SpringBootServletInitializer {
	
	public static void main(String[] args) {
		 SpringApplication.run(ApotekaApp.class, args);
	}
}
