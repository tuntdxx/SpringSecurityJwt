package com.vnpt.iot.portal.service;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;

import com.vnpt.iot.portal.entity.Customer;
import com.vnpt.iot.portal.model.RequestModel;
import com.vnpt.iot.portal.model.ResponseModel;

/**
 * @author Trinh Dinh Tuan : Developer
 * @Email tuantdxyz@gmail.com
 * @Version 1.0.0 Sep 7, 2020
 */

public interface CustomerService {
	public ResponseModel createCustomer(RequestModel customerRequest);

	public ResponseModel viewCustomer(Integer page, Integer limit, String sort, String params, Integer contractType);

	public List<Customer> getAll();

	public List<Customer> getAll(Example<Customer> example, Pageable pageable);

	public int totalCustomer();

	public Customer findById(String id);

	public ResponseModel showCustomer(String id);

	public void editCustomer(RequestModel customerRequest);

	public void deleteCustomer(String id);
}
