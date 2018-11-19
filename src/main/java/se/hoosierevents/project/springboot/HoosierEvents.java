package se.hoosierevents.project.springboot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import se.hoosierevents.project.springboot.config.StorageProperties;
import se.hoosierevents.project.springboot.service.StorageService;


@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
@ComponentScan("se.hoosierevents.project.springboot")
@EntityScan("se.hoosierevents.project.model")
@Configuration
public class HoosierEvents {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(HoosierEvents.class, args);
	}

	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }
	
	@Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> {
            //storageService.deleteAll();
            storageService.init();
        };
    }
}