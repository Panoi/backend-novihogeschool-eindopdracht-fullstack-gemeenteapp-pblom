package nl.novi.eindopdrachtfsdgeementeapp.dtos;

import nl.novi.eindopdrachtfsdgeementeapp.models.AccountType;
import nl.novi.eindopdrachtfsdgeementeapp.models.Province;

import java.util.List;
import java.util.Set;

public class UserDto {

    private int id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private int municipalityId;
    private String municipalityName;
    private List<ProposalDto> proposals;
    private List<ReviewDto> reviews;
    private Set<String> authorities;
    private AccountType accountType;
    private Province province;
    private String city;

    public UserDto() {
    }

    public UserDto(int id, String email, String password, String firstName, String lastName, int municipalityId, String municipalityName, List<ProposalDto> proposals, List<ReviewDto> reviews, AccountType accountType, Province province, String city) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.municipalityId = municipalityId;
        this.proposals = proposals;
        this.reviews = reviews;
        this.accountType = accountType;
        this.municipalityName = municipalityName;
        this.province = province;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<ProposalDto> getProposals() {
        return proposals;
    }

    public void setProposals(List<ProposalDto> proposals) {
        this.proposals = proposals;
    }

    public List<ReviewDto> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewDto> reviews) {
        this.reviews = reviews;
    }

    public Set<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<String> authorities) {
        this.authorities = authorities;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public String getMunicipalityName() {
        return municipalityName;
    }

    public void setMunicipalityName(String municipalityName) {
        this.municipalityName = municipalityName;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
