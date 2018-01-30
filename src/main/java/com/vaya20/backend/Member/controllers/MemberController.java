package com.vaya20.backend.Member.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vaya20.backend.Member.domain.Member;
import com.vaya20.backend.Member.services.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	@RequestMapping("/members/list")
	public ResponseEntity<?> list(){
		List<Member> members = memberService.list();
		return new ResponseEntity<List<Member>>(members,HttpStatus.OK);
	}
	
	@RequestMapping("/view")
	public String view(Model model){
		return "members/view";
	}
	
	@RequestMapping("/createMember")
	public String createAuthor(Model model) {
		model.addAttribute("member", new Member());
		return "auth/createaccount";
	}
	
	@RequestMapping(value = "/processForm", method = RequestMethod.POST)
	public String processForm(@Valid @ModelAttribute("member") Member member, BindingResult theBindingResult) {
		if(theBindingResult.hasErrors()) {
			return "/auth/createaccount";
		}
		memberService.save(member);
		return "redirect:/";
	}
	
	/*
	 * Pre-processes 
	 */
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		//Removes leading and trailing whitespace.
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
}
