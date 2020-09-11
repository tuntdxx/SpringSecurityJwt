package com.vnpt.iot.portal.entity;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.vnpt.iot.portal.utils.EnumValues.UserStatusEnum;
import com.vnpt.iot.portal.utils.EnumValues.UserTypeEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Trinh Dinh Tuan : Developer
 * @Email tuantdxyz@gmail.com
 * @Version 1.0.0 Sep 1, 2020
 */

@Entity
@Table(name = "AUTH_USER")
@Data
@EqualsAndHashCode(callSuper = true)
//@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AuthUser extends BaseEntity {

	private static final long serialVersionUID = 910745418602770130L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID", columnDefinition = "VARCHAR(50) NOT NULL")
	private String id;

	@Column(name = "EMAIL", columnDefinition = "TEXT NOT NULL")
	private String email;

	@Column(name = "PHONE", columnDefinition = "VARCHAR(15) NOT NULL")
	private String phone;

	@Column(name = "PASSWORD", columnDefinition = "VARCHAR(100) NOT NULL")
	private String password;

	@Column(name = "FULLNAME", columnDefinition = "TEXT NOT NULL")
	private String fullname;

	@Column(name = "ADDRESS", columnDefinition = "TEXT")
	private String address;

	@Column(name = "LAST_ACTIVE", columnDefinition = "DATETIME(6)")
	private LocalDateTime lastActive;

	@Column(name = "STATUS", columnDefinition = "TEXT")
	@Enumerated(EnumType.STRING)
	private UserStatusEnum status;

	@Column(name = "TYPE", columnDefinition = "TEXT")
	@Enumerated(EnumType.STRING)
	private UserTypeEnum type;

	@Column(name = "VERIFY_TOKEN", columnDefinition = "TEXT")
	private String verifyToken;

	@Column(name = "VERIFY_TOKEN_CREATED_DATE", columnDefinition = "DATETIME(6)")
	private LocalDateTime verifyTokenCreatedDate;

	@Column(name = "UNIT_ID", columnDefinition = "VARCHAR(50) NOT NULL")
	private String unitId;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "AUTH_USER_ROLE", joinColumns = @JoinColumn(name = "USER_ID"), inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
	private Set<AuthRole> roles;

}