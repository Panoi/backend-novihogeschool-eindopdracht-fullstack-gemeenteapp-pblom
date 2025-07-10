package nl.novi.eindopdrachtfsdgeementeapp.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ProposalInputDto {

@NotBlank(message = "Title is required")
@Size(min = 5, max = 50)
private String title;
@NotBlank(message = "Description is required")
@Size(min = 10, max = 1000)
private String description;


    public ProposalInputDto(String title, String description) {
        this.title = title;
        this.description = description;
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
}
