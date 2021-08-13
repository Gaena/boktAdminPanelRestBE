package az.millikart.boktadminpanelrestbe.service;

import az.millikart.boktadminpanelrestbe.entity.Branch;
import az.millikart.boktadminpanelrestbe.entity.Group;
import az.millikart.boktadminpanelrestbe.exception.GroupNotFoundException;
import az.millikart.boktadminpanelrestbe.repository.BranchRepository;
import az.millikart.boktadminpanelrestbe.repository.GroupRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class BranchService {
    private final BranchRepository branchRepository;

    public BranchService(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    public List<Branch> listAllBranches() {
        List<Branch> branchesList =  branchRepository.findAll();
        if (branchesList.size() < 1){
            throw new GroupNotFoundException("No branches");
        }
        return branchesList;

    }

}
