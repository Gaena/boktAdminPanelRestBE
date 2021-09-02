package az.millikart.boktadminpanelrestbe.controller;

import az.millikart.boktadminpanelrestbe.entity.User;
import az.millikart.boktadminpanelrestbe.service.UserService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER","ROLE_MODERATOR"})
    @GetMapping("all")
    public List<User> list() {
        return userService.listAllUsers();
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER","ROLE_MODERATOR"})
    @PostMapping("/save")
    public User add(@RequestBody User user) {
        return userService.saveNewUser(user);
    }
}
