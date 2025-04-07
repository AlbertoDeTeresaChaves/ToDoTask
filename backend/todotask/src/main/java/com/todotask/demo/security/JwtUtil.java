package com.todotask.demo.security;

import java.security.Key;
import java.util.Date;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

public class JwtUtil {

	private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

	public static String generateToken(String email) {
		return Jwts.builder().setSubject(email).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 86400000))
				.signWith(SECRET_KEY).compact();
	}

	 public static boolean validateToken(String token) {
	        try {
	            Claims claims = Jwts.parserBuilder()
	                    .setSigningKey(SECRET_KEY)
	                    .build()
	                    .parseClaimsJws(token)
	                    .getBody();

	            if (claims.getExpiration().before(new Date())) {
	                return false; 
	            }

	            return true;
	        } catch (ExpiredJwtException e) {
	            System.out.println("Token expirado");
	            return false;
	        } catch (SignatureException e) {
	            System.out.println("Firma inválida");
	            return false;
	        } catch (Exception e) {
	            System.out.println("Error al validar el token: " + e.getMessage());
	            return false;
	        }
	    }
	 public static String extractUsername(String token) {
		    return Jwts.parserBuilder()
		            .setSigningKey(SECRET_KEY)
		            .build()
		            .parseClaimsJws(token)
		            .getBody()
		            .getSubject();
		}
	
}
