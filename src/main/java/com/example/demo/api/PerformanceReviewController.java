package com.example.demo.api;

import com.example.demo.entity.PerformanceReview;
import com.example.demo.service.PerformanceReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/performance-reviews")
public class PerformanceReviewController {

    @Autowired
    private PerformanceReviewService performanceReviewService;

    @PostMapping("/add")
    public ResponseEntity<PerformanceReview> createPerformanceReview(@RequestBody PerformanceReview performanceReview) {
        return performanceReviewService.createPerformanceReview(performanceReview);
    }

    @GetMapping("/review")
    public String getAllPerformanceReviews() {
        return "testing stuff";
    }

    @GetMapping("/{id}")
    public ResponseEntity<PerformanceReview> getPerformanceReviewById(@PathVariable Long id) {
        return performanceReviewService.getPerformanceReviewById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PerformanceReview> updatePerformanceReview(@PathVariable Long id, @RequestBody PerformanceReview performanceReviewDetails) {
        return performanceReviewService.updatePerformanceReview(id, performanceReviewDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerformanceReview(@PathVariable Long id) {
        return performanceReviewService.deletePerformanceReview(id);
    }
}
