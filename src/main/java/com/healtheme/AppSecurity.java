package com.healtheme;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class AppSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;

	private AuthenticationSuccessHandler authenticationSuccessHandler;

	@Autowired
	public AppSecurity(AuthenticationSuccessHandler authenticationSuccessHandler) {
		this.authenticationSuccessHandler = authenticationSuccessHandler;
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();

	}


	@Override
	protected void configure(AuthenticationManagerBuilder auth){
		
		try {
			auth.jdbcAuthentication().dataSource(dataSource);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Bean
	public JdbcUserDetailsManager jdbcUserDetailsManager(){
		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
		jdbcUserDetailsManager.setDataSource(dataSource);
		
		return jdbcUserDetailsManager;
	}

	@Override
	protected void configure(HttpSecurity http){

		String[] staticResources = { "/css/**", "/images/**", "/fonts/**", "/scripts/**", "/js/**"};

		try {
			http.authorizeRequests().antMatchers("/").permitAll().antMatchers(staticResources).permitAll()
					.antMatchers("/*signup").permitAll().antMatchers("/patient*").hasRole("PATIENT").antMatchers("/doctor*")
					.hasRole("DOCTOR").antMatchers("/lab*").hasRole("LAB").antMatchers("/admin*").hasRole("ADMIN")
					.anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll()
					.successHandler(authenticationSuccessHandler).and().logout().logoutUrl("/logout").permitAll()
					.logoutSuccessUrl("/login");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
