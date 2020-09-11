package com.vnpt.iot.portal.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Trinh Dinh Tuan : Developer
 * @Email tuantdxyz@gmail.com
 * @Version 1.0.0 Sep 1, 2020
 */

@Entity
@Table(name = "AUTH_PRIVILEGES")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthPrivilege {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID", columnDefinition = "VARCHAR(50) NOT NULL")
	private String id;

	@Column(name = "PRIVILEGES_GROUP", columnDefinition = "TEXT")
	private String group;

	@Column(name = "PRIVILEGES_KEY", columnDefinition = "TEXT")
	private String key;

	@Column(name = "PRIVILEGES_VALUE", columnDefinition = "INT")
	private Integer value;

}