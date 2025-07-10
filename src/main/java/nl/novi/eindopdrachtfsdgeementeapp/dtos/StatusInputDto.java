package nl.novi.eindopdrachtfsdgeementeapp.dtos;


import jakarta.validation.constraints.NotNull;

public class StatusInputDto {

    @NotNull(message = "Status can not be empty")
    private String status;

    public StatusInputDto(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
