package nl.novi.eindopdrachtfsdgeementeapp.mappers;

import nl.novi.eindopdrachtfsdgeementeapp.dtos.ProposalPhotoDto;
import nl.novi.eindopdrachtfsdgeementeapp.models.ProposalPhoto;

public class ProposalPhotoMapper {

    public static ProposalPhotoDto ProposalPhotoToDto(ProposalPhoto proposalPhoto) {
        ProposalPhotoDto proposalPhotoDto = new ProposalPhotoDto();
        proposalPhotoDto.setFileName(proposalPhoto.getFileName());
        return proposalPhotoDto;
    }

    public static ProposalPhoto inputDtoToProposalPhoto(ProposalPhotoDto proposalPhotoDto) {
        ProposalPhoto proposalPhoto = new ProposalPhoto();
        proposalPhoto.setFileName(proposalPhotoDto.getFileName());
        return proposalPhoto;
    }

}
