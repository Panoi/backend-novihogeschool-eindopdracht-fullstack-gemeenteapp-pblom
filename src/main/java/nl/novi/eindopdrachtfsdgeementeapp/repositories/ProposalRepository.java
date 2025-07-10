package nl.novi.eindopdrachtfsdgeementeapp.repositories;

import nl.novi.eindopdrachtfsdgeementeapp.models.Proposal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProposalRepository extends JpaRepository<Proposal, Integer> {
    List<Proposal> findByUserEmail(String email);

    List<Proposal> findByUserMunicipalityId(int municipalityId);
}
