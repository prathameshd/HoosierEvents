package se.hoosierevents.project.springboot.springboot.data.repository;

import org.springframework.data.ldap.repository.LdapRepository;

import java.util.List;

public interface UserRepository extends LdapRepository<User> {

    User findByUsername(String username);

    User findByUsernameAndPassword(String username, String password);

    List<User> findByUsernameLikeIgnoreCase(String username);
}
