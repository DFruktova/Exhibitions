package com.example.exhibitions.controller;

import com.example.exhibitions.model.Visitor;
import com.example.exhibitions.service.VisitorService; // Предполагается, что у вас есть VisitorService
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/visitors")
public class VisitorController {

    private final VisitorService visitorService;

    @Autowired
    public VisitorController(VisitorService visitorService) {
        this.visitorService = visitorService;
    }

    @GetMapping
    public String getAllVisitors(Model model) {
        List<Visitor> visitors = visitorService.getAllVisitors();
        model.addAttribute("visitors", visitors);
        return "visitors/list"; // Thymeleaf template
    }

    @GetMapping("/{id}")
    public String getVisitorById(@PathVariable int id, Model model) {
        Optional<Visitor> visitor = visitorService.getVisitorById(id);
        if (visitor.isPresent()) {
            model.addAttribute("visitor", visitor.get());
            return "visitors/details";
        } else {
            return "error"; // Error template
        }
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("visitor", new Visitor());
        return "visitors/create";
    }

    @PostMapping("/create")
    public String createVisitor(@ModelAttribute Visitor visitor) {
        visitorService.createVisitor(visitor);
        return "redirect:/visitors";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable int id, Model model) {
        Optional<Visitor> visitor = visitorService.getVisitorById(id);
        if (visitor.isPresent()) {
            model.addAttribute("visitor", visitor.get());
            return "visitors/update";
        } else {
            return "error";
        }
    }

    @PostMapping("/update/{id}")
    public String updateVisitor(@PathVariable int id, @ModelAttribute Visitor visitor) {
        Visitor updatedVisitor = visitorService.updateVisitor(id, visitor);
        if (updatedVisitor != null) {
            return "redirect:/visitors";
        } else {
            return "error";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteVisitor(@PathVariable int id) {
        visitorService.deleteVisitor(id);
        return "redirect:/visitors";
    }

    @GetMapping("/search")
    public String searchVisitors(@RequestParam(required = false) String searchTerm, Model model) { // searchTerm как отдельный параметр
        List<Visitor> visitors = visitorService.searchVisitors(searchTerm);
        model.addAttribute("visitors", visitors);
        model.addAttribute("searchTerm", searchTerm); // Передаем searchTerm в шаблон для отображения
        return "visitors/list";
    }

}