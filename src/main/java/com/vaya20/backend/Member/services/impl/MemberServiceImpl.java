package com.vaya20.backend.Member.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vaya20.backend.Member.domain.Role;
import com.vaya20.backend.Member.domain.Member;
import com.vaya20.backend.Member.repositories.MemberRepository;
import com.vaya20.backend.Member.services.MemberService;

@Service
public class MemberServiceImpl implements UserDetailsService, MemberService {
	
	
	private MemberRepository memberRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	 
	@Autowired
	public MemberServiceImpl(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	public MemberServiceImpl() {}

	public List<Member> list() {
		return memberRepository.findAllByOrderByEmail();
	}
	

	public void save(Member member) {
		/*
		 * Changes the password into Hash before saving into DB.
		 */
		member.setRole(Role.GUEST);
		member.setPassword(passwordEncoder.encode(member.getPassword()));
		memberRepository.save(member);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Member member = memberRepository.findByEmail(email);
		if(member != null) {
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			if (member.getRole()==Role.ADMIN) 
				authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			else if (member.getRole()==Role.USER) 
				authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
			else 
				authorities.add(new SimpleGrantedAuthority("ROLE_GUEST"));
			return new User(member.getEmail(),member.getPassword(),authorities); 
		} 
		throw new UsernameNotFoundException("Email '" + email + "' not found.");
	}
}
