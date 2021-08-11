package az.millikart.boktadminpanelrestbe.domain;

import az.millikart.boktadminpanelrestbe.entity.Group;
import az.millikart.boktadminpanelrestbe.entity.Organisation;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
public class UserRequest {

    private String firstName;
    private String lastName;
    private String middleName;
    private String birth;
    private String gender;
    private String status;

    private Integer groupId;

    private String userName;
    private String password;
    private String otp;
    private String branch;//TDDO change type to Brach class

    private Integer organisationId;

    private Date expiry;
    private Integer bitmask;

}
