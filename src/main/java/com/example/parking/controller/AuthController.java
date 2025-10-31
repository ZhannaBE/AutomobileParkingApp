
package com.example.parking.controller;

import com.example.parking.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {
    private final UserService userService;
    public AuthController(UserService userService){ this.userService = userService; }

    @GetMapping("/login") public String login(){ return "login"; }

    @GetMapping("/register") public String regForm(){ return "register"; }

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password, Model model){
        try{
            userService.register(username, password);
            return "redirect:/login?registered";
        }catch(Exception ex){
            model.addAttribute("error", ex.getMessage());
            return "register";
        }
    }
}
