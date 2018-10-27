package se.hoosierevents.project.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("se.hoosierevents.project.springboot")
@EntityScan("se.hoosierevents.project.model")
public class HoosierEvents {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(HoosierEvents.class, args);

	}

}