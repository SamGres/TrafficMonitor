package si.samgres.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import si.samgres.api.managers.DatabaseManager;
import si.samgres.api.models.Post;
import si.samgres.api.models.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PostService {
    @Autowired
    LoginService loginService;

    public List getAllPosts() {
        return DatabaseManager.getAll(Post.class);
    }

    public String add(String token, String description, String category, String cause, double x, double y, String region, String date) {
        if (!loginService.checkUserTokenValidity(token)) { //token flag
            return "false";
        }

        //get user
        User user = loginService.getUser(token);
        if (user == null) { //flag
            return "false";
        }

        //format date
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date formattedDate = new Date();
        try {
             formattedDate = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //get current highest post id from db
        int id = ((ArrayList<Post>)DatabaseManager.getAll(Post.class)).stream().max(Comparator.comparing(z -> z.getId())).get().getId();
        id++; //increase for new post

        //create new post object
        Post post = new Post(id, description, category, cause, x, y, region, formattedDate, user);

        //add to db
        try {
            DatabaseManager.add(post);
        } catch (Exception e) {
            e.printStackTrace();

            //fail
            return "false";
        }

        //success
        return "true";
    }
}
