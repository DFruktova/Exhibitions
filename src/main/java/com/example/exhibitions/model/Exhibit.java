package com.example.exhibitions.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "exhibits")
public class Exhibit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_exhibit")
    private Integer ID_exhibit;

    @Column(name = "ID_exhibition")
    private Integer ID_exhibition;

    @Column(name = "Name")
    private String Name;

    @Column(name = "Description")
    private String Description;

    @Column(name = "Author")
    private String Author;

    @Column(name = "Fabric")
    private String Fabric;

    @Column(name = "Create_year")
    private Integer Create_year;
}