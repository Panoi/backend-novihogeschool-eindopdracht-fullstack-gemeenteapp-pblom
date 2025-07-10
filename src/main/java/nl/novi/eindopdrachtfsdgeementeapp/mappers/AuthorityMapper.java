package nl.novi.eindopdrachtfsdgeementeapp.mappers;

import nl.novi.eindopdrachtfsdgeementeapp.dtos.AuthorityDto;
import nl.novi.eindopdrachtfsdgeementeapp.models.Authority;

public class AuthorityMapper {

    public static AuthorityDto toDto(Authority authority) {
        return new AuthorityDto(authority.getEmail(), authority.getAuthority());
    }
}
