package com.vnpt.iot.portal.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Trinh Dinh Tuan : Developer
 * @Email tuantdxyz@gmail.com
 * @Version 1.0.0 Sep 7, 2020
 */

@Entity
@Table(name = "PROVINCE")
@Data
@EqualsAndHashCode(callSuper = true)
//@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Province extends BaseEntity {

	private static final long serialVersionUID = 5823277528674324212L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "ID", columnDefinition = "VARCHAR(50) NOT NULL")
	private String id;

	@Column(name = "PROVICE_CODE", columnDefinition = "VARCHAR(4) NOT NULL")
	private String proviceCode;

	@Column(name = "PROVICE_NAME", columnDefinition = "NVARCHAR(100) NOT NULL")
	private String proviceName;

	@Column(name = "STATUS", nullable = true, columnDefinition = "TINYINT(1)")
	private boolean status;

	@Column(name = "NOTE", columnDefinition = "varchar(100)")
	private String note;

}