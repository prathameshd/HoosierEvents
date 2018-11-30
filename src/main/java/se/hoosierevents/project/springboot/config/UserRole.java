package se.hoosierevents.project.springboot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("usertype")
public interface UserRole {

	final Long USER_NORMAL = 1L;
	final Long USER_ADMIN = 99L;
	final Long USER_ORGANIZER = 2L;
}
