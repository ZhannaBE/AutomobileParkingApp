
package com.example.parking.controller;

import com.example.parking.model.ModelEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {
    @GetMapping({"/","/index"})

    public String home(ModelEntity model, Principal principal){ model.addAttribute("username", principal.getName()); return "index"; }
    @GetMapping("/about") public String about(){ return "about"; }
}
