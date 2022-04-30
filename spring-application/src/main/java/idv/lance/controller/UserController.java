package idv.lance.controller;

import idv.lance.dao.entity.UserView;
import idv.lance.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserController {

    UserService userService;

    @GetMapping("/users")
    List<UserView> getUsers() {
        return userService.users();
    }
}
