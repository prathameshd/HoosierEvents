package com.login.ldap.springboot.data.repository;

import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;

@Entry(base = "ou=people", objectClasses = { "organizationPerson","person", "inetOrgPerson", "top" })
public class User {

    private @Attribute (name="cn") String username;
    private @Attribute(name="uid") String username1;
    private @Attribute (name="userPassword") String password;

    public User(){}
    public User(String username,String password){
        this.username=username;
        this.username1=username;
        this.password=password;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return username;
    }



}
