package com.vnpt.iot.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vnpt.iot.portal.service.ProvinceService;

/**
 * @author Trinh Dinh Tuan : Developer
 * @Email tuantdxyz@gmail.com
 * @Version 1.0.0 Sep 8, 2020
 */

@RestController
@RequestMapping("api/province")
public class ProvinceController {

	@Autowired
	ProvinceService provinceService;

	@GetMapping
	@Secured("ROLE_ADMIN")
	public void createProvince() {
		provinceService.createProvince();
	}
}
