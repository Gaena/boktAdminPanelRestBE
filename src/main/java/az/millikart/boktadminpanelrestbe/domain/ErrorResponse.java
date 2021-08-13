package az.millikart.boktadminpanelrestbe.domain;

import lombok.Data;

@Data
public class ErrorResponse {

    private String message;
    private Integer code;
}
