package si.samgres.api.services;


import org.springframework.stereotype.Service;
import si.samgres.api.helpers.GsonHelper;
import si.samgres.api.managers.DatabaseManager;
import si.samgres.api.models.Post;
import si.samgres.api.models.User;

import java.util.ArrayList;

@Service
public class LoginService {

    public String authenticateUser(String username, String password){
           //Database control class
           return "Hello user";
    }


    public String registerNewUser(String phone, String password, String name, String surname, String email)
    {
        //create new user
        User user = new User(phone, password, name, surname, email);

        //try adding user
        if (DatabaseManager.add(user)) {
            return "true";
        }
        else {
            return "false";
        }
    }
}
