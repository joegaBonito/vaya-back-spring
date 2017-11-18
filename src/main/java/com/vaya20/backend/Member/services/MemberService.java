package com.vaya20.backend.Member.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.vaya20.backend.Member.domain.Member;

public interface MemberService {
	public List<Member> list();
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
	public void save(Member member);
}
