package si.samgres.api.services;

import org.springframework.stereotype.Service;
import si.samgres.api.helpers.GsonHelper;
import si.samgres.api.managers.DatabaseManager;
import si.samgres.api.managers.authentication.TokenManager;
import si.samgres.api.models.User;
import si.samgres.api.models.authentication.AuthenticatedUser;

@Service
public class UserService {
    public String changeEmail(String token, String newEmail) {
        //try getting user
        AuthenticatedUser userWrap = TokenManager.getUser(token);
        if (userWrap == null) { //flag
            return "false";
        }

        //unwrap user
        User user = userWrap.getUser();
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
}
