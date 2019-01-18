package si.samgres.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import si.samgres.api.services.TrafficService;
import si.samgres.api.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/changeEmail", method = RequestMethod.POST)
    public String changeEmail(@RequestHeader String token, @RequestHeader String newemail) {
        return userService.changeEmail(token, newemail);
    }

    @RequestMapping(value = "/changeFullname", method = RequestMethod.POST)
    public String changeFullname(@RequestHeader String token, @RequestHeader String newfullname) {
        return userService.changeFullname(token, newfullname);
    }

    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    public String changePassword(@RequestHeader String token, @RequestHeader String newpassword) {
        return userService.changeFullname(token, newpassword);
    }
}
