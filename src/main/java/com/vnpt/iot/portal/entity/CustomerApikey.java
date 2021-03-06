package com.vnpt.iot.portal.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Trinh Dinh Tuan : Developer
 * @Email tuantdxyz@gmail.com
 * @Version 1.0.0 Sep 9, 2020
 */

@Entity
@Table(name = "CUSTOMER_APIKEY")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class CustomerApikey extends BaseEntity {

	private static final long serialVersionUID = -5986261884308918647L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID", columnDefinition = "VARCHAR(50) NOT NULL")
	private String id;

	@Column(name = "APIKEY", columnDefinition = "VARCHAR(100) NOT NULL")
	private String apci;

	@Column(name = "STATUS", nullable = false, columnDefinition = "TINYINT(1)")
	private boolean status;

	@Column(name = "NOTE", columnDefinition = "varchar(100)")
	private String note;
	
	@ManyToOne
	@JsonIgnore //TODO
	private Customer customer;
}