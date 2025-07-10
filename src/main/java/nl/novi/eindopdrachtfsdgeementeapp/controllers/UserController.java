package nl.novi.eindopdrachtfsdgeementeapp.controllers;

import jakarta.validation.Valid;
import nl.novi.eindopdrachtfsdgeementeapp.dtos.*;
import nl.novi.eindopdrachtfsdgeementeapp.models.Authority;
import nl.novi.eindopdrachtfsdgeementeapp.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> allUsers = userService.getAllUsers();
        return ResponseEntity.ok(allUsers);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> getUser(@PathVariable String email) {
        UserDto user = userService.getUser(email);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody @Valid UserInputDto userInputDto) {
        UserDto createdUser = userService.createUser(userInputDto);
        return ResponseEntity.ok(createdUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") int id, @RequestBody @Valid UpdateUserInputDto updateUserInputDto) {
        UserDto updatedUser = userService.updateUser(id, updateUserInputDto);
        return ResponseEntity.ok().body(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{email}/authorities")
    public ResponseEntity<Set<AuthorityDto>> getUserAuthorities(@PathVariable String email) {
        Set<AuthorityDto> authorities = userService.getAuthorities(email);
        return ResponseEntity.ok().body(authorities);
    }

    @PostMapping("/{email}/authorities")
    public ResponseEntity<Void> addUserAuthorities(@PathVariable String email, @RequestBody @Valid AuthorityInputDto authorityInputDto) {
        userService.addAuthority(email, authorityInputDto.getAuthority());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{email}/authorities/{authority}")
    public ResponseEntity<Void> deleteUserAuthorities(@PathVariable String email, @PathVariable String authority) {
        userService.removeAuthority(email, authority);
        return ResponseEntity.noContent().build();
    }
}
