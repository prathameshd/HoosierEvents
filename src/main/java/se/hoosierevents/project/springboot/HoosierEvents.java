package se.hoosierevents.project.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@org.springframework.boot.autoconfigure.SpringBootApplication
@ComponentScan("se.hoosierevents")
public class HoosierEvents {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(HoosierEvents.class, args);

	}

}