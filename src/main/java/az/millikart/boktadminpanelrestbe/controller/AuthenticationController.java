package az.millikart.boktadminpanelrestbe.controller;


import az.millikart.boktadminpanelrestbe.dto.login.UserLoginRequest;
import az.millikart.boktadminpanelrestbe.dto.login.UserLoginResponse;
import az.millikart.boktadminpanelrestbe.service.AuthenticationService;
import az.millikart.boktadminpanelrestbe.service.UserAuthorizationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserAuthorizationService userAuthorizationService;

    public AuthenticationController(AuthenticationService authenticationService,
                                    UserAuthorizationService userAuthorizationService) {
        this.authenticationService = authenticationService;
        this.userAuthorizationService = userAuthorizationService;
    }


    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> login(@RequestBody UserLoginRequest requestDto) {
        return authenticationService.getLoginResponse(requestDto);
    }



//    @PostMapping("/register")
//    public ResponseEntity<RegisterUserResponse> register(@RequestBody RegisterUserRequest request) {
//        return userAuthorizationService.getRegisterResponse(request);
//    }

}
