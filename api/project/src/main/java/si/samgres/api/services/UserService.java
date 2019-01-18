package si.samgres.api.services;

import org.springframework.stereotype.Service;
import si.samgres.api.helpers.GsonHelper;
import si.samgres.api.managers.DatabaseManager;
import si.samgres.api.managers.authentication.TokenManager;
import si.samgres.api.models.SimpleUser;
import si.samgres.api.models.User;
import si.samgres.api.models.authentication.AuthenticatedUser;

@Service
public class UserService {
    public String changeEmail(String token, String password, String newEmail) {
        //try getting user
        AuthenticatedUser userWrap = TokenManager.getUser(token);
        if (userWrap == null) { //flag
            return "false";
        }

        //unwrap user
        User user = userWrap.getUser();
        if (!user.getPassword().equals(password)) {
            return "false";
        }

        //change data
        user.setEmail(newEmail);

        //wrap user again
        userWrap.setUser(user);

        try {
            //update in database
            DatabaseManager.update(user);

            //update in tokenmanager
            TokenManager.setUserWithExistingToken(token, userWrap);

            //success
            return GsonHelper.toJson(true);
        }catch (Exception e) {
            e.printStackTrace();

            //fail
            return GsonHelper.toJson(false);
        }
    }

    public String changeFullname(String token, String password, String fullname) {
        //try getting user
        AuthenticatedUser userWrap = TokenManager.getUser(token);
        if (userWrap == null) { //flag
            return "false";
        }

        //unwrap user
        User user = userWrap.getUser();
        if (!user.getPassword().equals(password)) {
            return "false";
        }

        //change data
        user.setFullname(fullname);

        //wrap user again
        userWrap.setUser(user);

        try {
            //update in database
            DatabaseManager.update(user);

            //update in tokenmanager
            TokenManager.setUserWithExistingToken(token, userWrap);

            //success
            return GsonHelper.toJson(true);
        }catch (Exception e) {
            e.printStackTrace();

            //fail
            return GsonHelper.toJson(false);
        }
    }

    public String changePassword(String token, String password, String newPassword) {
        //try getting user
        AuthenticatedUser userWrap = TokenManager.getUser(token);
        if (userWrap == null) { //flag
            return "false";
        }

        //unwrap user
        User user = userWrap.getUser();
        if (!user.getPassword().equals(password)) {
            return "false";
        }

        //change data
        user.setPassword(password);

        //wrap user again
        userWrap.setUser(user);

        try {
            //update in database
            DatabaseManager.update(user);

            //update in tokenmanager
            TokenManager.setUserWithExistingToken(token, userWrap);

            //success
            return GsonHelper.toJson(true);
        }catch (Exception e) {
            e.printStackTrace();

            //fail
            return GsonHelper.toJson(false);
        }
    }

    public String getData(String token) {
        //try getting user
        AuthenticatedUser userWrap = TokenManager.getUser(token);
        if (userWrap == null) { //flag
            return "false";
        }

        //create simple user
        SimpleUser user = new SimpleUser(userWrap.getUser());
        if (user == null) {
            return "false";
        }

        //return
        return GsonHelper.toJson(user);
    }
}
