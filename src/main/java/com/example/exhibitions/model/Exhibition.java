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
@Entity
@Table(name = "exhibitions")
public class Exhibition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_exhibition")
    private int ID_exhibition;

    @Column(name = "Name")
    private String Name;

    @Column(name = "Description")
    private String Description;

    @Column(name = "Start_date")
    private LocalDate Start_date;

    @Column(name = "End_date")
    private LocalDate End_date;

    @Column(name = "Theme")
    private String Theme;

    @Column(name = "Price")
    private BigDecimal Price;
}