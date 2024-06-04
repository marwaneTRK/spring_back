package com.example.demo.repository;

import com.example.demo.entity.PerformanceReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerformanceReviewRepository  extends JpaRepository<PerformanceReview, Long> {

}
