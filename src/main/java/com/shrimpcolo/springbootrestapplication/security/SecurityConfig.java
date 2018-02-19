package com.shrimpcolo.springbootrestapplication.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    @Override
//    protected void configure(HttpSecurity httpSecurity) throws Exception {
//        System.out.println("TANHQ===> httpSecurity: " + httpSecurity);
//
//        super.configure(httpSecurity);
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        System.out.println("TANHQ===> auth: " + auth);
//        auth.inMemoryAuthentication()
//                .withUser("user1").password("{noop}secret1").roles("USER");
//    }



    // Authentication: User -> Roles
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        System.out.println("TANHQ===> auth: " + auth);
        auth.inMemoryAuthentication()
                .withUser("user1").password("{noop}secret1").roles("USER")
                .and()
                .withUser("admin1").password("{noop}secret1").roles("USER", "ADMIN");
    }

    // Authorization: Role -> Access
    // Survey -> User
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        System.out.println("TANHQ===> httpSecurity: " + httpSecurity);
        httpSecurity.httpBasic().and()
                .authorizeRequests().antMatchers("/surveys/**").hasRole("USER")
                .antMatchers("/users/**").hasRole("USER")
                .antMatchers("/**").hasRole("ADMIN")
                .and().csrf().disable()
                .headers().frameOptions().disable();
    }

}
