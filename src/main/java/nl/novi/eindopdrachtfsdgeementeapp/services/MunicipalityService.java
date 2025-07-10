package nl.novi.eindopdrachtfsdgeementeapp.services;

import nl.novi.eindopdrachtfsdgeementeapp.dtos.MunicipalityDto;
import nl.novi.eindopdrachtfsdgeementeapp.dtos.MunicipalityInputDto;
import nl.novi.eindopdrachtfsdgeementeapp.exceptions.RecordNotFoundException;
import nl.novi.eindopdrachtfsdgeementeapp.mappers.MunicipalityMapper;
import nl.novi.eindopdrachtfsdgeementeapp.models.Municipality;
import nl.novi.eindopdrachtfsdgeementeapp.models.Province;
import nl.novi.eindopdrachtfsdgeementeapp.repositories.MunicipalityRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MunicipalityService {

    private final MunicipalityRepository municipalityRepository;

    public MunicipalityService(MunicipalityRepository municipalityRepository) {
        this.municipalityRepository = municipalityRepository;
    }

    public MunicipalityDto getMunicipality(int id) {
        Municipality municipality = municipalityRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("No municipality found with id: " + id));
        return MunicipalityMapper.municipalityToDto(municipality);
    }

    public List<MunicipalityDto> getAllMunicipalities() {
        List<Municipality> municipalities = municipalityRepository.findAll();
        return municipalities.stream().map(MunicipalityMapper::municipalityToDto).collect(Collectors.toList());
    }

    public List<String> getAllProvinces() {
        return Arrays.stream(Province.values()).map(Enum::name).collect(Collectors.toList());
    }

    public List<MunicipalityDto> getAllMunicipalitiesByProvince(Province province) {
        List<Municipality> municipalities = municipalityRepository.findByProvince(province);
        return municipalities.stream().map(MunicipalityMapper::municipalityToDto).collect(Collectors.toList());
    }

    public MunicipalityDto createMunicipality(MunicipalityInputDto municipalityInputDto) {
        Municipality municipality = MunicipalityMapper.inputDtoToMunicipality(municipalityInputDto);
        Municipality newMunicipality = municipalityRepository.save(municipality);
        return MunicipalityMapper.municipalityToDto(newMunicipality);
    }
}
