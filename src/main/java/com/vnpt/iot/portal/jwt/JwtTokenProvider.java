package com.vnpt.iot.portal.jwt;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.vnpt.iot.portal.config.CustomUserDetails;
import com.vnpt.iot.portal.exceptions.PortalException;
import com.vnpt.iot.portal.utils.EnumValues;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Trinh Dinh Tuan : Developer
 * @Email tuantdxyz@gmail.com
 * @Version 1.0.0 Sep 1, 2020
 */

@Component
@Slf4j
public class JwtTokenProvider {
	// millisecond to minute, set 15 minus
//	private final long JWT_EXPIRATION = 15 * 60000;
	@Value("${spring.jwt.expiration}")
	private int JWT_EXPIRATION;

//	private final String JWT_SECRET = "VNPT_IOT_PORTAL";
	@Value("${spring.jwt.secret}")
	private String JWT_SECRET;

	// create JWT from User
	public String generateToken(CustomUserDetails userDetails) {
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);

		// get list roles
		List<String> listRoles = new ArrayList<String>();
		List<String> roles = userDetails.getUser().getRoles().stream().map(role -> role.getPrivileges())
				.collect(Collectors.toList());
		for (int i = 0; i < roles.size(); i++) {
			String[] part = roles.get(i).split(",");
			for (int j = 0; j < part.length; j++) {
				String role = part[j];
				listRoles.add(role);
			}
		}
		log.info("List roles gen with Jwt: " + listRoles.toString());

		// create JWT token
		return Jwts.builder().setSubject(userDetails.getUser().getId()).claim("roles", listRoles).setIssuedAt(now)
				.setExpiration(expiryDate).signWith(SignatureAlgorithm.HS512, JWT_SECRET).compact();
	}

	// get infor user from JWT Token
	public String getUserIdFromJWT(String token) {
		Claims claims = Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody();
		return claims.getSubject();
	}

	// check JWT Token
	public boolean validateToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
			return true;
		} catch (MalformedJwtException ex) {
			log.error("Invalid JWT token" + ex.getMessage());
//			throw new PortalException(EnumValues.StatusProtocolEnum.STATUS_608.code, EnumValues.StatusProtocolEnum.STATUS_608.message);
		} catch (ExpiredJwtException ex) {
			log.error("Expired JWT token" + ex.getMessage());
//			throw new PortalException(EnumValues.StatusProtocolEnum.STATUS_401.code, EnumValues.StatusProtocolEnum.STATUS_401.message);
		} catch (UnsupportedJwtException ex) {
			log.error("Unsupported JWT token" + ex.getMessage());
//			throw new PortalException(EnumValues.StatusProtocolEnum.STATUS_609.code, EnumValues.StatusProtocolEnum.STATUS_609.message);
		} catch (IllegalArgumentException ex) {
			log.error("JWT claims string is empty." + ex.getMessage());
//			throw new PortalException(EnumValues.StatusProtocolEnum.STATUS_610.code, EnumValues.StatusProtocolEnum.STATUS_610.message);
		}
		return false;
	}

}
