package com.example.demo.entity;

import jakarta.persistence.*;
import com.example.demo.enums.StatusEnum.Status;

import java.time.LocalDate;

@Entity
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

    public Tache() {
    }

    public Tache(Long id, String name, String description, LocalDate dateDebut, LocalDate dateFin, Status status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.status = status;
    }

   public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
