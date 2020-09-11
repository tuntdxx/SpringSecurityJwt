package com.vnpt.iot.portal.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.vnpt.iot.portal.model.ErrorResponse;
import com.vnpt.iot.portal.utils.EnumValues;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Trinh Dinh Tuan : Developer
 * @Email tuantdxyz@gmail.com
 * @Version 1.0.0 Sep 4, 2020
 */
@ControllerAdvice
@Slf4j
public class ExceptionAdvice {

	@ExceptionHandler(PortalException.class)
	public ResponseEntity<ErrorResponse> mapException(PortalException ex) {
		log.warn("ControllerAdvice::ExceptionAdvice::PortalException :" + ex.getMessage());
		ErrorResponse err = new ErrorResponse(ex.getResponseStatusCode(), ex.getResponseStatusMessage());
		HttpStatus status = null;
		boolean checkStatus = getHttpStatusCode(ex.getResponseStatusCode());
		if (checkStatus) {
			status = HttpStatus.valueOf(ex.getResponseStatusCode());
		} else {
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<ErrorResponse>(err, status);
	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ErrorResponse> handleAccessDeniedException(Exception e, WebRequest request) {
		log.warn("ControllerAdvice::ExceptionAdvice::AccessDeniedException :" + e.getMessage());
		ErrorResponse err = new ErrorResponse();
		if (e.getMessage().toLowerCase().indexOf("access is denied") > -1) {
			err = new ErrorResponse(EnumValues.StatusProtocolEnum.STATUS_401.code, e.getMessage());
			return new ResponseEntity<ErrorResponse>(err, HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<ErrorResponse>(err, HttpStatus.INTERNAL_SERVER_ERROR);
	}

//	@ExceptionHandler(Exception.class)
//    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
//    public ResponseEntity<ErrorResponse> handleAllException(Exception ex, WebRequest request) {
//		ErrorResponse err = new ErrorResponse(ex.ge, ex.getResponseStatusMessage());
//		return new ResponseEntity<ErrorResponse>(err, HttpStatus.UNAUTHORIZED);
//    }

	private static boolean getHttpStatusCode(Integer statusCode) {
		for (HttpStatus c : HttpStatus.values()) {
			if (c.value() == statusCode.intValue()) {
				return true;
			}
		}
		return false;

	}
}
