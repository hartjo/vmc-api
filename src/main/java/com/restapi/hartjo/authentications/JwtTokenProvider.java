package com.restapi.hartjo.authentications;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import com.restapi.hartjo.constants.GlobalConstants;


/**
 * 
 * @author jan.llarenas
 * 
 * JWT authentication service
 * all logic in JWT is in this Class
 */

@Component
public class JwtTokenProvider {
	
	private long EXPIRATIONTIME 	= GlobalConstants.EXPIRATIONTIME;
    private String secret 			= GlobalConstants.SECRET_KEY;
    private String tokenPrefix 		= GlobalConstants.TOKEN_PREFIX;
    private Date now 				= new Date();
    
	
	public String generateJwt(String payloads) {
		
		String JWT = 	Jwts.builder()
	             		.claim("payloads", payloads)
	             		.setIssuedAt(now)
	             		.setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
	             		.signWith(SignatureAlgorithm.HS512, secret)
	             		.compact();
		
	   return tokenPrefix + JWT;
	}

	

	public boolean validateToken(String token) {
		try {
			
	    	Claims data = Jwts.parser()
	                .setSigningKey(secret)
	                .parseClaimsJws(token)
	                .getBody();
	    	System.out.println(data);
	    	
	    	return true;
	    } catch (JwtException | IllegalArgumentException e) {
	    	System.out.println(e);
	    }
		return false;
	}
	
	
	public String resolveToken(HttpServletRequest req) {
	    String bearerToken = req.getHeader("Authorization");
	    if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
	      return bearerToken.substring(7, bearerToken.length());
	    }
	    return null;
	  }
 
}
