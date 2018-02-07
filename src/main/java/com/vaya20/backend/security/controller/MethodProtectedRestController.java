package com.vaya20.backend.security.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vaya20.backend.Member.domain.Member;
import com.vaya20.backend.Member.services.MemberService;


@RestController
@RequestMapping("/api")
public class MethodProtectedRestController {
	
	@Autowired
	private MemberService memberService;
	
	MethodProtectedRestController(MemberService memberService) {
		this.memberService = memberService;
	}

    /**
     * This is an example of some different kinds of granular restriction for endpoints. You can use the built-in SPEL expressions
     * in @PreAuthorize such as 'hasRole()' to determine if a user has access. Remember that the hasRole expression assumes a
     * 'ROLE_' prefix on all role names. So 'ADMIN' here is actually stored as 'ROLE_ADMIN' in database!
     **/
    @RequestMapping(value="protected",method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getProtected() {
        return ResponseEntity.ok("Greetings from admin protected method!");
    }
    
    @RequestMapping(value="admin",method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAdmin(Principal principal) {
    	System.out.println("Admin Testing: " + principal.getName());
        return new ResponseEntity<Boolean> (true,HttpStatus.OK);
    }
    
    @RequestMapping(value="self/{id}",method = RequestMethod.GET)
    public ResponseEntity<?> checkIfSelf(@PathVariable("id") long id, Principal principal) {
    	Member member = memberService.findOne(id);
    	boolean validity = false;
    	System.out.println("For self test: member.getEmail()= " + member.getEmail() + "  principal.getName()= " + principal.getName());
    	if(principal.getName().equals(member.getEmail())){
    		validity = true;
    	}
    	System.out.println("Checks for Validity: " + validity);
    	return new ResponseEntity<Boolean> (validity,HttpStatus.OK);
    }
}