package nl.novi.eindopdrachtfsdgeementeapp.mappers;

import nl.novi.eindopdrachtfsdgeementeapp.dtos.ProposalDto;
import nl.novi.eindopdrachtfsdgeementeapp.dtos.ProposalInputDto;
import nl.novi.eindopdrachtfsdgeementeapp.dtos.ReviewDto;
import nl.novi.eindopdrachtfsdgeementeapp.models.Municipality;
import nl.novi.eindopdrachtfsdgeementeapp.models.Proposal;
import nl.novi.eindopdrachtfsdgeementeapp.models.User;
//import nl.novi.eindopdrachtfsdgeementeapp.models.ProposalPhoto;

import java.util.List;
import java.util.stream.Collectors;

public class ProposalMapper {

    public static ProposalDto proposalToDto(Proposal proposal) {
        ProposalDto proposalDto = new ProposalDto();
        proposalDto.setId(proposal.getId());
        proposalDto.setDescription(proposal.getDescription());
        proposalDto.setTitle(proposal.getTitle());
        proposalDto.setStatus(proposal.getStatus().getStatus().toString());
        if (proposal.getPhoto() != null) {proposalDto.setPhotoFileName(proposal.getPhoto().getFileName());}
        proposalDto.setMunicipalityId(proposal.getMunicipality().getId());
        proposalDto.setUserId(proposal.getUser().getId());
        proposalDto.setSubmittedAt(proposal.getSubmittedAt());
        List<ReviewDto> reviewsDto = proposal.getReviews().stream().map(ReviewMapper::reviewToDto).collect(Collectors.toList());
        proposalDto.setReview(reviewsDto);
        proposalDto.setFirstName(proposal.getUser().getFirstName());
        proposalDto.setLastName(proposal.getUser().getLastName());
        return proposalDto;
    }

    public static Proposal inputDtoToProposal(ProposalInputDto proposalInputDto, User user, Municipality municipality) {
        Proposal proposal = new Proposal();
        proposal.setTitle(proposalInputDto.getTitle());
        proposal.setDescription(proposalInputDto.getDescription());
        proposal.setUser(user);
        proposal.setMunicipality(municipality);
        return proposal;
    }
}
