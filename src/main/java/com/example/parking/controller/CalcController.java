
package com.example.parking.controller;

import com.example.parking.service.AutomobileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/calc")
public class CalcController {
    private final AutomobileService autoService;
    public CalcController(AutomobileService autoService){ this.autoService = autoService; }

    @GetMapping
    public String calc(Model model){
        double sum = autoService.all().stream().mapToDouble(a-> a.getModel()!=null && a.getModel().getCost()!=null ? a.getModel().getCost() : 0.0).sum();
        model.addAttribute("sum", sum);
        model.addAttribute("count", autoService.all().size());
        return "calc";
    }
}
