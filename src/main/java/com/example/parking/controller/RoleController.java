
package com.example.parking.controller;

import com.example.parking.service.UserService;
import com.example.parking.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/roles")
public class RoleController {
    private final UserService userService;
    private final UserRepository userRepository;
    public RoleController(UserService userService, UserRepository userRepository){
        this.userService = userService; this.userRepository = userRepository;
    }

    @GetMapping
    public String list(Model model){
        model.addAttribute("users", userService.all());
        return "roles";
    }

    @PostMapping("/addRole")
    public String addRole(@RequestParam Long userId, @RequestParam String role){
        userService.addRoleToUser(userId, role);
        return "redirect:/roles";
    }
}
