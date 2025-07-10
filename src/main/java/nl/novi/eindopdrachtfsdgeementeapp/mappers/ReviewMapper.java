package nl.novi.eindopdrachtfsdgeementeapp.mappers;

import nl.novi.eindopdrachtfsdgeementeapp.dtos.ReviewDto;
import nl.novi.eindopdrachtfsdgeementeapp.dtos.ReviewInputDto;
import nl.novi.eindopdrachtfsdgeementeapp.models.Proposal;
import nl.novi.eindopdrachtfsdgeementeapp.models.Review;
import nl.novi.eindopdrachtfsdgeementeapp.models.User;

import java.time.LocalDateTime;

public class ReviewMapper {

    public static ReviewDto reviewToDto(Review review) {
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setId(review.getId());
        reviewDto.setMessage(review.getMessage());
        reviewDto.setDate(review.getDate());
        reviewDto.setUserId(review.getUser().getId());
        reviewDto.setProposalId(review.getProposal().getId());
        reviewDto.setUserFirstName(review.getUser().getFirstName());
        reviewDto.setUserLastName(review.getUser().getLastName());
        reviewDto.setAccountType(review.getUser().getAccountType().toString());
        return reviewDto;
    }

    public static Review inputDtoToReview(ReviewInputDto reviewInputDto, User user, Proposal proposal) {
        Review review = new Review();
        review.setMessage(reviewInputDto.getMessage());
        review.setDate(LocalDateTime.now());
        review.setUser(user);
        review.setProposal(proposal);
        return review;
    }
}
