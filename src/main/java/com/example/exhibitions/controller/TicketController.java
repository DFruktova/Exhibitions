package com.example.exhibitions.controller;
import com.example.exhibitions.model.Ticket;
import com.example.exhibitions.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    public String getAllTickets(Model model) {
        List<Ticket> tickets = ticketService.getAllTickets();
        model.addAttribute("tickets", tickets);
        return "tickets/list";
    }

    @GetMapping("/{id}")
    public String getTicketById(@PathVariable int id, Model model) {
        Optional<Ticket> ticket = ticketService.getTicketById(id);
        if (ticket.isPresent()) {
            model.addAttribute("ticket", ticket.get());
            return "tickets/details";
        } else {
            return "error";
        }
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("ticket", new Ticket());
        return "tickets/create";
    }

    @PostMapping("/create")
    public String createTicket(@ModelAttribute Ticket ticket) {
        ticketService.createTicket(ticket);
        return "redirect:/tickets";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable int id, Model model) {
        Optional<Ticket> ticket = ticketService.getTicketById(id);
        if (ticket.isPresent()) {
            model.addAttribute("ticket", ticket.get());
            return "tickets/update";  // Шаблон для обновления
        } else {
            return "error";
        }
    }

    @PostMapping("/update/{id}")
    public String updateTicket(@PathVariable int id, @ModelAttribute Ticket ticket) {
        Ticket updatedTicket = ticketService.updateTicket(id, ticket);
        if (updatedTicket != null) {
            return "redirect:/tickets";
        } else {
            return "error";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteTicket(@PathVariable int id) {
        ticketService.deleteTicket(id);
        return "redirect:/tickets";
    }
}