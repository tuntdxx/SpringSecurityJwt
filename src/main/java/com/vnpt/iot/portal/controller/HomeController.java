package com.vnpt.iot.portal.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Trinh Dinh Tuan : Developer
 * @Email tuantdxyz@gmail.com
 * @Version 1.0.0 Sep 2, 2020
 */

@RestController
@RequestMapping("home")
public class HomeController {

	/**
	 * check role from JWT
	 * 
	 * @return
	 */
	@GetMapping("/viewrole")
	@Secured("ROLE_ADMIN")
	public String wellcome() {
		return "Hello ROLE_ADMIN";
	}

	/**
	 * check not role from JWT
	 * 
	 * @return
	 */
	@GetMapping("/norole")
	@Secured("ROLE_NO")
	public String login() {
		return "Hello login";
	}
}
