package com.vnpt.iot.portal.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.vnpt.iot.portal.config.CustomUserDetails;
import com.vnpt.iot.portal.entity.AuthUser;
import com.vnpt.iot.portal.exceptions.PortalException;
import com.vnpt.iot.portal.jwt.JwtTokenProvider;
import com.vnpt.iot.portal.model.RequestModel;
import com.vnpt.iot.portal.model.ResponseModel;
import com.vnpt.iot.portal.service.impl.UserServiceImpl;
import com.vnpt.iot.portal.model.LoginRequestDTO;
import com.vnpt.iot.portal.utils.ConstantDefine;
import com.vnpt.iot.portal.utils.EnumValues;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Trinh Dinh Tuan : Developer
 * @Email tuantdxyz@gmail.com
 * @Version 1.0.0 Sep 2, 2020
 */

@RestController
@RequestMapping("api")
@Slf4j
public class LoginController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider tokenProvider;

	@Autowired
	private UserServiceImpl userService;

	/**
	 * gen JWT
	 * 
	 * @param loginRequest
	 * @param response
	 * @return
	 */
	@PostMapping("login")
	public ResponseModel authenticateUser(@RequestBody RequestModel userRequest, HttpServletResponse response) {
		String jwt = null;
		Gson gson = new Gson();
		ResponseModel userRespone = new ResponseModel();
		try {
			// get infor user
			LoginRequestDTO loginRequest = gson.fromJson(userRequest.getContent().toString(), LoginRequestDTO.class);
			// Authe user = email && pass from DB
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

			// Not Exception then set authentication = Security Context
			SecurityContextHolder.getContext().setAuthentication(authentication);

			// return JWT token
			jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
			log.info("JWT generated from user: " + jwt);
			response.setHeader(ConstantDefine.STATUS_CODE, EnumValues.StatusProtocolEnum.STATUS_200.code.toString());
			userRespone.setResponseStatusCode(EnumValues.StatusProtocolEnum.STATUS_200.code);
			userRespone.setResponseStatusMessage(EnumValues.StatusProtocolEnum.STATUS_200.message);
			userRespone.setContent("Bearer " + jwt);
		} catch (Exception e) {
			log.error(e.getMessage());
			response.setHeader(ConstantDefine.STATUS_CODE, EnumValues.StatusProtocolEnum.STATUS_400.code.toString());
			throw new PortalException(EnumValues.StatusProtocolEnum.STATUS_400.code,
					"Incorrect resource representation in content");
		}
		return userRespone;
	}

	@PostMapping("register")
	public ResponseModel registerUser(@RequestBody AuthUser userRequest, HttpServletResponse response) {
		ResponseModel userRespone = new ResponseModel();
		try {
			// save user
			userService.addUser(userRequest);
			log.info("Created User");
			response.setHeader(ConstantDefine.STATUS_CODE, EnumValues.StatusProtocolEnum.STATUS_201.code.toString());
			response.setStatus(EnumValues.StatusProtocolEnum.STATUS_201.code);
			userRespone.setResponseStatusCode(EnumValues.StatusProtocolEnum.STATUS_201.code);
			userRespone.setResponseStatusMessage(EnumValues.StatusProtocolEnum.STATUS_201.message);
		} catch (Exception e) {
			log.error(e.getMessage());
			response.setHeader(ConstantDefine.STATUS_CODE, EnumValues.StatusProtocolEnum.STATUS_400.code.toString());
			response.setStatus(EnumValues.StatusProtocolEnum.STATUS_400.code);
			throw new PortalException(EnumValues.StatusProtocolEnum.STATUS_400.code,
					"Incorrect resource representation in content");
		}
		return userRespone;
	}

}
