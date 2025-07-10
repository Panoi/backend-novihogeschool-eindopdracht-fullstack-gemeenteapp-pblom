package nl.novi.eindopdrachtfsdgeementeapp.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import nl.novi.eindopdrachtfsdgeementeapp.models.AccountType;
import nl.novi.eindopdrachtfsdgeementeapp.models.Province;

public class UserInputDto {
    @NotBlank(message = "Email is required")
    @Email(message = "Email address is invalid, must end with @gemeenteApp.nl")
    @Size(min = 5, max = 50)
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 5, max = 50)
    private String password;

    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 25)
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 50)
    private String lastName;

    @NotNull(message = "Municipality is required")
    private int municipalityId;

    @NotNull(message = "Accounttype is required")
    private AccountType accountType;

    @NotBlank(message = "City is required")
    @Size(min = 2, max = 75)
    private String city;

    @NotNull(message = "Province is required")
    private Province province;

    public UserInputDto(AccountType accountType, int municipalityId, String lastName, String firstName, String password, String email, Province province, String city) {
        this.accountType = accountType;
        this.municipalityId = municipalityId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.password = password;
        this.email = email;
        this.province = province;
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getMunicipalityId() {
        return municipalityId;
    }

    public void setMunicipalityId(int municipalityId) {
        this.municipalityId = municipalityId;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }
}
