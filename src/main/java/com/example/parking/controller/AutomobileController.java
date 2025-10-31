
package com.example.parking.controller;

import com.example.parking.model.Automobile;
import com.example.parking.model.ModelEntity;
import com.example.parking.model.User;
import com.example.parking.repository.UserRepository;
import com.example.parking.service.AutomobileService;
import com.example.parking.service.ModelService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/autos")
public class AutomobileController {
    private final AutomobileService autoService;
    private final ModelService modelService;
    private final UserRepository userRepository;
    public AutomobileController(AutomobileService autoService, ModelService modelService, UserRepository userRepository){
        this.autoService = autoService; this.modelService = modelService; this.userRepository = userRepository;
    }

    @GetMapping
    public String list(Model model, @AuthenticationPrincipal UserDetails ud){
        User current = userRepository.findByUsername(ud.getUsername()).orElse(null);
        boolean isAdmin = ud.getAuthorities().stream().anyMatch(a->a.getAuthority().equals("ROLE_ADMIN"));
        model.addAttribute("autos", isAdmin ? autoService.all() : autoService.byOwner(current));
        return "automobiles";
    }

    @GetMapping("/add")
    public String addForm(Model model){ model.addAttribute("models", modelService.all()); return "add-automobile"; }

    @PostMapping("/add")
    public String add(@RequestParam Long modelId, @AuthenticationPrincipal UserDetails ud){
        User owner = userRepository.findByUsername(ud.getUsername()).orElseThrow();
        ModelEntity me = modelService.find(modelId);
        Automobile a = new Automobile(); a.setModel(me); a.setOwner(owner);
        autoService.save(a);
        return "redirect:/autos";
    }

    @GetMapping("/delete/{id}") public String delete(@PathVariable Long id){ autoService.delete(id); return "redirect:/autos"; }
}
