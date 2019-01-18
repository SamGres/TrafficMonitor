package si.samgres.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import si.samgres.api.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/changeEmail", method = RequestMethod.POST)
    public String changeEmail(@RequestHeader String token, @RequestHeader String password, @RequestHeader String newEmail) {
        return userService.changeEmail(token, password, newEmail);
    }

    @RequestMapping(value = "/changeFullname", method = RequestMethod.POST)
    public String changeFullname(@RequestHeader String token, @RequestHeader String password, @RequestHeader String newFullname) {
        return userService.changeFullname(token, password, newFullname);
    }

    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    public String changePassword(@RequestHeader String token, @RequestHeader String password, @RequestHeader String newPassword) {
        return userService.changePassword(token, password, newPassword);
    }
}
