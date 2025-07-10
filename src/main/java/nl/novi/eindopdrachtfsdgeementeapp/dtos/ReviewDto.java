package nl.novi.eindopdrachtfsdgeementeapp.dtos;

import java.time.LocalDateTime;

public class ReviewDto {

    private int id;
    private String message;
    private LocalDateTime date;
    private int userId;
    private int proposalId;
    private String userFirstName;
    private String userLastName;
    private String AccountType;

    public ReviewDto() {
    }

    public ReviewDto(int id, String message, LocalDateTime date, int userId, int proposalId, String userFirstName, String userLastName, String AccountType) {
        this.id = id;
        this.message = message;
        this.date = date;
        this.userId = userId;
        this.proposalId = proposalId;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.AccountType = AccountType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProposalId() {
        return proposalId;
    }

    public void setProposalId(int proposalId) {
        this.proposalId = proposalId;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getAccountType() {
        return AccountType;
    }

    public void setAccountType(String accountType) {
        AccountType = accountType;
    }
}
