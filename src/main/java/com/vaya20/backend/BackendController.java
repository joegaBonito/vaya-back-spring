package com.vaya20.backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BackendController {
	@RequestMapping("/")
    public String index(){
        System.out.println("Looking in the index controller.........");
        return "index.html";
    }
}
