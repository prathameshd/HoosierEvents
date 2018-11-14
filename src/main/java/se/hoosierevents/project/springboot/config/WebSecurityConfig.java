package se.hoosierevents.project.springboot.springboot.config;

import se.hoosierevents.project.springboot.controller.FacebookConnectionSignup;
import se.hoosierevents.project.springboot.controller.FacebookSignInAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.LdapShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.mem.InMemoryUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInController;
import org.springframework.web.bind.annotation.RequestMapping;
import se.hoosierevents.project.springboot.controller.SuccessfulLoginHandler;

import java.util.Collections;

@Configuration
@EnableWebSecurity

public  class WebSecurityConfig  extends WebSecurityConfigurerAdapter {


@Autowired
private SuccessfulLoginHandler successHandler;


    @Autowired
    private ConnectionFactoryLocator connectionFactoryLocator;

    @Autowired
    private UsersConnectionRepository usersConnectionRepository;

    @Autowired
    private FacebookConnectionSignup facebookConnectionSignup;



    @Override
    @RequestMapping("/login")
    protected void configure(HttpSecurity http) throws Exception {
        http

                .authorizeRequests()
              //  .antMatchers("/resources/**").permitAll().anyRequest().permitAll()
                .antMatchers("/css/**", "/js/**", "/images/**").permitAll()
                .antMatchers("/signup/me").permitAll()
                .antMatchers("/forgotPass").permitAll()
                .antMatchers("//signin/**").permitAll()
                .antMatchers("/forgotPassword").permitAll()
                .antMatchers("/forgotPassword.html").permitAll()
                .antMatchers("/sendCode").permitAll()
                .antMatchers("**facebook.com**").permitAll()
                .and()
        .csrf().disable()
                .authorizeRequests()
                .antMatchers("/managers").hasRole("MANAGERS")
                .antMatchers("/employees").hasRole("EMPLOYEES")
                .anyRequest().fullyAuthenticated()
                .and()
                .formLogin().successHandler(successHandler)
                      .loginPage("/login").permitAll();

    }

    @Bean
    public ProviderSignInController providerSignInController() {
        ((InMemoryUsersConnectionRepository) usersConnectionRepository)
                .setConnectionSignUp(facebookConnectionSignup);

        return new ProviderSignInController(
                connectionFactoryLocator,
                usersConnectionRepository,
                new FacebookSignInAdapter());
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth

                .ldapAuthentication()
                .userDnPatterns("uid={0},ou=people")
                .userSearchBase("ou=people")
                .userSearchFilter("uid={0}")
                .groupSearchBase("ou=groups")
                .groupSearchFilter("uniqueMember={0}")
                .contextSource(contextSource())
                .passwordCompare()
                .passwordEncoder(new LdapShaPasswordEncoder())
                .passwordAttribute("userPassword")
        ;
    }

    @Bean
    public DefaultSpringSecurityContextSource contextSource() {
        return  new DefaultSpringSecurityContextSource(
                Collections.singletonList("ldap://localhost:12345"), "dc=loginComponent,dc=com");
    }


}
