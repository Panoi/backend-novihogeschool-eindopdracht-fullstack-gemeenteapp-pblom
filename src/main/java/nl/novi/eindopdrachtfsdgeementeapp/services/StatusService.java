package nl.novi.eindopdrachtfsdgeementeapp.services;

import nl.novi.eindopdrachtfsdgeementeapp.dtos.StatusDto;
import nl.novi.eindopdrachtfsdgeementeapp.dtos.StatusInputDto;
import nl.novi.eindopdrachtfsdgeementeapp.exceptions.RecordNotFoundException;
import nl.novi.eindopdrachtfsdgeementeapp.mappers.StatusMapper;
import nl.novi.eindopdrachtfsdgeementeapp.models.Proposal;
import nl.novi.eindopdrachtfsdgeementeapp.models.ProposalStatus;
import nl.novi.eindopdrachtfsdgeementeapp.models.Status;
import nl.novi.eindopdrachtfsdgeementeapp.repositories.ProposalRepository;
import nl.novi.eindopdrachtfsdgeementeapp.repositories.StatusRepository;
import org.springframework.stereotype.Service;

import static nl.novi.eindopdrachtfsdgeementeapp.mappers.StatusMapper.statusToDto;

@Service
public class StatusService {

    private final StatusRepository statusRepository;
    private final ProposalRepository proposalRepository;

    public StatusService(StatusRepository statusRepository, ProposalRepository proposalRepository) {
        this.statusRepository = statusRepository;
        this.proposalRepository = proposalRepository;
    }

    public StatusDto getStatus(int id) {
        Status status = statusRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("No status found with id: " + id));
        return statusToDto(status);
    }

    public StatusDto getStatusByProposalId(int proposalId) {
        Proposal proposal = proposalRepository.findById(proposalId)
                .orElseThrow(() -> new RecordNotFoundException("No proposal found with id: " + proposalId));

        Status status = proposal.getStatus();

        return StatusMapper.statusToDto(status);
    }

    public StatusDto updateStatusByProposalId(int proposalId, StatusInputDto statusInputDto) {
        Status status = statusRepository.findByProposalId(proposalId)
                .orElseThrow(() -> new RecordNotFoundException("No status found with proposalId: " + proposalId));

        status.setStatus(ProposalStatus.valueOf(statusInputDto.getStatus()));
        Status updatedStatus = statusRepository.save(status);

        return statusToDto(updatedStatus);
    }
}






