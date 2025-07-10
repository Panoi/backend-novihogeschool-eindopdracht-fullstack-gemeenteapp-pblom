package nl.novi.eindopdrachtfsdgeementeapp.repositories;

import nl.novi.eindopdrachtfsdgeementeapp.models.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StatusRepository extends JpaRepository<Status, Integer> {
    Optional<Status> findByProposalId(int proposalId);
}
