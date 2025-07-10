package nl.novi.eindopdrachtfsdgeementeapp.repositories;

import nl.novi.eindopdrachtfsdgeementeapp.models.Authority;
import nl.novi.eindopdrachtfsdgeementeapp.models.AuthorityKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorityRepository extends JpaRepository<Authority, AuthorityKey> {

}
