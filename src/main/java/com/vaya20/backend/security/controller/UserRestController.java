package com.vaya20.backend.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vaya20.backend.Member.domain.Member;
import com.vaya20.backend.Member.services.MemberService;
import com.vaya20.backend.Picture.domain.Picture;
import com.vaya20.backend.Sermon.domain.SermonPost;
import com.vaya20.backend.security.JwtAuthenticationRequest;

@RestController
@RequestMapping("/api")
public class UserRestController {
	
    @Autowired
    private MemberService memberService;
    
    @RequestMapping(value = "signup", method = RequestMethod.POST)
    public ResponseEntity<?> signUp(@RequestBody JwtAuthenticationRequest authenticationRequest) {
        Member member = new Member();
        member.setEmail(authenticationRequest.getUsername());
        member.setUsername(authenticationRequest.getUsername());
        member.setName(authenticationRequest.getName());
        member.setPassword(authenticationRequest.getPassword());
        member.setDeleteYN('N');
        memberService.save(member);
		return ResponseEntity.ok(null);
    }

}
