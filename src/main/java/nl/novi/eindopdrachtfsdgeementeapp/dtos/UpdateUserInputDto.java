package nl.novi.eindopdrachtfsdgeementeapp.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import nl.novi.eindopdrachtfsdgeementeapp.models.Province;

public class UpdateUserInputDto {

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

    @NotBlank(message = "City is required")
    @Size(min = 2, max = 75)
    private String city;

    @NotNull(message = "Province is required")
    private Province province;

    public UpdateUserInputDto(int municipalityId, String lastName, String firstName, String password, Province province, String city) {
        this.municipalityId = municipalityId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.password = password;
        this.province = province;
        this.city = city;
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
