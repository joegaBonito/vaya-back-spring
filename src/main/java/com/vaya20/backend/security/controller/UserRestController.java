package com.vaya20.backend.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vaya20.backend.Member.domain.Member;
import com.vaya20.backend.Member.services.MemberService;
import com.vaya20.backend.security.JwtAuthenticationRequest;

@RestController
public class UserRestController {
	
    @Autowired
    private MemberService memberService;
    
    @RequestMapping(value = "signup", method = RequestMethod.POST)
    public ResponseEntity<?> signUp(@RequestBody JwtAuthenticationRequest authenticationRequest) {
        Member member = new Member();
        member.setEmail(authenticationRequest.getUsername());
        member.setUsername(authenticationRequest.getUsername());
        member.setPassword(authenticationRequest.getPassword());
        member.setDeleteYN('N');
        memberService.save(member);
		return ResponseEntity.ok(null);
    }
}
