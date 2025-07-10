package nl.novi.eindopdrachtfsdgeementeapp.dtos;

import java.time.LocalDateTime;
import java.util.List;

public class ProposalDto {

    private int id;
    private String title;
    private String description;
    private String photoFileName;
    private int userId;
    private int municipalityId;
    private String status;
    private List<ReviewDto> reviews;
    private LocalDateTime submittedAt;
    private String firstName;
    private String lastName;


    public ProposalDto() {
    }

    public ProposalDto(int id, String title, String description, String photoFileName, int userId, int municipalityId, String status, List<ReviewDto> reviews, LocalDateTime submittedAt, String firstName, String lastName) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.photoFileName = photoFileName;
        this.userId = userId;
        this.municipalityId = municipalityId;
        this.status = status;
        this.reviews = reviews;
        this.submittedAt = submittedAt;
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhotoFileName() {
        return photoFileName;
    }

    public void setPhotoFileName(String photoFileName) {
        this.photoFileName = photoFileName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMunicipalityId() {
        return municipalityId;
    }

    public void setMunicipalityId(int municipalityId) {
        this.municipalityId = municipalityId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(LocalDateTime submittedAt) {
        this.submittedAt = submittedAt;
    }

    public List<ReviewDto> getReview() {
        return reviews;
    }

    public void setReview(List<ReviewDto> review) {
        this.reviews = review;
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


}
