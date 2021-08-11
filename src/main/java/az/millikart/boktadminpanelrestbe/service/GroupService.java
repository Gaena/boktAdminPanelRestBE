package az.millikart.boktadminpanelrestbe.service;

import az.millikart.boktadminpanelrestbe.entity.Group;
import az.millikart.boktadminpanelrestbe.exception.GroupNotFoundException;
import az.millikart.boktadminpanelrestbe.repository.GroupRepository;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class GroupService {
    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public List<Group> listAllGroups() {
        List<Group> groupList =  groupRepository.findAll();
         if (groupList.size() < 1){
              throw new GroupNotFoundException("No groups");
        }
         return groupList;

    }
}
