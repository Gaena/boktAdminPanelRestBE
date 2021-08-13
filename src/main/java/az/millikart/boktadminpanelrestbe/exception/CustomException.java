package az.millikart.boktadminpanelrestbe.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


public class CustomException extends  RuntimeException{

    public CustomException(String message, String message1) {
        super(message);
    }
}
