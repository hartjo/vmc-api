package com.restapi.hartjo.authentications;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.restapi.hartjo.constants.GlobalConstants;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;





/**
 * 
 * @author jan.llarenas
 * 
 * JWT authentication service
 * all logic in JWT is in this Class
 */

@Component
public class JwtTokenProvider {
	
	@Autowired
    private MyUserDetails myUserDetails;
	
	
	private long EXPIRATIONTIME 	= GlobalConstants.EXPIRATIONTIME;
    private String secret 			= GlobalConstants.SECRET_KEY;
    private String tokenPrefix 		= GlobalConstants.TOKEN_PREFIX;
    private Date now 				= new Date();
    
    
    
	
	public String generateJwt(String payloads) {
		
		String JWT = Jwts.builder().claim("payloads", payloads).setIssuedAt(now)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
		
	   return tokenPrefix + JWT;
	}

	

	public boolean validateToken(String token) {
		try {
			
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	    	return true;
		} catch (JwtException | IllegalArgumentException e) {
			return false;
		}
	}
	
	
	public String resolveToken(HttpServletRequest req) {
	    String bearerToken = req.getHeader("Authorization");
	    if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
	      return bearerToken.substring(7, bearerToken.length());
	    }
	    return null;
	}
	
	
	public Authentication getAuthentication(String token) {
		UserDetails userDetails = myUserDetails.loadUserByUsername("username");
	    return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	}
 
}
