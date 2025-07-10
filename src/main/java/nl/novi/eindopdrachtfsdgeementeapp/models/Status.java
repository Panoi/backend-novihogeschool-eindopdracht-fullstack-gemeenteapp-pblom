package nl.novi.eindopdrachtfsdgeementeapp.models;

import jakarta.persistence.*;

@Entity
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private ProposalStatus status = ProposalStatus.PENDING;

    @ManyToOne
    @JoinColumn(name = "municipality_id")
    private Municipality municipality;

    @OneToOne
    @JoinColumn(name = "proposal_id")
    private Proposal proposal;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProposalStatus getStatus() {
        return status;
    }

    public void setStatus(ProposalStatus status) {
        this.status = status;
    }

    public Municipality getMunicipality() {
        return municipality;
    }

    public void setMunicipality(Municipality municipality) {
        this.municipality = municipality;
    }

    public Proposal getProposal() {
        return proposal;
    }

    public void setProposal(Proposal proposal) {
        this.proposal = proposal;
    }

}
