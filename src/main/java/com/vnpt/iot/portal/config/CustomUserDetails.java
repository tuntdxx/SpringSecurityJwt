package com.vnpt.iot.portal.config;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.vnpt.iot.portal.entity.AuthUser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Trinh Dinh Tuan : Developer
 * @Email tuantdxyz@gmail.com
 * @Version 1.0.0 Sep 1, 2020
 */

@Data
@AllArgsConstructor
@Slf4j
public class CustomUserDetails implements UserDetails {

	private static final long serialVersionUID = -3346342628033411241L;

	AuthUser user;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> list = new HashSet<GrantedAuthority>();
		List<String> roles = user.getRoles().stream().map(role -> role.getPrivileges()).collect(Collectors.toList());
		log.info("List_Roles of email: " + user.getEmail() + " is: " + roles.toString());
		for (int i = 0; i < roles.size(); i++) {
			String[] part = roles.get(i).split(",");
			for (int j = 0; j < part.length; j++) {
				String role = part[j];
				list.add(new SimpleGrantedAuthority(role));
			}
		}
		log.info("Total_Roles: " + list.size());
		return list;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// set Username = Email
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
