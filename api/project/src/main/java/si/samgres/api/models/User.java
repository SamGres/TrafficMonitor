package si.samgres.api.models;


import java.util.Date;

public class User {

     String idUser;
     String user_name;
     String password;
     String salt;
     String name;
     String surname;
     Date birthday;

     public User(String idUser,String user_name,String password, String salt,String name,String surname, Date birthday){
        this.idUser = idUser;
        this.user_name = user_name;
        this.password = password;
        this.salt = salt;
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
     }

     public String GetID()
     {
         return  this.idUser;
     }

     public String GetSalt(){
         return this.salt;
     }

     public String GetPassword(){
         return  this.password;
     }

     public String GetUserName(){
         return  this.user_name;
     }

     public String GetNameandSurname(){
         return String.format("%s %s",this.name,this.surname);
     }



}
