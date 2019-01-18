package si.samgres.api.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Post {
    @Id
    int idPost;
    String description;
    String category;
    String cause;
    double x;
    double y;
    String region;
    Date date;

    @ManyToOne(fetch = FetchType.EAGER)
    User user;

    public Post() {}

    public Post(int idPost, String description, String category, String cause, double x, double y, String region, Date date)
    {
        this.idPost = idPost;
        this.description = description;
        this.category = category;
        this.cause = cause;
        this.x = x;
        this.y = y;
        this.region = region;
        this.date = date;
    }

    public Post(int idPost, String descriptio, String category, double x, double y, String region, Date date, User user)
    {
        this.idPost = idPost;
        this.description = descriptio;
        this.category = category;
        this.x = x;
        this.y = y;
        this.region = region;
        this.date = date;
        this.user = user;
    }

    public int getIdPost() {
        return idPost;
    }

    public void setIdPost(int idPost) {
        this.idPost = idPost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }
}
