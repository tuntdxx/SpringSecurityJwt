package com.vnpt.iot.portal.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vnpt.iot.portal.config.CustomUserDetails;
import com.vnpt.iot.portal.entity.AuthUser;
import com.vnpt.iot.portal.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Trinh Dinh Tuan : Developer
 * @Email tuantdxyz@gmail.com
 * @Version 1.0.0 Sep 1, 2020
 */

@Service
@Slf4j
public class UserServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// check User exist with username = email
		Optional<AuthUser> user = userRepository.findByEmail(username);
		if (!user.isPresent()) {
			throw new UsernameNotFoundException("API authen token not found user with username: " + username);
		}
		// set password with passwordEncoder
		user.get().setPassword(passwordEncoder.encode(user.get().getPassword()));
		log.info("login with User : " + username + " PasswordEncoder: " + user.get().getPassword());
		return new CustomUserDetails(user.get());
	}

	public UserDetails loadUserById(String id) {
		AuthUser user = userRepository.findById(id)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with id : " + id));
		return new CustomUserDetails(user);
	}

	@Transactional
	public AuthUser addUser(AuthUser user) {
		return userRepository.save(user);
	}
}
