package az.millikart.boktadminpanelrestbe.repository;

import az.millikart.boktadminpanelrestbe.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Integer> {
}
