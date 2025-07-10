package nl.novi.eindopdrachtfsdgeementeapp.dtos;

public class ProposalPhotoDto {

    private String fileName;

    public ProposalPhotoDto(String fileName) {
        this.fileName = fileName;
    }

    public ProposalPhotoDto() {
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
