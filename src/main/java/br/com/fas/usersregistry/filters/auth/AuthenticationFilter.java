package br.com.fas.usersregistry.filters.auth;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	protected AuthenticationManager authenticationManager;
	
	protected static ObjectMapper objMapper = new ObjectMapper();
	
	protected String expirantionTime;
	protected String tokenPrefix;
	protected String authorizationHeader;
	protected String tokenSecret;

	public AuthenticationFilter(
			AuthenticationManager authenticationManager,
			String expirationTime,
			String tokenPrefix,
			String authorizationHeader,
			String tokenSecret
	) {
        this.authenticationManager = authenticationManager;
        this.expirantionTime = expirationTime;
        this.tokenPrefix = tokenPrefix;
        this.authorizationHeader = authorizationHeader;
        this.tokenSecret = tokenSecret;
    }

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException {
		try {
			br.com.fas.usersregistry.entities.User creds = objMapper.readValue(req.getInputStream(), br.com.fas.usersregistry.entities.User.class);

			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					creds.getCpf(),
					creds.getPassword(),
					new ArrayList<>())
			);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		String token = JWT.create()
				.withSubject(((User) auth.getPrincipal()).getUsername())
				.withExpiresAt(getTomorrowDate())
				.sign(Algorithm.HMAC512(tokenSecret.getBytes()));

		res.addHeader(authorizationHeader, tokenPrefix + token);
	}
	
	protected Date getTomorrowDate() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DATE, cal.get(Calendar.DATE) + 1);
		return cal.getTime();
	}

}
