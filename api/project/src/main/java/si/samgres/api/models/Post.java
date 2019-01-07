package si.samgres.api.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
public class Post {
    @Id
    int idPost;
    String description;
    String category;
    BigDecimal x;
    BigDecimal y;
    String region;

    @ManyToOne(fetch = FetchType.EAGER)
    User user;

    public Post() {}

    public Post(int idPost, String descriptio, String category, BigDecimal x, BigDecimal y, String region)
    {
        this.idPost = idPost;
        this.description = descriptio;
        this.category = category;
        this.x = x;
        this.y = y;
        this.region = region;
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

    public BigDecimal getX() {
        return x;
    }

    public void setX(BigDecimal x) {
        this.x = x;
    }

    public BigDecimal getY() {
        return y;
    }

    public void setY(BigDecimal y) {
        this.y = y;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
