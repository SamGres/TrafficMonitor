package si.samgres.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import si.samgres.api.services.LoginService;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    LoginService loginService;

    @RequestMapping(value = "/getTestText", method = RequestMethod.GET)
    public String getTestText() {
        return loginService.getTestString();
    }

    @RequestMapping(value = "/authenticateUser", method = RequestMethod.GET)
    public String AuthenticateUser(@RequestParam String usernameU,@RequestParam String passwordU) {
        return loginService.AuthenticateUser(usernameU,passwordU);
    }




}
