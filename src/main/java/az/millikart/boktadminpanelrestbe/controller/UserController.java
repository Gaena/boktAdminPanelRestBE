package az.millikart.boktadminpanelrestbe.controller;

import az.millikart.boktadminpanelrestbe.entity.Group;
import az.millikart.boktadminpanelrestbe.entity.User;
import az.millikart.boktadminpanelrestbe.service.GroupService;
import az.millikart.boktadminpanelrestbe.service.UserService;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.lang.UsesSunHttpServer;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("all")
    public List<User> list() {
        return userService.listAllUsers();
    }

    @PostMapping("/save")
    public User add(@RequestBody User user) {
        return userService.saveNewUser(user);
    }
}
