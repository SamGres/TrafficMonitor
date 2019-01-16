package si.samgres.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import si.samgres.api.services.TrafficService;

@RestController
@RequestMapping("/traffic")
public class TrafficController {
    @Autowired
    TrafficService trafficService;

    @RequestMapping(value = "/getEvents", method = RequestMethod.GET)
    public String getEvents(@RequestHeader String token) {
        return trafficService.getEvents(token);
    }

    @RequestMapping(value = "/getEventsBetweenDestinations", method = RequestMethod.GET)
    public String getEventsBetweenDestinations(@RequestHeader String token, @RequestHeader double x1, @RequestHeader double y1, @RequestHeader double x2, @RequestHeader double y2) {
        return trafficService.getEventsBetweenDestinations(token, x1, y1, x2, y2);
    }

    @RequestMapping(value = "/getBorderDelays", method = RequestMethod.GET)
    public String getBorderDelays(@RequestHeader String token) {
        return trafficService.getBorderDelays(token);
    }

    @RequestMapping(value = "/getStorms", method = RequestMethod.GET)
    public String getStorms(@RequestHeader String token) {
        return trafficService.getStorms(token);
    }
}
