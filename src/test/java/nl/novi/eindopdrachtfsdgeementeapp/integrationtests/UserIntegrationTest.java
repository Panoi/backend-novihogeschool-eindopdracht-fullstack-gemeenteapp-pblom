package nl.novi.eindopdrachtfsdgeementeapp.integrationtests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import nl.novi.eindopdrachtfsdgeementeapp.dtos.UserDto;
import nl.novi.eindopdrachtfsdgeementeapp.models.AccountType;
import nl.novi.eindopdrachtfsdgeementeapp.models.Municipality;
import nl.novi.eindopdrachtfsdgeementeapp.models.Province;
import nl.novi.eindopdrachtfsdgeementeapp.repositories.MunicipalityRepository;
import nl.novi.eindopdrachtfsdgeementeapp.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class UserIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MunicipalityRepository municipalityRepository;

    Municipality municipality;
    UserDto userDto;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
        municipalityRepository.deleteAll();

        municipality = new Municipality();
        municipality.setName("Amsterdam");
        municipalityRepository.save(municipality);

        userDto = new UserDto();
        userDto.setId(1);
        userDto.setEmail("testgebruiker@gemeenteapp.nl");
        userDto.setPassword("wachtwoord");
        userDto.setFirstName("Pascal");
        userDto.setLastName("Blom");
        userDto.setMunicipalityId(municipality.getId());
        userDto.setMunicipalityName(municipality.getName());
        userDto.setAccountType(AccountType.RESIDENT);
        userDto.setProvince(Province.NOORD_HOLLAND);
        userDto.setCity("Amsterdam");
    }

    @Test
    void createUser() throws Exception {
        mockMvc.perform(post("/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(userDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("testgebruiker@gemeenteapp.nl"))
                .andExpect(jsonPath("$.firstName").value("Pascal"))
                .andExpect(jsonPath("$.lastName").value("Blom"))
                .andExpect(jsonPath("$.municipalityId").value(municipality.getId()))
                .andExpect(jsonPath("$.municipalityName").value("Amsterdam"))
                .andExpect(jsonPath("$.accountType").value("RESIDENT"))
                .andExpect(jsonPath("$.province").value("NOORD_HOLLAND"))
                .andExpect(jsonPath("$.city").value("Amsterdam"));
    }

    public static String asJsonString(final UserDto obj) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}