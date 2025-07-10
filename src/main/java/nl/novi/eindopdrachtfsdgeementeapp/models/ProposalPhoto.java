package nl.novi.eindopdrachtfsdgeementeapp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class ProposalPhoto {

    @Id
    private String fileName;

    @OneToOne(mappedBy = "photo")
    private Proposal proposal;

    public ProposalPhoto(String fileName) {
        this.fileName = fileName;
    }

    public ProposalPhoto() {
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Proposal getProposal() {
        return proposal;
    }

    public void setProposal(Proposal proposal) {
        this.proposal = proposal;
    }
}
