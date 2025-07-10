package nl.novi.eindopdrachtfsdgeementeapp.controllers;

import nl.novi.eindopdrachtfsdgeementeapp.EindopdrachtfsdgeementeappApplication;
import nl.novi.eindopdrachtfsdgeementeapp.dtos.MunicipalityDto;
import nl.novi.eindopdrachtfsdgeementeapp.models.Province;
import nl.novi.eindopdrachtfsdgeementeapp.services.MunicipalityService;
import nl.novi.eindopdrachtfsdgeementeapp.utils.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.List;


@WebMvcTest(controllers = MunicipalityController.class)
@ContextConfiguration(classes={EindopdrachtfsdgeementeappApplication.class})
class MunicipalityControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MunicipalityService municipalityService;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private JwtUtil jwtUtil;

    @Test
    public void testControllerGetAllMunicipalities() throws Exception {
        MunicipalityDto municipalityDto = new MunicipalityDto();
        municipalityDto.setId(1);
        municipalityDto.setName("Amsterdam");
        municipalityDto.setProvince(Province.NOORD_HOLLAND);

        List<MunicipalityDto> municipalities = List.of(municipalityDto);

        given(municipalityService.getAllMunicipalities()).willReturn(municipalities);

        mvc.perform(get("/municipalities")
                .with(user("admin").roles("ADMIN"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is("Amsterdam")))
                .andExpect(jsonPath("$[0].province", is("NOORD_HOLLAND")));
    }
}