package si.samgres.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import si.samgres.api.helpers.GsonHelper;

@Service
public class TrafficService {
    @Autowired
    DARSService darsService;

    @Autowired
    LoginService loginService;

    public String getEvents(String token) {
        if (!loginService.checkUserTokenValidity(token)) { //token flag
            return "false";
        }

        return GsonHelper.toJson(darsService.getEvents());
    }

    public String getBorderDelays(String token) {
        if (!loginService.checkUserTokenValidity(token)) { //token flag
            return "false";
        }

        return GsonHelper.toJson(darsService.getBorderDelays());
    }

    public String getStorms(String token) {
        if (!loginService.checkUserTokenValidity(token)) { //token flag
            return "false";
        }

        return GsonHelper.toJson(darsService.getStorms());
    }
}
