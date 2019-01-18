package si.samgres.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import si.samgres.api.helpers.GsonHelper;
import si.samgres.api.models.DARS.events.FeatureCollection;
import si.samgres.api.models.DARS.events.features.Properties;
import si.samgres.api.models.Post;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrafficService {
    @Autowired
    DARSService darsService;

    @Autowired
    LoginService loginService;

    @Autowired
    PostService postService;

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
                String cause = currentlyFeaturedEventProperties.getVzrok();
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
                Post newPost = new Post(i, description, category, cause, x, y, "TODO get region from google", date);
                allPosts.add(newPost);
            }
        }

        //get all posts from our db
        List posts = postService.getAllPosts();
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

    public String getEventsBetweenDestinations(String token, double x1, double y1, double x2, double y2) {
        if (!loginService.checkUserTokenValidity(token)) { //token flag
            return "false";
        }

        //combine dars events with our posts
        ArrayList<Post> allPosts = new ArrayList();

        //get all events from dars db
        FeatureCollection events = darsService.getEvents();
        if (events != null) {
            for (int i = 0; i < events.getFeatures().length; i++) {
                //get the current feature
                Properties currentlyFeaturedEventProperties = events.getFeatures()[i].getProperties();

                //prepare needed properties
                String description = currentlyFeaturedEventProperties.getOpis() + " " + currentlyFeaturedEventProperties.getDodatnoPojasnilo();
                String category = currentlyFeaturedEventProperties.getKategorija();
                String cause = currentlyFeaturedEventProperties.getVzrok();
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
                Post newPost = new Post(i, description, category, cause,  x, y, "TODO get region from google", date);
                allPosts.add(newPost);
            }
        }

        //get all posts from our db
        List posts = postService.getAllPosts();
        if (posts != null) {
            allPosts.addAll(posts);
        }

        //list filtered posts
        List filteredPosts = new ArrayList();

        //border thickness
        double xBorderThickness = 0.000200;
        double yBorderThickness = 0.000030;

        //where is 2nd destination
        if (x2 <= x1 && y2 >= y1) { //left up

            //set coordinates with border
            //first destination
            double x1WithBorder = x1 + xBorderThickness;
            double y1WithBorder = y1 - yBorderThickness;

            //second destination
            double x2WithBorder = x2 - xBorderThickness;
            double y2WithBorder = y2 + yBorderThickness;

            //get actual filtered posts
            filteredPosts = allPosts.stream().filter(i -> i.getX() >= x2WithBorder && i.getX() <= x1WithBorder && i.getY() >= y1WithBorder && i.getY() <= y2WithBorder).collect(Collectors.toList());
        }
        else if (x2 >= x1 && y2 >= y1) { //right up

            //set coordinates with border
            //first destination
            double x1WithBorder = x1 - xBorderThickness;
            double y1WithBorder = y1 - yBorderThickness;

            //second destination
            double x2WithBorder = x2 + xBorderThickness;
            double y2WithBorder = y2 + yBorderThickness;

            //get actual filtered posts
            filteredPosts = allPosts.stream().filter(i -> i.getX() <= x2WithBorder && i.getX() >= x1WithBorder && i.getY() >= y1WithBorder && i.getY() <= y2WithBorder).collect(Collectors.toList());
        }
        else if (x2 <= x1 && y2 <= y1) { //left down

            //set coordinates with border
            //first destination
            double x1WithBorder = x1 + xBorderThickness;
            double y1WithBorder = y1 + yBorderThickness;

            //second destination
            double x2WithBorder = x2 - xBorderThickness;
            double y2WithBorder = y2 - yBorderThickness;

            //get actual filtered posts
            filteredPosts = allPosts.stream().filter(i -> i.getX() >= x2WithBorder && i.getX() <= x1WithBorder && i.getY() <= y1WithBorder && i.getY() >= y2WithBorder).collect(Collectors.toList());
        }
        else if (x2 >= x1 && y2 <= y1) { //right down

            //set coordinates with border
            //first destination
            double x1WithBorder = x1 - xBorderThickness;
            double y1WithBorder = y1 + yBorderThickness;

            //second destination
            double x2WithBorder = x2 + xBorderThickness;
            double y2WithBorder = y2 - yBorderThickness;

            //get actual filtered posts
            filteredPosts = allPosts.stream().filter(i -> i.getX() <= x2WithBorder && i.getX() >= x1WithBorder && i.getY() <= y1WithBorder && i.getY() >= y2WithBorder).collect(Collectors.toList());
        }

        //return
        return GsonHelper.toJson(filteredPosts);
    }
}
