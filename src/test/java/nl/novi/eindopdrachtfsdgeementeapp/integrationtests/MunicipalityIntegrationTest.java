package nl.novi.eindopdrachtfsdgeementeapp.integrationtests;

import nl.novi.eindopdrachtfsdgeementeapp.dtos.MunicipalityDto;
import nl.novi.eindopdrachtfsdgeementeapp.models.Municipality;
import nl.novi.eindopdrachtfsdgeementeapp.models.Province;
import nl.novi.eindopdrachtfsdgeementeapp.repositories.MunicipalityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class MunicipalityIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MunicipalityRepository municipalityRepository;

    Municipality municipality1;
    Municipality municipality2;
    MunicipalityDto municipalityDto1;
    MunicipalityDto municipalityDto2;

    @BeforeEach
    void setUp() {
        municipalityRepository.deleteAll();

        municipality1 = new Municipality();
        municipality1.setName("Amsterdam");
        municipality1.setProvince(Province.NOORD_HOLLAND);

        municipality2 = new Municipality();
        municipality2.setName("Rotterdam");
        municipality2.setProvince(Province.ZUID_HOLLAND);

        municipalityRepository.save(municipality1);
        municipalityRepository.save(municipality2);

        municipalityDto1 = new MunicipalityDto();
        municipalityDto1.setId(municipality1.getId());
        municipalityDto1.setName("Amsterdam");
        municipalityDto1.setProvince(Province.NOORD_HOLLAND);

        municipalityDto2 = new MunicipalityDto();
        municipalityDto2.setId(municipality2.getId());
        municipalityDto2.setName("Rotterdam");
        municipalityDto2.setProvince(Province.ZUID_HOLLAND);
    }

    @Test
    void getAllMunicipalities() throws Exception {
        mockMvc.perform(get("/municipalities")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(municipality1.getId()))
                .andExpect(jsonPath("$[0].name").value("Amsterdam"))
                .andExpect(jsonPath("$[0].province").value("NOORD_HOLLAND"))
                .andExpect(jsonPath("$[1].id").value(municipality2.getId()))
                .andExpect(jsonPath("$[1].name").value("Rotterdam"))
                .andExpect(jsonPath("$[1].province").value("ZUID_HOLLAND"));
    }
}