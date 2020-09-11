package com.vnpt.iot.portal.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonSyntaxException;
import com.vnpt.iot.portal.exceptions.PortalException;
import com.vnpt.iot.portal.model.RequestModel;
import com.vnpt.iot.portal.model.ResponseModel;
import com.vnpt.iot.portal.service.CustomerService;
import com.vnpt.iot.portal.utils.ConstantDefine;
import com.vnpt.iot.portal.utils.EnumValues;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Trinh Dinh Tuan : Developer
 * @Email tuantdxyz@gmail.com
 * @Version 1.0.0 Sep 7, 2020
 */

@RestController
@RequestMapping("api/customer")
@Slf4j
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@Secured("ROLE_ADMIN")
	@GetMapping("view")
	public ResponseModel doViewAndSearch(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort,
			@RequestParam(name = "params", required = false) String params,
			@RequestParam(name = "contractType", required = false) Integer contractType, HttpServletResponse response) {
		ResponseModel customerResponse = null;
		try {
			log.info("View and Search Customer");
			customerResponse = customerService.viewCustomer(page, limit, sort, params, contractType);
			response.setHeader(ConstantDefine.STATUS_CODE, EnumValues.StatusProtocolEnum.STATUS_200.code.toString());
		} catch (Exception e) {
			log.error(e.getMessage());
			log.error("Cannot view customer list");
			response.setHeader(ConstantDefine.STATUS_CODE, EnumValues.StatusProtocolEnum.STATUS_604.code.toString());
			throw new PortalException(EnumValues.StatusProtocolEnum.STATUS_604.code,
					EnumValues.StatusProtocolEnum.STATUS_604.message);
		}

		return customerResponse;
	}

	@Secured("ROLE_ADMIN")
	@PostMapping("create")
	public ResponseModel doCreate(@RequestBody RequestModel customerRequest, HttpServletResponse response) {
		ResponseModel customerResponse = null;
		try {
			log.info("Create Customer");
			customerResponse = customerService.createCustomer(customerRequest);
			response.setHeader(ConstantDefine.STATUS_CODE, EnumValues.StatusProtocolEnum.STATUS_200.code.toString());
		} catch (NullPointerException e) {
			log.error(e.getMessage());
			log.error("Email not null, NullPointerException");
			response.setHeader(ConstantDefine.STATUS_CODE, EnumValues.StatusProtocolEnum.STATUS_602.code.toString());
			throw new PortalException(EnumValues.StatusProtocolEnum.STATUS_602.code,
					EnumValues.StatusProtocolEnum.STATUS_602.message);
		} catch (IllegalStateException | JsonSyntaxException e) {
			log.error(e.getMessage());
			log.error("Json parse or incorrect format error");
			response.setHeader(ConstantDefine.STATUS_CODE, EnumValues.StatusProtocolEnum.STATUS_603.code.toString());
			throw new PortalException(EnumValues.StatusProtocolEnum.STATUS_603.code,
					EnumValues.StatusProtocolEnum.STATUS_603.message);
		} catch (DataAccessException e) {
			log.error(e.getMessage());
			log.error("Cannot execute sql statement, syntax error");
			response.setHeader(ConstantDefine.STATUS_CODE, EnumValues.StatusProtocolEnum.STATUS_601.code.toString());
			throw new PortalException(EnumValues.StatusProtocolEnum.STATUS_601.code,
					EnumValues.StatusProtocolEnum.STATUS_601.message);
		}
		return customerResponse;

	}

	@Secured("ROLE_ADMIN")
	@GetMapping("show")
	public ResponseModel doShow(@RequestParam(name = "id", required = true) String id, HttpServletResponse response) {
		ResponseModel customerResponse = null;
		try {
			log.info("Show Customer detail");
			customerResponse = customerService.showCustomer(id);
			response.setHeader(ConstantDefine.STATUS_CODE, EnumValues.StatusProtocolEnum.STATUS_200.code.toString());
		} catch (Exception e) {
			log.error(e.getMessage());
			log.error("Cannot show customer detail");
			response.setHeader(ConstantDefine.STATUS_CODE, EnumValues.StatusProtocolEnum.STATUS_605.code.toString());
			throw new PortalException(EnumValues.StatusProtocolEnum.STATUS_605.code,
					EnumValues.StatusProtocolEnum.STATUS_605.message);
		}

		return customerResponse;
	}

	@Secured("ROLE_ADMIN")
	@PutMapping("edit")
	public ResponseModel doEdit(@RequestBody RequestModel customerRequest, HttpServletResponse response) {
		ResponseModel customerResponse = new ResponseModel();
		try {
			log.info("Edit Customer");
			customerService.editCustomer(customerRequest);
			customerResponse.setResponseStatusCode(EnumValues.StatusProtocolEnum.STATUS_200.code);
			customerResponse.setResponseStatusMessage(EnumValues.StatusProtocolEnum.STATUS_200.message);
			response.setHeader(ConstantDefine.STATUS_CODE, EnumValues.StatusProtocolEnum.STATUS_200.code.toString());
		} catch (Exception e) {
			log.error(e.getMessage());
			log.error("Cannot edit customer");
			response.setHeader(ConstantDefine.STATUS_CODE, EnumValues.StatusProtocolEnum.STATUS_606.code.toString());
			throw new PortalException(EnumValues.StatusProtocolEnum.STATUS_606.code,
					EnumValues.StatusProtocolEnum.STATUS_606.message);
		}

		return customerResponse;
	}

	@Secured("ROLE_ADMIN")
	@DeleteMapping("delete")
	public ResponseModel doDelete(@RequestParam(name = "id", required = true) String id, HttpServletResponse response) {
		ResponseModel customerResponse = new ResponseModel();
		try {
			log.info("Delete Customer");
			customerService.deleteCustomer(id);
			customerResponse.setResponseStatusCode(EnumValues.StatusProtocolEnum.STATUS_200.code);
			customerResponse.setResponseStatusMessage(EnumValues.StatusProtocolEnum.STATUS_200.message);
			response.setHeader(ConstantDefine.STATUS_CODE, EnumValues.StatusProtocolEnum.STATUS_200.code.toString());
		} catch (Exception e) {
			log.error(e.getMessage());
			log.error("Cannot delete customer");
			response.setHeader(ConstantDefine.STATUS_CODE, EnumValues.StatusProtocolEnum.STATUS_607.code.toString());
			throw new PortalException(EnumValues.StatusProtocolEnum.STATUS_607.code,
					EnumValues.StatusProtocolEnum.STATUS_607.message);
		}

		return customerResponse;
	}

}
