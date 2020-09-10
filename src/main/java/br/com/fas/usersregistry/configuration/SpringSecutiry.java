package br.com.fas.usersregistry.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import br.com.fas.usersregistry.auth.filters.AuthenticationFilter;
import br.com.fas.usersregistry.auth.filters.AuthorizationFilter;
import br.com.fas.usersregistry.services.users.UserAuthService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
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
		
		AuthorizationFilter authorizationFilter = new AuthorizationFilter(
				authenticationManager(),
				tokenPrefix,
				authorizationHeader,
				tokenSecret
		);
		
		http.cors().and().csrf().disable()
			.authorizeRequests()
			.antMatchers(HttpMethod.POST, "/users")
				.permitAll()
			.antMatchers("/v2/api-docs/**", "/swagger.json", "/swagger-ui.html", "/swagger-resources/**", "/webjars/**")
				.permitAll()
			.anyRequest()
				.authenticated()
			.and()
			.addFilter(authFilter)
			.addFilter(authorizationFilter)
			.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
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
