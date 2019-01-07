package si.samgres.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import si.samgres.api.services.LoginService;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    LoginService loginService;

    @RequestMapping(value = "/authenticateUser", method = RequestMethod.GET)
    public String authenticateUser(@RequestParam String usernameU,@RequestParam String passwordU) {
        return loginService.authenticateUser(usernameU,passwordU);
    }

    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    public String registerUser(@RequestHeader String phone, @RequestHeader String password, @RequestHeader String name, @RequestHeader String surname, @RequestHeader String email) {
        return loginService.registerNewUser(phone, password, name, surname, email);
    }
}
