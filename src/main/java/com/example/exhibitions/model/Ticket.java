package com.example.exhibitions.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.*;


import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ticket")
    private int ID_ticket;

    @ManyToOne
    @JoinColumn(name = "ID_exhibition", insertable = false, updatable = false)
    private Exhibition exhibition;

    @ManyToOne
    @JoinColumn(name = "ID_visitor", insertable = false, updatable = false)
    private Visitor visitor;

    @Column(name = "ID_exhibition")
    private int ID_exhibition;

    @Column(name = "ID_visitor")
    private int ID_visitor;

    @Column(name = "Sale_date")
    private LocalDate Sale_date;

    @Column(name = "Price")
    private BigDecimal Price;

    @Column(name = "Count")
    private int Count;

    @Column(name = "Ticket_type")
    private String Ticket_type;
}