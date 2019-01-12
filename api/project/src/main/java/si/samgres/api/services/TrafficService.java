package si.samgres.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import si.samgres.api.helpers.GsonHelper;
import si.samgres.api.models.DARS.events.FeatureCollection;
import si.samgres.api.models.DARS.events.features.Feature;
import si.samgres.api.models.DARS.events.features.Properties;
import si.samgres.api.models.Post;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TrafficService {
    @Autowired
    DARSService darsService;

    @Autowired
    LoginService loginService;

    @Autowired
    SamGresService samGresService;

    public String getEvents(String token) {
        if (!loginService.checkUserTokenValidity(token)) { //token flag
            return "false";
        }

        //combine dars events with our posts
        List allPosts = new ArrayList();

        //get all events from dars db
        FeatureCollection events = darsService.getEvents();
        if (events != null) {
            for (int i = 0; i < events.getFeatures().length; i++) {
                //get the current feature
                Properties currentlyFeaturedEventProperties = events.getFeatures()[i].getProperties();

                //prepare needed properties
                String description = currentlyFeaturedEventProperties.getOpis() + " " + currentlyFeaturedEventProperties.getDodatnoPojasnilo();
                String category = currentlyFeaturedEventProperties.getKategorija();
                double x = currentlyFeaturedEventProperties.getX();
                double y = currentlyFeaturedEventProperties.getY();
                Date date = null;
                try {
                    date = new SimpleDateFormat().parse(currentlyFeaturedEventProperties.getUpdated());
                } catch (ParseException e) {
                    e.printStackTrace();
                    date = new Date();
                }

                //load data of the feature into a new post
                Post newPost = new Post(i, description, category, x, y, "TODO get region from google", date);
                allPosts.add(newPost);
            }
        }

        //get all posts from our db
        List posts = samGresService.getAllPosts();
        if (posts != null) {
            allPosts.addAll(posts);
        }

        //return all
        return GsonHelper.toJson(allPosts);
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
