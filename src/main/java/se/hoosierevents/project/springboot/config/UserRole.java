package se.hoosierevents.project.springboot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("usertype")
public interface UserRole {

	final Integer USER_ADMIN = 1;
	final Integer USER_ORGANIZER = 2;
	final Integer USER_IU = 3;
}
