package com.example.exhibitions.controller;

import com.example.exhibitions.model.Exhibit;
import com.example.exhibitions.model.Exhibition;
import com.example.exhibitions.service.ExhibitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/exhibitions")
public class ExhibitionController {

    private final ExhibitionService exhibitionService;

    @Autowired
    public ExhibitionController(ExhibitionService exhibitionService) {
        this.exhibitionService = exhibitionService;
    }

    @GetMapping
    public String listExhibitions(Model model) {
        List<Exhibition> exhibitions = exhibitionService.getAllExhibitions();
        model.addAttribute("exhibitions", exhibitions);
        return "exhibitions/list"; // Thymeleaf template
    }

    @GetMapping("/{id}")
    public String getExhibition(@PathVariable("id") int id, Model model) {
        Optional<Exhibition> exhibition = exhibitionService.getExhibitionById(id);
        if (exhibition.isPresent()) {
            model.addAttribute("exhibition", exhibition.get());
            return "exhibitions/details";
        } else {
            return "error"; // Create an error template
        }
    }

    @GetMapping("/create")  // create instead of /add
    public String showCreateForm(Model model) {
        model.addAttribute("exhibition", new Exhibition()); // Пустой объект для формы
        return "exhibitions/create"; // Имя шаблона для формы создания
    }

    @PostMapping("/create") // Use /create instead of /add
    public String createExhibition(@ModelAttribute Exhibition exhibition) {  // @ModelAttribute связывает данные формы с объектом Exhibition
        exhibitionService.createExhibition(exhibition);
        return "redirect:/exhibitions"; // Перенаправление на список выставок
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        Optional<Exhibition> exhibition = exhibitionService.getExhibitionById(id);
        if (exhibition.isPresent()) {
            model.addAttribute("exhibition", exhibition.get());
            return "exhibitions/update"; // Шаблон для обновления
        } else {
            return "error";
        }
    }

    @PostMapping("/update/{id}")
    public String updateExhibition(@PathVariable("id") int id, @ModelAttribute Exhibition exhibition) {
        Exhibition updatedExhibition = exhibitionService.updateExhibition(id, exhibition);
        if (updatedExhibition != null) {
            return "redirect:/exhibitions";
        } else {
            return "error";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteExhibition(@PathVariable("id") int id) {
        exhibitionService.deleteExhibition(id);
        return "redirect:/exhibitions";
    }
}