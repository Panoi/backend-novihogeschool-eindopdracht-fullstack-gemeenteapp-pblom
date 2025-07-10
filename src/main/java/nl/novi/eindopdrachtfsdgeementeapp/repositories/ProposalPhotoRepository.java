package nl.novi.eindopdrachtfsdgeementeapp.repositories;

import nl.novi.eindopdrachtfsdgeementeapp.models.ProposalPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProposalPhotoRepository extends JpaRepository<ProposalPhoto, String> {
    Optional<ProposalPhoto> findByFileName(String FileName);
}
