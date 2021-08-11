package az.millikart.boktadminpanelrestbe.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "BOKT_USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName;
    private String lastName;
    private String middleName;
    private String birth;
    private String gender;
    private String status;

    @ManyToOne(
//            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id",referencedColumnName = "id")
    private Group group;

    private String userName;
    private String password;
    private String otp;
    private String branch;//TDDO change type to Brach class


    @ManyToOne(
//            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "organisation_id",referencedColumnName = "id")
    private Organisation organisation;

    private Date expiry;
    private Integer bitmask;

}
