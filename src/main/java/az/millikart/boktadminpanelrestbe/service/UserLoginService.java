package az.millikart.boktadminpanelrestbe.service;

import az.millikart.boktadminpanelrestbe.dto.login.UserLoginRequest;
import az.millikart.boktadminpanelrestbe.dto.login.UserLoginResponse;
import az.millikart.boktadminpanelrestbe.dto.login.UserRoleDto;
import az.millikart.boktadminpanelrestbe.entity.RoleEntity;
import az.millikart.boktadminpanelrestbe.entity.UserLoginEntity;
import az.millikart.boktadminpanelrestbe.exception.UserNotFoundException;
import az.millikart.boktadminpanelrestbe.repository.RoleRepository;
import az.millikart.boktadminpanelrestbe.repository.UserLoginRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class UserLoginService {

    private final UserLoginRepository userLoginRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public List<UserLoginResponse> getAllUsers() {
        List<UserLoginEntity> loginEntityList = userLoginRepository.findAll();
        List<UserLoginResponse> loginResponseList = new ArrayList<>();
        for (UserLoginEntity user : loginEntityList) {

            log.info("Role size: " + user.getRoles().size());
            log.info("Role: " + user.getRoles().get(0).getName());
            UserLoginResponse response = UserLoginResponse.builder()
                    .username(user.getUsername())
                    .role(user.getRoles().get(0).getName())
                    .status(user.getStatus().name())
                    .created(user.getCreated())
                    .build();
            loginResponseList.add(response);
        }

        return loginResponseList;

    }

    public UserLoginEntity getUserDataByUsername(String username) {
        return Optional.ofNullable(userLoginRepository.findUserEntityByUsername(username))
                .orElseThrow(() -> new UserNotFoundException("USER_NOT_FOUND"));
    }

    public UserLoginEntity getUserDataByUsernameAndPassword(UserLoginRequest userLoginRequest) {
        UserLoginEntity userLoginEntity = Optional.ofNullable(userLoginRepository.findUserEntityByUsername(userLoginRequest.getUsername()))
                .orElseThrow(() -> new UserNotFoundException("User with username: " +
                        userLoginRequest.getUsername() + " not found"));

        if (passwordEncoder.matches(userLoginRequest.getPassword(), userLoginEntity.getPassword())) {
            return userLoginEntity;
        } else
            throw new UserNotFoundException("Wrong password");
    }

    public UserRoleDto changeUserRole(UserRoleDto userRoleDto) {
        UserLoginEntity userLoginEntity = Optional.ofNullable(userLoginRepository
                .findUserEntityByUsername(userRoleDto.getUserName()))
                .orElseThrow(() -> new UserNotFoundException("USER_NOT_FOUND"));

        RoleEntity roleEntity = Optional.ofNullable(roleRepository
                .findRoleByName(userRoleDto.getRoleName()))
                .orElseThrow(() -> new UserNotFoundException("ROLE_NOT_FOUND"));

        List<RoleEntity> roleEntityList = new ArrayList<>();
        roleEntityList.add(roleEntity);

        userLoginEntity.setRoles(roleEntityList);
        userLoginRepository.save(userLoginEntity);

        return UserRoleDto.builder()
                .roleName(userLoginEntity.getRoles().get(0).getName())
                .userName(userLoginEntity.getUsername())
                .message("USERNAME UPDATED")
                .build();
    }


}
