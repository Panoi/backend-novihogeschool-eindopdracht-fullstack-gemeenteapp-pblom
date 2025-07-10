package nl.novi.eindopdrachtfsdgeementeapp.services;

import nl.novi.eindopdrachtfsdgeementeapp.dtos.AuthorityDto;
import nl.novi.eindopdrachtfsdgeementeapp.dtos.UpdateUserInputDto;
import nl.novi.eindopdrachtfsdgeementeapp.dtos.UserDto;
import nl.novi.eindopdrachtfsdgeementeapp.dtos.UserInputDto;
import nl.novi.eindopdrachtfsdgeementeapp.exceptions.EmailNotFoundException;
import nl.novi.eindopdrachtfsdgeementeapp.exceptions.RecordNotFoundException;
import nl.novi.eindopdrachtfsdgeementeapp.mappers.AuthorityMapper;
import nl.novi.eindopdrachtfsdgeementeapp.mappers.UserMapper;
import nl.novi.eindopdrachtfsdgeementeapp.models.Authority;
import nl.novi.eindopdrachtfsdgeementeapp.models.Municipality;
import nl.novi.eindopdrachtfsdgeementeapp.models.User;
import nl.novi.eindopdrachtfsdgeementeapp.repositories.AuthorityRepository;
import nl.novi.eindopdrachtfsdgeementeapp.repositories.MunicipalityRepository;
import nl.novi.eindopdrachtfsdgeementeapp.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {


    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final MunicipalityRepository municipalityRepository;

    public UserService(UserRepository userRepository, PasswordEncoder encoder, AuthorityRepository authorityRepository, MunicipalityRepository municipalityRepository) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.authorityRepository = authorityRepository;
        this.municipalityRepository = municipalityRepository;
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(UserMapper::userToDto).collect(Collectors.toList());
    }

    public UserDto getUser(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RecordNotFoundException("No user found with email: " + email));
        return UserMapper.userToDto(user);
    }

    public UserDto createUser(UserInputDto userInputDto) {
        if (userRepository.findByEmail(userInputDto.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
        }

        Municipality municipality = municipalityRepository.findById(userInputDto.getMunicipalityId())
                .orElseThrow(() -> new IllegalArgumentException("Municipality not found"));

        User user = UserMapper.inputDtoToUser(userInputDto, municipality, encoder);

        user = userRepository.save(user);
        Authority role = new Authority();
        role.setAuthority("ROLE_" + userInputDto.getAccountType().name());
        role.setUser(user);
        role.setEmail(user.getEmail());
        user.addAuthority(role);

        userRepository.save(user);

        return UserMapper.userToDto(user);
    }

    public UserDto updateUser(int id, UpdateUserInputDto updateUserInputDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("No user found with id: " + id));

        Municipality municipality = municipalityRepository.findById(updateUserInputDto.getMunicipalityId())
                .orElseThrow(() -> new RecordNotFoundException("No municipality found with id: " + updateUserInputDto.getMunicipalityId()));

        UserMapper.updateInputDtoToUser(updateUserInputDto, user, municipality, encoder);

        User updatedUser = userRepository.save(user);
        return UserMapper.userToDto(updatedUser);
    }

    public void deleteUser(int id) {
        if (!userRepository.existsById(id)) {
            throw new RecordNotFoundException("no user found with id: " + id);
        }
        userRepository.deleteById(id);
    }

    public Set<AuthorityDto> getAuthorities(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new EmailNotFoundException("No user found with email:" + email));
        return user.getAuthorities().stream().map(AuthorityMapper::toDto).collect(Collectors.toSet());
    }

    public void addAuthority(String email, String authority) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new EmailNotFoundException("No user found with email:" + email));
        user.addAuthority(new Authority(email, authority));
        userRepository.save(user);
    }

    public void removeAuthority(String email, String authority) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new EmailNotFoundException("No user found with email:" + email));
        Authority authorityToRemove = user.getAuthorities().stream().filter((a) -> a.getAuthority().equalsIgnoreCase(authority)).findFirst().orElseThrow(() -> new RecordNotFoundException("No authority found for authority: " + authority));
        user.removeAuthority(authorityToRemove);
        userRepository.save(user);
    }
}
