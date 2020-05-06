package chains.restaurant.application.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, proxyTargetClass = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/resources/**", "/registration", "/registrationOwner", "/homeNotLogged").permitAll()
				.antMatchers("/h2-console/**", "/admin/**", "/owner/**").hasRole("ADMIN").antMatchers("/owner/**")
				.hasRole("OWNER").anyRequest().authenticated().and().formLogin().loginPage("/login")
				.defaultSuccessUrl("/welcome", true).permitAll().and().logout().permitAll();

		http.csrf().disable();
		http.headers().frameOptions().disable();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user999").password(bCryptPasswordEncoder().encode("999999999"))
				.roles("USER").and().withUser("owner111").password(bCryptPasswordEncoder().encode("111111111"))
				.roles("USER", "OWNER").and().withUser("admin000").password(bCryptPasswordEncoder().encode("000000000"))
				.roles("USER", "OWNER", "ADMIN");
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}
}