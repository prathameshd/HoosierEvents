package se.hoosierevents.project.springboot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("usertype")
public interface UserRole {

	final Integer USER_NORMAL = 1;
	final Integer USER_ADMIN = 99;
	final Integer USER_ORGANIZER = 2;
}
