package com.vnpt.iot.portal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vnpt.iot.portal.entity.AuthUser;

/**
 * @author Trinh Dinh Tuan : Developer
 * @Email tuantdxyz@gmail.com
 * @Version 1.0.0 Sep 1, 2020
 */

public interface UserRepository extends JpaRepository<AuthUser, String> {

	public Optional<AuthUser> findByEmail(String email);

}
