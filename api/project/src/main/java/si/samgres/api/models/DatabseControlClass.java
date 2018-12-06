package si.samgres.api.models;
import java.sql.*;


public class DatabseControlClass {

    public boolean InsertNewUser(User user){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("sql7.freemysqlhosting.net", "sql7267489", "PmsqdM8FNI");

            Statement st = con.createStatement();
            String sql = ("IF NOT EXISTS (SELECT * FROM User) INSERT INTO USER(...) VALUES (.....)");
            st.executeUpdate(sql);


            con.close();
        }
        catch(Exception ex)
        {
           return false;
        }

        return true;
    }

    public boolean GetUserData(String username, String password)
    {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/t", "", "");

            Statement st = con.createStatement();
            String sql = ("SELECT * FROM posts ORDER BY id DESC LIMIT 1;");
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()) {
                int id = rs.getInt("first_column_name");
                String str1 = rs.getString("second_column_name");
            }

            con.close();
        }
        catch (Exception ex)
        {

        }
        return true;
    }
}
