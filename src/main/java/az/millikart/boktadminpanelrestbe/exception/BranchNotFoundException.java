package az.millikart.boktadminpanelrestbe.exception;

public class BranchNotFoundException extends RuntimeException{

    public BranchNotFoundException(String message) {
        super(message);
    }
}
