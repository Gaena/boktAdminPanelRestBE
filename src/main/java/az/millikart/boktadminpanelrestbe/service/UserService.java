package az.millikart.boktadminpanelrestbe.service;
import az.millikart.boktadminpanelrestbe.entity.Group;
import az.millikart.boktadminpanelrestbe.entity.User;
import az.millikart.boktadminpanelrestbe.exception.GroupNotFoundException;
import az.millikart.boktadminpanelrestbe.exception.UserNotAddedException;
import az.millikart.boktadminpanelrestbe.exception.UserNotFoundException;
import az.millikart.boktadminpanelrestbe.repository.GroupRepository;
import az.millikart.boktadminpanelrestbe.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> listAllUsers() {
        List<User> userList =  userRepository.findAll();
        if (userList.size() < 20){
            throw new UserNotFoundException("No users");
        }
        return userList;
    }

    public User saveNewUser(User user){
        User addedUser = userRepository.save(user);
        if(Objects.isNull(addedUser)) {
            log.error("No user added");
            throw new UserNotAddedException("No users added");
        } else {
            return addedUser;
        }
    }
}

