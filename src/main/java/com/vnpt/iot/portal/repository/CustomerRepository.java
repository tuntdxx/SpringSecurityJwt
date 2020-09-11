package com.vnpt.iot.portal.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.vnpt.iot.portal.entity.Customer;

/**
 * @author Trinh Dinh Tuan : Developer
 * @Email tuantdxyz@gmail.com
 * @Version 1.0.0 Sep 7, 2020
 */

public interface CustomerRepository extends JpaRepository<Customer, String> {

	List<Customer> findByFullnameOrEmailOrPhoneAndContractType(String fullname, String email, String phone,
			Integer contractType, Pageable pageable);

	int countByFullnameOrEmailOrPhoneAndContractType(String fullname, String email, String phone, Integer contractType);

}
