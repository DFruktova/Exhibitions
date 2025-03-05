package com.example.exhibitions.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity //  Mark the class as an entity
@Table(name = "visitors")
public class Visitor {

    @Id // Mark the field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configure auto-generation
    @Column(name = "ID_visitor")
    private int ID_visitor;

    @Column(name = "Name")
    private String Name;

    @Column(name = "Surname")
    private String Surname;

    @Column(name = "Email")
    private String Email;

    @Column(name = "Phone_number")
    private String Phone_number;

}