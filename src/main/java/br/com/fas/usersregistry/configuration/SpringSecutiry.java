package br.com.fas.usersregistry.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import br.com.fas.usersregistry.filters.auth.AuthenticationFilter;
import br.com.fas.usersregistry.services.users.UserAuthService;

@Configuration
@EnableWebSecurity
public class SpringSecutiry extends WebSecurityConfigurerAdapter {
	
	@Autowired
	protected UserAuthService userAuthService;
	
	@Autowired
	protected BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Value("${security.jwt.expiration_time}")
	String expirationTime;

	@Value("${security.jwt.token_prefix}")
	String tokenPrefix;

	@Value("${security.jwt.authorization_header}")
	String authorizationHeader;

	@Value("${TOKEN_SECRET}")
	String tokenSecret;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		AuthenticationFilter authFilter = new AuthenticationFilter(
				authenticationManager(),
				expirationTime,
				tokenPrefix,
				authorizationHeader,
				tokenSecret
		);
		
		http.cors().and().csrf().disable()
			.addFilterAfter(authFilter, UsernamePasswordAuthenticationFilter.class)
			.authorizeRequests()
			.anyRequest()
			.permitAll()
			.and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userAuthService).passwordEncoder(bCryptPasswordEncoder);
    }

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;
	}

}
