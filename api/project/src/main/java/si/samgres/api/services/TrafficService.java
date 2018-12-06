package si.samgres.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import si.samgres.api.helpers.GsonHelper;

@Service
public class TrafficService {
    @Autowired
    DARSService darsService;

    public String getEvents() {
        return GsonHelper.toJson(darsService.getEvents());
    }

    public String getBorderDelays() {
        return GsonHelper.toJson(darsService.getBorderDelays());
    }

    public String getStorms() {
        return GsonHelper.toJson(darsService.getStorms());
    }
}
