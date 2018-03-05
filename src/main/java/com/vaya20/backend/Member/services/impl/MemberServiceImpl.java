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

import com.vaya20.backend.Member.domain.Member;
import com.vaya20.backend.Member.domain.Role;
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
		return memberRepository.findEmailByIdAndDeleteYN();
	}
	

	public void save(Member member) {
		/*
		 * Changes the password into Hash before saving into DB.
		 */
		if(member.getRole()==null) {
			member.setRole(Role.ROLE_GUEST);
		}
		member.setPassword(passwordEncoder.encode(member.getPassword()));
		memberRepository.save(member);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Member member = memberRepository.findByEmail(email);
		if(member != null) {
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			if (member.getRole()==Role.ROLE_ADMIN) 
				authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			else if (member.getRole()==Role.ROLE_MEMBER) 
				authorities.add(new SimpleGrantedAuthority("ROLE_MEMBER"));
			else if (member.getRole()==Role.ROLE_USER) 
				authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
			else 
				authorities.add(new SimpleGrantedAuthority("ROLE_GUEST"));
			return new User(member.getEmail(),member.getPassword(),authorities); 
		} 
		throw new UsernameNotFoundException("Email '" + email + "' not found.");
	}

	@Override
	public Member findOne(long id) {
		return memberRepository.findOne(id);
	}

	@Override
	public void delete(long id) {
		Member member = memberRepository.findOne(id);
		member.setDeleteYN('Y');
		save(member);
	}

	@Override
	public Integer findIdByUsername(String username) {
		Integer id = memberRepository.findIdByEmail(username);
		System.out.println(id);
		return id;
	}
}
