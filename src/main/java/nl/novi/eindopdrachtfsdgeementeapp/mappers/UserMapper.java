package nl.novi.eindopdrachtfsdgeementeapp.mappers;

import nl.novi.eindopdrachtfsdgeementeapp.dtos.UpdateUserInputDto;
import nl.novi.eindopdrachtfsdgeementeapp.dtos.UserDto;
import nl.novi.eindopdrachtfsdgeementeapp.dtos.UserInputDto;
import nl.novi.eindopdrachtfsdgeementeapp.models.Authority;
import nl.novi.eindopdrachtfsdgeementeapp.models.Municipality;
import nl.novi.eindopdrachtfsdgeementeapp.models.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.stream.Collectors;

public class UserMapper {

    public static UserDto userToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setMunicipalityId(user.getMunicipality().getId());
        userDto.setMunicipalityName(user.getMunicipality().getName());
        userDto.setProvince(user.getProvince());
        userDto.setCity(user.getCity());
        userDto.setAuthorities(user.getAuthorities().stream().map(Authority::getAuthority).collect(Collectors.toSet()));
        userDto.setAccountType(user.getAccountType());
        return userDto;
    }

    public static User inputDtoToUser(UserInputDto userInputDto, Municipality municipality, PasswordEncoder encoder) {
        User user = new User();
        user.setEmail(userInputDto.getEmail());
        user.setPassword(userInputDto.getPassword());
        user.setFirstName(userInputDto.getFirstName());
        user.setLastName(userInputDto.getLastName());
        user.setMunicipality(municipality);
        user.setAccountType(userInputDto.getAccountType());
        user.setCity(userInputDto.getCity());
        user.setProvince(userInputDto.getProvince());
        user.setPassword(encoder.encode(userInputDto.getPassword()));
        return user;
    }

    public static void updateInputDtoToUser(UpdateUserInputDto dto, User user, Municipality municipality, PasswordEncoder encoder) {
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setCity(dto.getCity());
        user.setProvince(dto.getProvince());
        user.setMunicipality(municipality);

        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            user.setPassword(encoder.encode(dto.getPassword()));
        }
    }
}


