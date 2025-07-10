package nl.novi.eindopdrachtfsdgeementeapp.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class MunicipalityInputDto {

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 50)
    private String name;
    @NotBlank(message = "Description is required")

    @NotNull(message = "Province is required")
    private String province;

    public MunicipalityInputDto(String name, String province) {
        this.name = name;
        this.province = province;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
