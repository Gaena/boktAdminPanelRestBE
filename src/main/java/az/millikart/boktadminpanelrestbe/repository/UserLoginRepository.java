package az.millikart.boktadminpanelrestbe.repository;

import az.millikart.boktadminpanelrestbe.entity.UserLoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLoginRepository extends JpaRepository<UserLoginEntity, Long> {


    UserLoginEntity findUserEntityByUsername(String username);

    UserLoginEntity findUserEntityByUsernameAndPassword(String username, String password);

}

