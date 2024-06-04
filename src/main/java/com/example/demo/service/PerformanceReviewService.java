package com.example.demo.service;

import com.example.demo.entity.Employee;
import com.example.demo.entity.PerformanceReview;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.PerformanceReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PerformanceReviewService {

    @Autowired
    private PerformanceReviewRepository performanceReviewRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public ResponseEntity<PerformanceReview> createPerformanceReview(PerformanceReview performanceReview) {
        Optional<Employee> employeeOpt = employeeRepository.findById(performanceReview.getEmployee().getId());
        Optional<Employee> reviewerOpt = employeeRepository.findById(performanceReview.getReviewer().getId());

        if (employeeOpt.isPresent() && reviewerOpt.isPresent()) {
            performanceReview.setEmployee(employeeOpt.get());
            performanceReview.setReviewer(reviewerOpt.get());

            PerformanceReview savedReview = performanceReviewRepository.save(performanceReview);
            return new ResponseEntity<>(savedReview, HttpStatus.CREATED);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<List<PerformanceReview>> getAllPerformanceReviews() {
        List<PerformanceReview> reviews = performanceReviewRepository.findAll();
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    public ResponseEntity<PerformanceReview> getPerformanceReviewById(Long reviewID) {
        Optional<PerformanceReview> review = performanceReviewRepository.findById(reviewID);
        return review.map(performanceReview -> new ResponseEntity<>(performanceReview, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<PerformanceReview> updatePerformanceReview(Long reviewID, PerformanceReview performanceReviewDetails) {
        Optional<PerformanceReview> optionalReview = performanceReviewRepository.findById(reviewID);

        if (optionalReview.isPresent()) {
            PerformanceReview performanceReview = optionalReview.get();

            Optional<Employee> employeeOpt = employeeRepository.findById(performanceReviewDetails.getEmployee().getId());
            Optional<Employee> reviewerOpt = employeeRepository.findById(performanceReviewDetails.getReviewer().getId());

            if (employeeOpt.isPresent() && reviewerOpt.isPresent()) {
                performanceReview.setEmployee(employeeOpt.get());
                performanceReview.setReviewer(reviewerOpt.get());
                performanceReview.setReviewDate(performanceReviewDetails.getReviewDate());
                performanceReview.setScore(performanceReviewDetails.getScore());
                performanceReview.setComments(performanceReviewDetails.getComments());

                PerformanceReview updatedReview = performanceReviewRepository.save(performanceReview);
                return new ResponseEntity<>(updatedReview, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Void> deletePerformanceReview(Long reviewID) {
        Optional<PerformanceReview> optionalReview = performanceReviewRepository.findById(reviewID);

        if (optionalReview.isPresent()) {
            performanceReviewRepository.delete(optionalReview.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
