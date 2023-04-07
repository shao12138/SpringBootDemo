package com.nudt.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

    @RequestMapping("/")
    public String index() {
        return "/index.html";
    }

    @RequestMapping("/login")
    public String login() {
        return "/login.html";
    }

    @RequestMapping("/content")
    public String content() {
        return "/content/content.html";
    }

    @RequestMapping("/admin")
    public String admin() {
        return "/admin/admin.html";
    }
}
