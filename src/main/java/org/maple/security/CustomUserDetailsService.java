package org.maple.security;

import org.maple.mapper.MemberMapper;

import org.maple.security.domain.CustomUser;
import org.maple.domain.MemberVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import lombok.extern.log4j.Log4j;

@Log4j
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private MemberMapper memberMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		MemberVO vo = memberMapper.read(username);

		return new CustomUser(vo);
	}
}
