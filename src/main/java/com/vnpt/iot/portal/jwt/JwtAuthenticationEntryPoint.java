package com.vnpt.iot.portal.jwt;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vnpt.iot.portal.model.ErrorResponse;
import com.vnpt.iot.portal.utils.ConstantDefine;
import com.vnpt.iot.portal.utils.EnumValues;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Trinh Dinh Tuan : Developer
 * @Email tuantdxyz@gmail.com
 * @Version 1.0.0 Sep 2, 2020
 */

@Component
@Slf4j
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

	private static final long serialVersionUID = 3593583381022565469L;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException {

		response.setStatus(EnumValues.StatusProtocolEnum.STATUS_403.code);
		response.setHeader(ConstantDefine.STATUS_CODE, EnumValues.StatusProtocolEnum.STATUS_403.code.toString());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);

		String message;
		if (authException.getCause() != null) {
			message = authException.getCause().getMessage();
		} else {
			message = authException.getMessage();
		}

		log.error(authException.getMessage());

		ErrorResponse err = new ErrorResponse(EnumValues.StatusProtocolEnum.STATUS_403.code, message);
		byte[] body = new ObjectMapper().writeValueAsBytes(err);
		response.getOutputStream().write(body);
	}
}
