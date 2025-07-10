package nl.novi.eindopdrachtfsdgeementeapp.services;

import nl.novi.eindopdrachtfsdgeementeapp.dtos.ReviewDto;
import nl.novi.eindopdrachtfsdgeementeapp.dtos.ReviewInputDto;
import nl.novi.eindopdrachtfsdgeementeapp.exceptions.NotAuthorizedException;
import nl.novi.eindopdrachtfsdgeementeapp.exceptions.RecordNotFoundException;
import nl.novi.eindopdrachtfsdgeementeapp.mappers.ReviewMapper;
import nl.novi.eindopdrachtfsdgeementeapp.models.Proposal;
import nl.novi.eindopdrachtfsdgeementeapp.models.Review;
import nl.novi.eindopdrachtfsdgeementeapp.models.User;
import nl.novi.eindopdrachtfsdgeementeapp.repositories.ProposalRepository;
import nl.novi.eindopdrachtfsdgeementeapp.repositories.ReviewRepository;
import nl.novi.eindopdrachtfsdgeementeapp.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProposalRepository proposalRepository;
    private final UserRepository userRepository;

    public ReviewService(ReviewRepository reviewRepository, ProposalRepository proposalRepository, UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.proposalRepository = proposalRepository;
        this.userRepository = userRepository;
    }

    public List<ReviewDto> getAllReviews(int proposalId) {
        Proposal proposal = proposalRepository.findById(proposalId)
                .orElseThrow(() -> new RecordNotFoundException("No proposal found with id: " + proposalId));
        List<Review> reviews = reviewRepository.findByProposal(proposal);
        return reviews.stream().map(ReviewMapper::reviewToDto).toList();
    }

    public ReviewDto createReview(int proposalId, ReviewInputDto reviewInputDto) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Proposal proposal = proposalRepository.findById(proposalId)
                .orElseThrow(() -> new RecordNotFoundException("Proposal not found with id: " + proposalId));
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RecordNotFoundException("No user found with email: " + email));

        Review review = ReviewMapper.inputDtoToReview(reviewInputDto, user, proposal);
        Review completeReview = reviewRepository.save(review);
        return ReviewMapper.reviewToDto(completeReview);
    }

    public void deleteReview(int id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("No review found with id: " + id));

        if (!review.getUser().getEmail().equals(email) && auth.getAuthorities().stream().noneMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            throw new NotAuthorizedException("You are not allowed to delete this proposal.");
        }

        reviewRepository.delete(review);
    }
}
