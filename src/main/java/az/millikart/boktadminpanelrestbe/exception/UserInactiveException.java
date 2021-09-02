package az.millikart.boktadminpanelrestbe.exception;

public class UserInactiveException extends RuntimeException {

    public UserInactiveException() {
        super();
    }

    public UserInactiveException(String message) {
        super(message);
    }
}
