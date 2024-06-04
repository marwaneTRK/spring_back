package com.example.demo.entity;

import jakarta.persistence.*;
import com.example.demo.enums.StatusEnum.Status;
import lombok.Data;

import java.time.LocalDate;
@Data
@Entity
@Table
public class Tache {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    @Enumerated(EnumType.ORDINAL)
    private Status status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id")
    private Employee employee;


}
