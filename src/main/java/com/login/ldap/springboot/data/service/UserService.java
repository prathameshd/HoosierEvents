package com.login.ldap.springboot.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.LdapShaPasswordEncoder;
import org.springframework.stereotype.Service;
import com.login.ldap.springboot.data.repository.*;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
//    @Autowired
//    private UserRepository userRepository;
//
//    public Boolean authenticate(final String username, final String password) {
//        User user = userRepository.findByUsernameAndPassword(username, password);
//        return user != null;
//    }
//
//    public List<String> search(final String username) {
//        List<User> userList = userRepository.findByUsernameLikeIgnoreCase(username);
//        if (userList == null) {
//            return Collections.emptyList();
//        }
//
//        return userList.stream()
//                .map(User::getUsername)
//                .collect(Collectors.toList());
//    }


    public void writingintoLDAP(User newUser){
        FileWriter fileWriter=null;
        PrintWriter printWriter=null;
        try {
     fileWriter = new FileWriter("C:\\Users\\Swaroop\\Desktop\\old\\hoosier-events-swaroop\\loginComponent-master\\src\\main\\resources\\schema.ldif",true);
    printWriter = new PrintWriter(fileWriter);
    printWriter.print("\n\n\n\n");
    printWriter.printf("dn: uid=%s,ou=people,dc=loginComponent,dc=com\nobjectclass: top\nobjectclass: person\nobjectclass:organizationalPerson\nobjectclass:inetOrgPerson\ncn: %s\nuid: %s\nuserPassword: %s", newUser.getUsername(),newUser.getUsername(),newUser.getUsername(), newUser.getPassword());
}
catch (Exception e){e.printStackTrace();}
finally {
    printWriter.close();

}
    }

    public void create(final String username, final String password) {
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(digestSHA(password));
        System.out.println(newUser.getPassword());

       writingintoLDAP(newUser);





    }
//
//    public void modify(final String username, final String password) {
//        User user = userRepository.findByUsername(username);
//        user.setPassword(password);
//        userRepository.save(user);
//    }

    private String digestSHA(final String password) {
        LdapShaPasswordEncoder p = new LdapShaPasswordEncoder();
        String new_password;
        new_password = p.encodePassword(password, null);
        return new_password;
    }



}
