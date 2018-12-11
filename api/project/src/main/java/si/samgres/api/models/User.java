package si.samgres.api.models;


import java.util.Date;

public class User {

     String idUser;
     String password;
     String salt;
     String name;
     String surname;
     Date birthday;
     String email;

     public User(){

     }

     public User(String idUser,String password, String salt,String name,String surname, Date birthday,String email){
        this.idUser = idUser;
        this.password = password;
        this.salt = salt;
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.email = email;
     }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
