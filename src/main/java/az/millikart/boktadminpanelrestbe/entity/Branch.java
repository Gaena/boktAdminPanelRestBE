package az.millikart.boktadminpanelrestbe.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "BOKT_BRANCHES")
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @PrimaryKeyJoinColumn
    private int id;

    private String name;
    private int organisation;
    private String front_id;
    private String status;
}
