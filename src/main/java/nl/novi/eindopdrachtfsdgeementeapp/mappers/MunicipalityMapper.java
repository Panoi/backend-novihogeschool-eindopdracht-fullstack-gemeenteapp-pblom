package nl.novi.eindopdrachtfsdgeementeapp.mappers;

import nl.novi.eindopdrachtfsdgeementeapp.dtos.MunicipalityDto;
import nl.novi.eindopdrachtfsdgeementeapp.dtos.MunicipalityInputDto;
import nl.novi.eindopdrachtfsdgeementeapp.dtos.ProposalDto;
import nl.novi.eindopdrachtfsdgeementeapp.dtos.StatusDto;
import nl.novi.eindopdrachtfsdgeementeapp.models.Municipality;
import nl.novi.eindopdrachtfsdgeementeapp.models.Province;

import java.util.List;
import java.util.stream.Collectors;

public class MunicipalityMapper {

    public static MunicipalityDto municipalityToDto(Municipality municipality) {
        MunicipalityDto municipalityDto = new MunicipalityDto();
        municipalityDto.setId(municipality.getId());
        municipalityDto.setName(municipality.getName());
        municipalityDto.setProvince(municipality.getProvince());
        return municipalityDto;
    }

    public static Municipality inputDtoToMunicipality(MunicipalityInputDto municipalityInputDto) {
        Municipality municipality = new Municipality();
        municipality.setName(municipalityInputDto.getName());
        municipality.setProvince(Province.valueOf(municipalityInputDto.getProvince()));
        return municipality;
    }
}
