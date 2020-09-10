package br.com.fas.usersregistry.filters.auth;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

public class AuthorizationFilter extends BasicAuthenticationFilter {
	
	protected String tokenPrefix;
	protected String authorizationHeader;
	protected String tokenSecret;

	public AuthorizationFilter(
			AuthenticationManager authManager,
			String tokenPrefix,
			String authorizationHeader,
			String tokenSecret
	) {
        super(authManager);
        this.tokenPrefix = tokenPrefix;
        this.authorizationHeader = authorizationHeader;
        this.tokenSecret = tokenSecret;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(authorizationHeader);

        if (header == null || !header.startsWith(tokenPrefix)) {
            chain.doFilter(req, res);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }

    protected UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(authorizationHeader);
        
        if (Objects.isNull(token)) return null;
        
        String user = JWT.require(Algorithm.HMAC512(tokenSecret.getBytes()))
                .build()
                .verify(token.replace(tokenPrefix, ""))
                .getSubject();
        
        return Objects.nonNull(user)
        	? new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>())
        	: null;
    }
    
}
