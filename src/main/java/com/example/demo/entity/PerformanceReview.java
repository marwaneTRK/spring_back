package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "performance_review")
public class PerformanceReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewID;

    @ManyToOne
    @JoinColumn(name = "employeeID", nullable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "reviewerID", nullable = false)
    private Employee reviewer;

    @Temporal(TemporalType.DATE)
    private Date reviewDate;

    private int score;

    @Column(length = 1000)
    private String comments;
}
