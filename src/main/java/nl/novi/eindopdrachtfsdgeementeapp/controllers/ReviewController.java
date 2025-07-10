package nl.novi.eindopdrachtfsdgeementeapp.controllers;

import jakarta.validation.Valid;
import nl.novi.eindopdrachtfsdgeementeapp.dtos.ReviewDto;
import nl.novi.eindopdrachtfsdgeementeapp.dtos.ReviewInputDto;
import nl.novi.eindopdrachtfsdgeementeapp.services.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/proposals/{proposalId}/reviews")
    public ResponseEntity<List<ReviewDto>> getAllReviewsForProposal(@PathVariable int proposalId) {
        List<ReviewDto> reviews = reviewService.getAllReviews(proposalId);
        return ResponseEntity.ok().body(reviews);
    }

    @PostMapping("/proposals/{proposalId}/reviews")
    public ResponseEntity<ReviewDto> createReview(@PathVariable int proposalId, @RequestBody @Valid ReviewInputDto reviewInputDto) {
        ReviewDto newReview = reviewService.createReview(proposalId, reviewInputDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newReview);
    }

    @DeleteMapping("/reviews/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable int id) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }
}
