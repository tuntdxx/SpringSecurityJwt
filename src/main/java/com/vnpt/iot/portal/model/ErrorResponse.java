package com.vnpt.iot.portal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
	private Integer responseStatusCode;
	private String responseStatusMessage;
}
