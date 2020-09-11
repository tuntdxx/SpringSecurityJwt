package com.vnpt.iot.portal.exceptions;

/**
 * @author Trinh Dinh Tuan : Developer
 * @Email tuantdxyz@gmail.com
 * @Version 1.0.0 Sep 3, 2020
 */

//@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class PortalException extends RuntimeException {

	private static final long serialVersionUID = 8449482234962398199L;

	private Integer responseStatusCode;

	private String responseStatusMessage;

	public Integer getResponseStatusCode() {
		return responseStatusCode;
	}

	public void setResponseStatusCode(Integer responseStatusCode) {
		this.responseStatusCode = responseStatusCode;
	}

	public String getResponseStatusMessage() {
		return responseStatusMessage;
	}

	public void setResponseStatusMessage(String responseStatusMessage) {
		this.responseStatusMessage = responseStatusMessage;
	}

	public PortalException(Integer responseStatusCode, String responseStatusMessage) {
		super();
		this.responseStatusCode = responseStatusCode;
		this.responseStatusMessage = responseStatusMessage;
	}

}
