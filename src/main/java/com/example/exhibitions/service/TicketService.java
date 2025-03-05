package com.example.exhibitions.service;
import com.example.exhibitions.model.Ticket;
import com.example.exhibitions.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Optional<Ticket> getTicketById(int id) {
        return ticketRepository.findById(id);
    }

    public Ticket createTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public Ticket updateTicket(int id, Ticket ticket) {
        return ticketRepository.findById(id)
                .map(existingTicket -> {
                    ticket.setID_ticket(id);
                    return ticketRepository.save(ticket);
                })
                .orElse(null);
    }

    public void deleteTicket(int id) {
        ticketRepository.deleteById(id);
    }
}