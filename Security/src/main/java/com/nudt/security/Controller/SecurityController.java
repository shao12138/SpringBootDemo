package com.nudt.security.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SecurityController {
    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/content")
    public String content() {
        return "/content/2.html";
    }

//    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping("/admin")
    public String admin() {
        System.out.println("Admin方法");
        return "/admin/1.html";
    }
}