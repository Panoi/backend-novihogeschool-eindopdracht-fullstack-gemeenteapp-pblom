package nl.novi.eindopdrachtfsdgeementeapp.repositories;

import nl.novi.eindopdrachtfsdgeementeapp.models.Municipality;
import nl.novi.eindopdrachtfsdgeementeapp.models.Province;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MunicipalityRepository extends JpaRepository<Municipality, Integer> {
    List<Municipality> findByProvince(Province province);
}
