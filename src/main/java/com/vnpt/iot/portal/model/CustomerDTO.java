package com.vnpt.iot.portal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Trinh Dinh Tuan : Developer
 * @Email tuantdxyz@gmail.com
 * @Version 1.0.0 Sep 10, 2020
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

	private String id;

	private String fullname;

	private String phone;

	private String address;

	private Integer contractType;

	private String email;

	private Integer accountType;

	private String proviceCode;

	private String contractNumber;

	private String companyName;

	private String website;

	private boolean status;

	private String note;

}