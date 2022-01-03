package com.luzko.spring.controllers;

import com.luzko.spring.dao.SmartphoneDAO;
import com.luzko.spring.models.Smartphone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/phones")
public class PhoneController {
    private final SmartphoneDAO smartphoneDAO;

    @Autowired
    public PhoneController(SmartphoneDAO smartphoneDAO) {
        this.smartphoneDAO = smartphoneDAO;
    }

    @GetMapping()
    public String phones(Model model) {
        model.addAttribute("phones", smartphoneDAO.index());
        return "phones/list";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("smartphone", smartphoneDAO.show(id));
        return "phones/show";
    }

    @GetMapping("/new")
    public String newSmartphone(@ModelAttribute("smartphone") Smartphone smartphone) {
        return "phones/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("smartphone") @Valid Smartphone smartphone, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "phones/new";
        }
        smartphoneDAO.save(smartphone);
        return "redirect:/phones";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("smartphone", smartphoneDAO.show(id));
        return "phones/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("smartphone") @Valid Smartphone smartphone, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "phones/edit";
        }

        smartphoneDAO.update(id, smartphone);
        return "redirect:/phones";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        smartphoneDAO.delete(id);
        return "redirect:/phones";
    }
}