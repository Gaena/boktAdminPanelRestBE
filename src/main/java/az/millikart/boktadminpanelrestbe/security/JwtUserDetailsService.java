package az.millikart.boktadminpanelrestbe.security;

import az.millikart.boktadminpanelrestbe.entity.UserLoginEntity;
import az.millikart.boktadminpanelrestbe.exception.UserNotFoundException;
import az.millikart.boktadminpanelrestbe.security.jwt.JwtUser;
import az.millikart.boktadminpanelrestbe.security.jwt.JwtUserFactory;
import az.millikart.boktadminpanelrestbe.service.UserLoginService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@Slf4j
@AllArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final UserLoginService userLoginService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserLoginEntity userLoginEntity = (UserLoginEntity) Optional.ofNullable(userLoginService.getUserDataByUsername(username))
                .orElseThrow(() -> new UserNotFoundException("USER_NOT_FOUND"));

        JwtUser jwtUser = JwtUserFactory.create(userLoginEntity);

        log.info("IN loadByUserName jwt user: " + jwtUser);
        return jwtUser;
    }
}
