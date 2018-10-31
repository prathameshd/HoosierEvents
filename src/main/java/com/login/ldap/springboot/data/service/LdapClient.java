package com.login.ldap.springboot.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.ldap.core.*;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.security.authentication.encoding.LdapShaPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.Name;
import javax.naming.directory.ModificationItem;
import java.util.List;

@Service
public class LdapClient {

    @Autowired
    private Environment env;

    @Autowired
    private ContextSource contextSource;

    @Autowired
    private LdapTemplate ldapTemplate;

    public void authenticate(final String username, final String password) {
        contextSource.getContext("cn=" + username + ",ou=people," + env.getRequiredProperty("ldap.partitionSuffix"), password);
    }

    public List<String> search(final String username) {
        return ldapTemplate.search(
                "ou=people",
                "cn=" + username,
                (AttributesMapper<String>) attrs -> (String) attrs
                        .get("cn")
                        .get());
    }

    public void create(final String username, final String password) {
        Name dn = LdapNameBuilder
                .newInstance()
                .add("ou", "people")
                .add("uid", username)
                .build();
        DirContextAdapter context = new DirContextAdapter(dn);

        context.setAttributeValues("objectclass", new String[]{"top", "person", "organizationalPerson", "inetOrgPerson"});
        context.setAttributeValue("cn", username);
        context.setAttributeValue("uid", username);
        context.setAttributeValue("userPassword",  digestSHA(password));

        ldapTemplate.bind(context);
    }

    public void modify(final String username, final String password) {
        Name dn = LdapNameBuilder
                .newInstance()
                .add("ou", "people")
                .add("uid", username)

                .build();
        DirContextOperations context = ldapTemplate.lookupContext(dn);

        String a="champ";
        context.setAttributeValues("objectclass", new String[]{"top", "person", "organizationalPerson", "inetOrgPerson"});
        context.setAttributeValue("cn", username);
        context.setAttributeValue("uid", username);
        context.setAttributeValue("userPassword", digestSHA(password));

        context.getAttributes();
        System.out.println(context.getAttributes());


        ldapTemplate.modifyAttributes(context);

    }

    public String digestSHA(final String password) {



        LdapShaPasswordEncoder p = new LdapShaPasswordEncoder();

        String new_password;
        new_password = p.encodePassword(password, null);
        return "{SHA}" + new_password;
//            MessageDigest digest = MessageDigest.getInstance("SHA");
//            digest.update(password.getBytes());
//            base64 = Base64
//                    .getEncoder()
//                    .encodeToString(digest.digest());
//        } catch (NoSuchAlgorithmException e) {
//            throw new RuntimeException(e);
//        }
//        return "{SHA}" + base64;
    }
}
