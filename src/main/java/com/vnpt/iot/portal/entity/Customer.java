package com.vnpt.iot.portal.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Trinh Dinh Tuan : Developer
 * @Email tuantdxyz@gmail.com
 * @Version 1.0.0 Sep 4, 2020
 */

@Entity
@Table(name = "CUSTOMER")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends BaseEntity {

	private static final long serialVersionUID = 2409911788921911378L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID", columnDefinition = "VARCHAR(50) NOT NULL")
	private String id;

	@Column(name = "[FULLNAME]", columnDefinition = "VARCHAR(100) NOT NULL")
	private String fullname;

	@Column(name = "PHONE", columnDefinition = "VARCHAR(10) NOT NULL")
	private String phone;

	@Column(name = "ADDRESS", columnDefinition = "varchar(100)")
	private String address;

	@Column(name = "CONTRACT_TYPE", columnDefinition = "INT(1) NOT NULL")
	private Integer contractType;

	@Column(name = "EMAIL", columnDefinition = "VARCHAR(100) NOT NULL")
	private String email;

	@Column(name = "ACCOUNT_TYPE", columnDefinition = "INT(1) NOT NULL")
	private Integer accountType;

	@Column(name = "PROVINCE_CODE", columnDefinition = "varchar(100) NOT NULL")
	private String proviceCode;

	@Column(name = "CONTRACT_NUMBER", columnDefinition = "varchar(50)")
	private String contractNumber;

	@Column(name = "COMPANY_NAME", columnDefinition = "varchar(100)")
	private String companyName;

	@Column(name = "WEBSITE", columnDefinition = "varchar(100)")
	private String website;

	@Column(name = "STATUS", nullable = false, columnDefinition = "TINYINT(1)")
	private boolean status;

	@Column(name = "NOTE", columnDefinition = "varchar(100)")
	private String note;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private List<CustomerApikey> customerApikeys;
}