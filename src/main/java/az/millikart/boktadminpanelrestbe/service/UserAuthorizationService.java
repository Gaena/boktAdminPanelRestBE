package az.millikart.boktadminpanelrestbe.service;

import az.millikart.boktadminpanelrestbe.dto.login.ChangePasswordRequest;
import az.millikart.boktadminpanelrestbe.dto.login.ChangePasswordResponse;
import az.millikart.boktadminpanelrestbe.dto.login.RegisterUserRequest;
import az.millikart.boktadminpanelrestbe.dto.login.RegisterUserResponse;
import az.millikart.boktadminpanelrestbe.entity.RoleEntity;
import az.millikart.boktadminpanelrestbe.entity.Status;
import az.millikart.boktadminpanelrestbe.entity.UserLoginEntity;
import az.millikart.boktadminpanelrestbe.exception.ChangePasswordException;
import az.millikart.boktadminpanelrestbe.exception.RegisterException;
import az.millikart.boktadminpanelrestbe.exception.UserNotFoundException;
import az.millikart.boktadminpanelrestbe.repository.RoleRepository;
import az.millikart.boktadminpanelrestbe.repository.UserLoginRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class UserAuthorizationService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final UserLoginRepository userLoginRepository;

    private final RoleRepository roleRepository;

    public UserAuthorizationService(BCryptPasswordEncoder bCryptPasswordEncoder,
                                    UserLoginRepository userLoginRepository,
                                    RoleRepository roleRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userLoginRepository = userLoginRepository;
        this.roleRepository = roleRepository;
    }


    public ResponseEntity<RegisterUserResponse> getRegisterResponse(RegisterUserRequest request) {

        if (Objects.isNull(request.getPassword()) ||
                Objects.isNull(request.getUsername())) {
            throw new RegisterException("Username or password are empty");
        }

        if (request.getPassword().trim().isEmpty() ||
                request.getUsername().trim().isEmpty()) {
            throw new RegisterException("password or username is empty");
        }

        if (Objects.nonNull(userLoginRepository.findUserEntityByUsername(request.getUsername()))) {
            throw new RegisterException("Username already exists");
        }

        String encodedPass = bCryptPasswordEncoder.encode(request.getPassword());
        RoleEntity user = roleRepository.findRoleByName("ROLE_USER");
        List<RoleEntity> roleEntityList = new ArrayList<>();
        roleEntityList.add(user);

        UserLoginEntity userLoginEntity = UserLoginEntity.builder()
                .username(request.getUsername())
                .password(encodedPass)
                .roles(roleEntityList)
                .status(Status.ACTIVE)
                .build();


        userLoginRepository.save(userLoginEntity);

        RegisterUserResponse response = RegisterUserResponse.builder()
                .username(request.getUsername())
                .responseCode("0")
                .message("OK")
                .role(user.getName())
                .build();

        return ResponseEntity.ok(response);

    }

    public ResponseEntity<ChangePasswordResponse> getChangePasswordResponse(ChangePasswordRequest request) {
        if (Objects.isNull(request.getUsername()) ||
                Objects.isNull(request.getOldPassword()) ||
                Objects.isNull(request.getNewPassword())) {
            throw new ChangePasswordException("username or passwords are empty");
        }

        if (request.getNewPassword().isEmpty() || request.getOldPassword().isEmpty()) {
            throw new ChangePasswordException("password cannot be empty");
        }

        UserLoginEntity userLoginEntity = Optional.ofNullable(userLoginRepository
                .findUserEntityByUsername(request.getUsername()))
                .orElseThrow(() -> new UserNotFoundException("USER_NOT_FOUND"));

        if (bCryptPasswordEncoder.matches(request.getOldPassword(), userLoginEntity.getPassword())) {
            String encodedNewPassword = bCryptPasswordEncoder.encode(request.getNewPassword());

            userLoginEntity.setPassword(encodedNewPassword);
            userLoginRepository.save(userLoginEntity);

            ChangePasswordResponse changePasswordResponse = ChangePasswordResponse.builder()
                    .username(request.getUsername())
                    .message("Password changed")
                    .responseCode("0")
                    .build();

            return ResponseEntity.ok(changePasswordResponse);
        } else
            throw new UserNotFoundException("Wrong password");
    }


}
