package com.shristy.web.projectmanagement.config;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private CustomLoginSuccessHandler sucessHandler;

	@Autowired
	private DataSource dataSource;

	@Value("${spring.queries.users-query}")
	private String usersQuery;

	@Value("${spring.queries.roles-query}")
	private String rolesQuery;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().usersByUsernameQuery(usersQuery).authoritiesByUsernameQuery(rolesQuery)
				.dataSource(dataSource).passwordEncoder(bCryptPasswordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
http.csrf().disable();
		http.authorizeRequests()
				// URLs matching for access rights
				.antMatchers("/").permitAll()
				.antMatchers("/login").permitAll()
                                .antMatchers("/registerUser").permitAll()
				.antMatchers("/register").permitAll()
                               .antMatchers("/user/saveStudent").permitAll()
				//.antMatchers("/user/teacher/**").access("hasAnyRole('ROLE_TEACHER', 'ROLE_ADMIN')")
                        .antMatchers("/user/**").access("hasAnyRole('ROLE_TEACHER', 'ROLE_ADMIN')")
                        .antMatchers("/student/**").access("hasAnyRole('ROLE_STUDENT', 'ROLE_ADMIN')")
                        .antMatchers("/user/courses/**").access("hasAnyRole('ROLE_STUDENT', 'ROLE_ADMIN')")
                       // .antMatchers("/user/student/**").access("hasAnyRole('ROLE_STUDENT', 'ROLE_ADMIN')")
                        .antMatchers("/uploadServlet").access("hasAnyRole('ROLE_TEACHER', 'ROLE_ADMIN','ROLE_STUDENT')")
				.antMatchers("/admin/**").access("hasAnyRole('ROLE_ADMIN')")
				.anyRequest().authenticated()
				.and()
				// form login
				.formLogin()
                                .loginProcessingUrl("/j_spring_security_check")
				.loginPage("/login")
				.failureUrl("/login?error=true")
				.successHandler(sucessHandler)
				.usernameParameter("username")
				.passwordParameter("password")
				.and()
				// logout
				.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/").and()
				.exceptionHandling()
				.accessDeniedPage("/access-denied");
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
	}

}
