package si.samgres.api.models;

import java.math.BigDecimal;

public class Post {

    int idPost;
    String url;
    String idUser;
    String description;
    String category;
    String region;
    BigDecimal x;
    BigDecimal y;

    public Post( int idPost,String url,String idUser,String descriptio,String category,String region,BigDecimal x,BigDecimal y)
    {
        this.idPost = idPost;
        this.url = url;
        this.idUser = idUser;
        this.description = descriptio;
        this.category = category;
        this.region = region;
        this.x = x;
        this.y = y;
    }

    public String getUserID(){
        return this.idUser;
    }

    public int getID(){
        return this.idPost;
    }


}
