
package com.example.parking.controller;

import com.example.parking.model.ModelEntity;
import com.example.parking.service.ModelService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/models")
public class ModelController {
    private final ModelService modelService;
    public ModelController(ModelService modelService){ this.modelService = modelService; }

    @GetMapping
    public String list(Model model){
        model.addAttribute("models", modelService.all());
        return "models";
    }

    @GetMapping("/add")
    public String addForm(Model model){ model.addAttribute("modelEntity", new ModelEntity()); return "edit-model"; }

    @PostMapping("/save")
    public String save(ModelEntity me){ modelService.save(me); return "redirect:/models"; }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model){
        model.addAttribute("modelEntity", modelService.find(id));
        return "edit-model";
    }

    @GetMapping("/delete/{id}") public String delete(@PathVariable Long id){ modelService.delete(id); return "redirect:/models"; }
}
