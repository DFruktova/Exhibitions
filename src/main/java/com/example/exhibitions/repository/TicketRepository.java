package com.example.exhibitions.repository;

import com.example.exhibitions.model.Exhibit;
import com.example.exhibitions.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Указывает, что это репозиторий
public interface TicketRepository extends JpaRepository<Ticket, Integer>{
}