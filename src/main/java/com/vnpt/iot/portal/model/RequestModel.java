package com.vnpt.iot.portal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Trinh Dinh Tuan : Developer
 * @Email tuantdxyz@gmail.com
 * @Version 1.0.0 Sep 1, 2020
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestModel {

	protected String from;
	protected Object content;

}
