package si.samgres.api.managers.authentication;

import si.samgres.api.helpers.UUIDHelper;
import si.samgres.api.models.User;
import si.samgres.api.models.authentication.AuthenticatedUser;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class TokenManager {
    private static HashMap<String, AuthenticatedUser> authenticatedUsers;

    private static void initialize() {
        if (authenticatedUsers == null) { //ensure singleton
            authenticatedUsers = new HashMap<>();
        }
    }

    public static String signIn(User user) {
        initialize(); //ensure list

        //try signing in a user
        try {
            //get todays date
            Date today = new Date();

            //add one day
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(today);
            calendar.add(Calendar.DATE, 1); //add one day

            //create authenticated user
            AuthenticatedUser au = new AuthenticatedUser(user, calendar.getTime());

            //generate id
            String token = UUIDHelper.generate();

            //sign in
            authenticatedUsers.put(token, au);

            //success
            return token;
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        //return
        return "false";
    }

    public static boolean isTokenValid(String token) {
        initialize(); //ensure objects

        //boolean for validity
        boolean valid = false;
        if (authenticatedUsers.containsKey(token)) {
            //get authenticated user
            AuthenticatedUser au = authenticatedUsers.get(token);

            //get todays date
            Date today = new Date();

            //check if todays date is past the date of authenticated user
            if (today.compareTo(au.getDate()) <= 0) { //it hasn't expired yet
                valid = true;
            }
            else {
                authenticatedUsers.remove(token);
            }
        }

        return valid;
    }
}
