package nl.novi.eindopdrachtfsdgeementeapp.services;

import jakarta.transaction.Transactional;
import nl.novi.eindopdrachtfsdgeementeapp.dtos.ProposalDto;
import nl.novi.eindopdrachtfsdgeementeapp.dtos.ProposalInputDto;
import nl.novi.eindopdrachtfsdgeementeapp.exceptions.NotAuthorizedException;
import nl.novi.eindopdrachtfsdgeementeapp.exceptions.RecordNotFoundException;
import nl.novi.eindopdrachtfsdgeementeapp.mappers.ProposalMapper;
import nl.novi.eindopdrachtfsdgeementeapp.models.*;
import nl.novi.eindopdrachtfsdgeementeapp.repositories.*;
import org.springframework.core.io.Resource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProposalService {

    private final ProposalRepository proposalRepository;
    private final MunicipalityRepository municipalityRepository;
    private final UserRepository userRepository;
    private final ProposalPhotoRepository proposalPhotoRepository;
    private final ProposalPhotoService proposalPhotoService;
    private final StatusRepository statusRepository;

    public ProposalService(ProposalRepository proposalRepository,
                           MunicipalityRepository municipalityRepository,
                           UserRepository userRepository,
                           ProposalPhotoRepository proposalPhotoRepository,
                           ProposalPhotoService proposalPhotoService, StatusRepository statusRepository) {
        this.proposalRepository = proposalRepository;
        this.municipalityRepository = municipalityRepository;
        this.userRepository = userRepository;
        this.proposalPhotoRepository = proposalPhotoRepository;
        this.proposalPhotoService = proposalPhotoService;
        this.statusRepository = statusRepository;
    }

    public List<ProposalDto> getAllProposals() {
        List<Proposal> allProposals = proposalRepository.findAll();
        return allProposals.stream().map(ProposalMapper::proposalToDto).collect(Collectors.toList());
    }

    public ProposalDto getProposal(int id) {
        Proposal proposal = proposalRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("No proposal found with id: " + id));
        return ProposalMapper.proposalToDto(proposal);
    }

    public List<ProposalDto> getProposalsByEmail(String email) {
        List<Proposal> proposals = proposalRepository.findByUserEmail(email);
        return proposals.stream().map(ProposalMapper::proposalToDto).collect(Collectors.toList());
    }

    public List<ProposalDto> getProposalsByUserMunicipality() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByEmail(email).orElseThrow(() -> new RecordNotFoundException("No user found with email: " + email));

        int municipalityId = user.getMunicipality().getId();
        List<Proposal> proposals = proposalRepository.findByUserMunicipalityId(municipalityId);
        return proposals.stream().map(ProposalMapper::proposalToDto).collect(Collectors.toList());
    }


    @Transactional
    public ProposalDto createProposal(ProposalInputDto proposalInputDto) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RecordNotFoundException("No user found with email: " + email));

        Municipality municipality = user.getMunicipality();
        if (municipality == null) {
            throw new RecordNotFoundException("User has no Municipality");
        }

        Proposal proposal = ProposalMapper.inputDtoToProposal(proposalInputDto, user, municipality);
        proposal.setSubmittedAt(LocalDateTime.now());

        Status status = new Status();
        status.setStatus(ProposalStatus.PENDING);
        status.setProposal(proposal);
        status.setMunicipality(municipality);
        proposal.setStatus(status);

        Proposal completeProposal = proposalRepository.save(proposal);
        return ProposalMapper.proposalToDto(completeProposal);
    }


    public void deleteProposal(int id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        Proposal proposal = proposalRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("No proposal found with id: " + id));

        if (!proposal.getUser().getEmail().equals(email) && auth.getAuthorities().stream().noneMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            throw new NotAuthorizedException("You are not allowed to delete this proposal.");
        }

        proposalRepository.delete(proposal);
    }

    @Transactional
    public Resource getPhotoFromProposal(int proposalId) {
        Optional<Proposal> optionalProposal = proposalRepository.findById(proposalId);

        if (optionalProposal.isEmpty()) {
            throw new RecordNotFoundException("No proposal found with id: " + proposalId);
        }

        ProposalPhoto photo = optionalProposal.get().getPhoto();

        if (photo == null) {
            throw new RecordNotFoundException("No photo found for proposal with id: " + proposalId);
        }

        return proposalPhotoService.downLoadFile(photo.getFileName());
    }


    @Transactional
    public Proposal assignPhotoToProposal(String filename, int proposalId) {
        Optional<Proposal> optionalProposal = proposalRepository.findById(proposalId);
        Optional<ProposalPhoto> optionalPhoto = proposalPhotoRepository.findByFileName(filename);

        if (optionalProposal.isPresent() && optionalPhoto.isPresent()) {
            ProposalPhoto photo = optionalPhoto.get();
            Proposal proposal = optionalProposal.get();
            proposal.setPhoto(photo);
            return proposalRepository.save(proposal);
        } else {
            throw new RecordNotFoundException("No proposal found with id: " + proposalId);
        }
    }
}