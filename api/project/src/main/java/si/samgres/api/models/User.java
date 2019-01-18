package si.samgres.api.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class User {
    @Id
    int id;
    String phone_number;
    String password;
    String fullname;
    String email;

    @OneToMany(mappedBy = "user")
    Set<Post> posts;

    public User() {     }

    public User(int id, String phone_number, String password, String fullname, String email){
        this.id = id;
        this.phone_number = phone_number;
        this.password = password;
        this.fullname = fullname;
        this.email = email;
    }

    public User(String phone_number, String password, String fullname, String email){
       this.phone_number = phone_number;
       this.password = password;
       this.fullname = fullname;
       this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set getPosts() {
        return posts;
    }

    public void setPosts(Set posts) {
        this.posts = posts;
    }
}
