package nl.novi.eindopdrachtfsdgeementeapp.dtos;

import jakarta.validation.constraints.NotBlank;

public class AuthorityInputDto {

    @NotBlank(message = "Authority is required")
    private String authority;

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
