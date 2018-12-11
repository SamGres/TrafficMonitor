package si.samgres.api.services;


import org.springframework.stereotype.Service;
import si.samgres.api.helpers.GsonHelper;
import si.samgres.api.models.DatabseControlClass;
import si.samgres.api.models.UUIDgenerator;
import si.samgres.api.models.User;

import javax.print.DocFlavor;
import java.util.Date;

@Service
public class LoginService {
    public String getTestString() {
        return "hello world!";
    }
    DatabseControlClass baza;
    UUIDgenerator uuiDgenerator;
    GsonHelper gsonHelper;
    public LoginService(){
        baza = new DatabseControlClass();
        uuiDgenerator = new UUIDgenerator();
        gsonHelper = new GsonHelper();
    }

    public String AuthenticateUser(String username, String password){
           //Database control class
           return "Hello user";
    }


    public boolean RegisterNewUser(String json)
    {
        return baza.InsertNewUser(gsonHelper.fromJson(json, User.class));
    }
}
