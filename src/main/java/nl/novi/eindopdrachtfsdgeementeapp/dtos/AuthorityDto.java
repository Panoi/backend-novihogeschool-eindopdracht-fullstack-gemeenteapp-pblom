package nl.novi.eindopdrachtfsdgeementeapp.dtos;

public class AuthorityDto {
    private String email;
    private String authority;

    public AuthorityDto() {
    }

    public AuthorityDto(String email, String authority) {
        this.email = email;
        this.authority = authority;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}