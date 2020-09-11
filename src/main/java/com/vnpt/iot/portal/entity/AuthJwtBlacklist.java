package com.vnpt.iot.portal.entity;

import java.time.LocalDateTime;

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
@Table(name = "AUTH_JWT_BLACKLIST")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthJwtBlacklist {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID", columnDefinition = "VARCHAR(50) NOT NULL")
	private String id;

	@Column(name = "TOKEN", columnDefinition = "TEXT NOT NULL")
	private String token;

	@Column(name = "CREATED_DATE", updatable = false, columnDefinition = "DATETIME(6)")
	private LocalDateTime createdDate;

	@Column(name = "EXPIRED_DATE", updatable = false, columnDefinition = "DATETIME(6)")
	private LocalDateTime expiredDate;

}