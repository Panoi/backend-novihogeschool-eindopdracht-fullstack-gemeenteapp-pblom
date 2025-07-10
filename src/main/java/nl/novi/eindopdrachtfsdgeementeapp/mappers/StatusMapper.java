package nl.novi.eindopdrachtfsdgeementeapp.mappers;

import nl.novi.eindopdrachtfsdgeementeapp.dtos.StatusDto;
import nl.novi.eindopdrachtfsdgeementeapp.dtos.StatusInputDto;
import nl.novi.eindopdrachtfsdgeementeapp.models.ProposalStatus;
import nl.novi.eindopdrachtfsdgeementeapp.models.Status;

public class StatusMapper {

    public static StatusDto statusToDto(Status status) {
        StatusDto statusDto = new StatusDto();
        statusDto.setId(status.getId());
        statusDto.setStatus(status.getStatus().toString());
        statusDto.setMunicipalityId(status.getMunicipality().getId());
        statusDto.setProposalId(status.getProposal().getId());
        return statusDto;
    }

    public static Status inputDtoToStatus(StatusInputDto statusInputDto) {
        Status status = new Status();
        status.setStatus(ProposalStatus.valueOf(statusInputDto.getStatus()));
        return status;
    }

}
