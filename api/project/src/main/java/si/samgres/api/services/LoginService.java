package si.samgres.api.services;

import org.springframework.stereotype.Service;
import javax.print.DocFlavor;
import java.util.Date;

@Service
public class LoginService {
    public String getTestString() {
        return "hello world!";
    }

    //Uspešna prijava dodeli token in ga vrne drugače null
    public String AuthenticateUser(String username, String password){
           //Database control class
           return "Hello user";
    }

    //Uspešna prijava vrne True drugače false
    public boolean RegisterNewUser(String username, String password, String name, String surname, Date birthday)
    {
      return false;
    }
}
