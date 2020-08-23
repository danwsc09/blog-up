package com.dwsc.blogsproj.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.dwsc.blogsproj.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
		
	// add a reference to our security data source
    @Autowired
    private UserService userService;
	
   @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
   
   	//beans
 	//bcrypt bean definition
 	@Bean
 	public BCryptPasswordEncoder passwordEncoder() {
 		return new BCryptPasswordEncoder();
 	}

 	//authenticationProvider bean definition
 	@Bean
 	public DaoAuthenticationProvider authenticationProvider() {
 		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
 		auth.setUserDetailsService(userService); //set the custom user details service
 		auth.setPasswordEncoder(passwordEncoder()); //set the password encoder - bcrypt
 		return auth;
 	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/").permitAll()
				.antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
				.antMatchers("/myblogs/**").hasAuthority("ROLE_USER")
				.and()
			.formLogin()
				.loginPage("/showLoginPage")
				.loginProcessingUrl("/authenticateTheUser")
				.permitAll()
				.and()
			.logout() // by default -- send request to "/logout"
				.logoutSuccessUrl("/")
				.permitAll()
				.and()
			.exceptionHandling().accessDeniedPage("/access-denied")
				.and()
			.httpBasic();
	
	}
}
