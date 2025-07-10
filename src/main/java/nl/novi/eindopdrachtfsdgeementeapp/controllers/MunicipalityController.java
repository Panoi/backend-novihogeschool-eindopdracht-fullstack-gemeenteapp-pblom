package nl.novi.eindopdrachtfsdgeementeapp.controllers;

import jakarta.validation.Valid;
import nl.novi.eindopdrachtfsdgeementeapp.dtos.MunicipalityDto;
import nl.novi.eindopdrachtfsdgeementeapp.dtos.MunicipalityInputDto;
import nl.novi.eindopdrachtfsdgeementeapp.models.Province;
import nl.novi.eindopdrachtfsdgeementeapp.services.MunicipalityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/municipalities")
public class MunicipalityController {

    private final MunicipalityService municipalityService;

    public MunicipalityController(MunicipalityService municipalityService) {
        this.municipalityService = municipalityService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<MunicipalityDto> getMunicipality(@PathVariable int id) {
        MunicipalityDto municipality = municipalityService.getMunicipality(id);
        return ResponseEntity.ok(municipality);
    }

    @GetMapping
    public ResponseEntity<List<MunicipalityDto>> getAllMunicipalities() {
        List<MunicipalityDto> allMunicipalities = municipalityService.getAllMunicipalities();
        return ResponseEntity.ok(allMunicipalities);
    }


    @GetMapping("province")
    public ResponseEntity<List<String>> getAllMunicipalitiesProvince() {
        List<String> allProvinces = municipalityService.getAllProvinces();
        return ResponseEntity.ok(allProvinces);
    }

    @GetMapping("by-province/{province}")
    public ResponseEntity<List<MunicipalityDto>> getMunicipalitiesByProvince(@PathVariable Province province) {
        List<MunicipalityDto> municipalities = municipalityService.getAllMunicipalitiesByProvince(province);
        return ResponseEntity.ok(municipalities);
    }

    @PostMapping
    public ResponseEntity<MunicipalityDto> createMunicipality(@RequestBody @Valid MunicipalityInputDto municipalityInputDto) {
        MunicipalityDto saved = municipalityService.createMunicipality(municipalityInputDto);
        return ResponseEntity.ok(saved);
    }
}
