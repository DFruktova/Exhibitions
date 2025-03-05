package com.example.exhibitions.controller;

import com.example.exhibitions.model.Exhibit;
import com.example.exhibitions.service.ExhibitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/exhibits")
public class ExhibitController {

    private final ExhibitService exhibitService;

    @Autowired
    public ExhibitController(ExhibitService exhibitService) {
        this.exhibitService = exhibitService;
    }

    @GetMapping
    public String getAllExhibits(Model model) {
        List<Exhibit> exhibits = exhibitService.getAllExhibits();
        model.addAttribute("exhibits", exhibits);
        return "exhibits/list";
    }

    @GetMapping("/{id}")
    public String getExhibitById(@PathVariable Integer id, Model model) {
        Optional<Exhibit> exhibit = exhibitService.getExhibitById(id);
        if (exhibit.isPresent()) {
            model.addAttribute("exhibit", exhibit.get());
            return "exhibits/details";
        } else {
            return "error";
        }
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("exhibit", new Exhibit());
        return "exhibits/create";
    }

    @PostMapping("/create")
    public String createExhibit(@ModelAttribute Exhibit exhibit) {
        exhibitService.createExhibit(exhibit);
        return "redirect:/exhibits";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Integer id, Model model) {
        Optional<Exhibit> exhibit = exhibitService.getExhibitById(id);
        if (exhibit.isPresent()) {
            model.addAttribute("exhibit", exhibit.get());
            return "exhibits/update";
        } else {
            return "error";
        }
    }

    @PostMapping("/update/{id}")
    public String updateExhibit(@PathVariable Integer id, @ModelAttribute Exhibit exhibit) {
        Exhibit updatedExhibit = exhibitService.updateExhibit(id, exhibit);
        if (updatedExhibit != null) {
            return "redirect:/exhibits";
        } else {
            return "error";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteExhibit(@PathVariable Integer id) {
        exhibitService.deleteExhibit(id);
        return "redirect:/exhibits";
    }

    @GetMapping("/filter")
    public String filterExhibits(@RequestParam Map<String, String> filters, Model model) {
        List<Exhibit> filteredExhibits = exhibitService.filterExhibits(filters);
        model.addAttribute("exhibits", filteredExhibits); // Передаем отфильтрованный список
        return "exhibits/list"; // Возвращаемся на страницу со списком (обновленным)
    }
}