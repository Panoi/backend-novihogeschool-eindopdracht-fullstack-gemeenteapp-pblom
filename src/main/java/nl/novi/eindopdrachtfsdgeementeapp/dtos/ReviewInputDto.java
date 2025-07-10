package nl.novi.eindopdrachtfsdgeementeapp.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ReviewInputDto {

    @NotBlank(message = "Message is required")
    @Size(min = 1, max = 200)
    private String message;

    public ReviewInputDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
