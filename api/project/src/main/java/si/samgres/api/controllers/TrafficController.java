package si.samgres.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import si.samgres.api.services.TrafficService;

@RestController
@RequestMapping("/traffic")
public class TrafficController {
    @Autowired
    TrafficService trafficService;

    @RequestMapping(value = "/getEvents", method = RequestMethod.GET)
    public String getEvents() {
        return trafficService.getEvents();
    }

    @RequestMapping(value = "/getBorderDelays", method = RequestMethod.GET)
    public String getBorderDelays() {
        return trafficService.getBorderDelays();
    }

    @RequestMapping(value = "/getStorms", method = RequestMethod.GET)
    public String getStorms() {
        return trafficService.getStorms();
    }
}
