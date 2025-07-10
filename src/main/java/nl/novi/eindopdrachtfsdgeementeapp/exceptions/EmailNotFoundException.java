package nl.novi.eindopdrachtfsdgeementeapp.exceptions;

public class EmailNotFoundException extends RuntimeException {

    public EmailNotFoundException(String email) {
        super("Cannot find user " + email);
    }
}
