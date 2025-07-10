package nl.novi.eindopdrachtfsdgeementeapp.dtos;

public class StatusDto {

    private int id;
    private String status;
    private int municipalityId;
    private int proposalId;

    public StatusDto() {
    }

    public StatusDto(int id, String status, int municipalityId, int proposalId) {
        this.id = id;
        this.status = status;
        this.municipalityId = municipalityId;
        this.proposalId = proposalId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getMunicipalityId() {
        return municipalityId;
    }

    public void setMunicipalityId(int municipalityId) {
        this.municipalityId = municipalityId;
    }

    public int getProposalId() {
        return proposalId;
    }

    public void setProposalId(int proposalId) {
        this.proposalId = proposalId;
    }
}

