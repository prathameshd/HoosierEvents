package com.login.ldap.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.LdapShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.web.bind.annotation.RequestMapping;
import com.login.ldap.springboot.controller.SuccessfulLoginHandler;

import javax.sql.DataSource;
import java.util.Collections;

@Configuration
@EnableWebSecurity

public  class WebSecurityConfig  extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

@Autowired
private SuccessfulLoginHandler successHandler;


    @Autowired
    private DataSource dataSource;


    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    @RequestMapping("/login")
    protected void configure(HttpSecurity http) throws Exception {
        http

                .authorizeRequests()
              //  .antMatchers("/resources/**").permitAll().anyRequest().permitAll()
                .antMatchers("/css/**", "/js/**", "/images/**").permitAll()
                .antMatchers("/signup/me").permitAll()
                .antMatchers("/login#").permitAll()
                .antMatchers("/forgotPassword/te").permitAll()
                .and()
        .csrf().disable()
                .authorizeRequests()
                .antMatchers("/managers").hasRole("MANAGERS")
                .antMatchers("/employees").hasRole("EMPLOYEES")
                .anyRequest().fullyAuthenticated()
                .and()
                .formLogin().successHandler(successHandler)
                      .loginPage("/login").permitAll()
                .usernameParameter("email")
                .passwordParameter("user_password");

    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .jdbcAuthentication()
                .dataSource(dataSource)

                .passwordEncoder(bCryptPasswordEncoder)
                .usersByUsernameQuery("select email, user_password enabled from user_details where email=?");

//                .ldapAuthentication()
//                .userDnPatterns("uid={0},ou=people")
//                .userSearchBase("ou=people")
//                .userSearchFilter("uid={0}")
//                .groupSearchBase("ou=groups")
//                .groupSearchFilter("uniqueMember={0}")
//                .contextSource(contextSource())
//                .passwordCompare()
//                .passwordEncoder(new LdapShaPasswordEncoder())
//                .passwordAttribute("userPassword")

    }

    @Bean
    public DefaultSpringSecurityContextSource contextSource() {
        return  new DefaultSpringSecurityContextSource(
                Collections.singletonList("ldap://localhost:12345"), "dc=loginComponent,dc=com");
    }


}
