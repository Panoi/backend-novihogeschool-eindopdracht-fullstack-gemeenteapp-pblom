package nl.novi.eindopdrachtfsdgeementeapp.services;

import nl.novi.eindopdrachtfsdgeementeapp.dtos.MunicipalityDto;
import nl.novi.eindopdrachtfsdgeementeapp.dtos.MunicipalityInputDto;
import nl.novi.eindopdrachtfsdgeementeapp.models.Municipality;
import nl.novi.eindopdrachtfsdgeementeapp.models.Province;
import nl.novi.eindopdrachtfsdgeementeapp.repositories.MunicipalityRepository;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@ContextConfiguration
class MunicipalityServiceTest {

    @Autowired
    private MunicipalityService municipalityService;

    @MockBean
    private MunicipalityRepository municipalityRepository;

    @Test
    void testGetAllMunicipalities() {
        Municipality municipality1 = new Municipality();
        municipality1.setId(1);
        municipality1.setName("Amsterdam");
        municipality1.setProvince(Province.NOORD_HOLLAND);

        Municipality municipality2 = new Municipality();
        municipality2.setId(2);
        municipality2.setName("Utrecht");
        municipality2.setProvince(Province.UTRECHT);

        List<Municipality> municipalityList = List.of(municipality1, municipality2);
        when(municipalityRepository.findAll()).thenReturn(municipalityList);

        List<MunicipalityDto> result = municipalityService.getAllMunicipalities();

        assertEquals(municipalityList.size(), result.size());
    }

    @Test
    void testCreateMunicipality() {
        MunicipalityInputDto municipality = new MunicipalityInputDto("Amsterdam", "NOORD_HOLLAND");
        municipality.setName("Amsterdam");
        municipality.setProvince("NOORD_HOLLAND");

        Municipality newMunicipality = new Municipality();
        newMunicipality.setId(1);
        newMunicipality.setName("Amsterdam");
        newMunicipality.setProvince(Province.NOORD_HOLLAND);

        Mockito.when(municipalityRepository.save(any(Municipality.class))).thenReturn(newMunicipality);

        MunicipalityDto result = municipalityService.createMunicipality(municipality);

        assertEquals(1, result.getId());
        assertEquals("Amsterdam", result.getName());
        assertEquals(Province.NOORD_HOLLAND, result.getProvince());
    }

    @Test
    void testGetAllMunicipalityByProvince() {
        Province province =  Province.NOORD_HOLLAND;

        Municipality municipality1 = new Municipality();
        municipality1.setId(1);
        municipality1.setName("Amsterdam");
        municipality1.setProvince(province);

        Municipality municipality2 = new Municipality();
        municipality2.setId(2);
        municipality2.setName("Amsterdam");
        municipality2.setProvince(province);

        List<Municipality> municipalityList = List.of(municipality1, municipality2);
        when(municipalityRepository.findByProvince(province)).thenReturn(List.of(municipality1, municipality2));

        List<MunicipalityDto> result = municipalityService.getAllMunicipalitiesByProvince(province);

        assertEquals(municipalityList.size(), result.size());
    }

    @Test
    void testGetAllProvinces() {
        List<String> Provinces = municipalityService.getAllProvinces();

     assertEquals(12, Provinces.size());
    }

    @Test
    void testGetMunicipality(){
        Municipality municipality = new Municipality();
        municipality.setId(1);
        municipality.setName("Amsterdam");
        municipality.setProvince(Province.NOORD_HOLLAND);


        Mockito.when(municipalityRepository.findById(1)).thenReturn(Optional.of(municipality));

        MunicipalityDto result = municipalityService.getMunicipality(1);

        assertEquals(1, result.getId());
        assertEquals("Amsterdam", result.getName());
        assertEquals(Province.NOORD_HOLLAND, result.getProvince());
    }
}