package si.samgres.api.services;


import org.springframework.stereotype.Service;
import si.samgres.api.managers.DatabaseManager;
import si.samgres.api.managers.authentication.TokenManager;
import si.samgres.api.models.User;

import java.util.List;

@Service
public class LoginService {
    public boolean checkUserTokenValidity(String token) {
        //check validity
        return TokenManager.isTokenValid(token);
    }

    public String authenticateUser(String email, String password){
        //get users
        List<User> users = DatabaseManager.getAll(User.class);
        if (users == null) {
            return "false";
        }

        //try finding user
        for (int i = 0; i < users.size(); i++) {
            User current = users.get(i);

            //check credentials
            if (current.getEmail().equals(email) && current.getPassword().equals(password)) {
                return TokenManager.signIn(current);
            }
        }

        //fail
        return "false";
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
