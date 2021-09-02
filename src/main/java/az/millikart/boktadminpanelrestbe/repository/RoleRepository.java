package az.millikart.boktadminpanelrestbe.repository;

import az.millikart.boktadminpanelrestbe.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    RoleEntity findRoleByName(String name);

}
