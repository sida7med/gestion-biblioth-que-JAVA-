import java.sql.*;
/**
 * Created by ahmed on 19.12.17.
 */
public class Connexion {
    Connection con=null;
    Statement stm=null;
    ResultSet res=null;

    Connection getConnexion()
    {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/monBase","root","");
        }
        catch (ClassNotFoundException e){
            System.out.println("Driver loading error: "+e);
        }catch (SQLException e){
            System.out.println("Error opening");
        }
        return con;
    }

    public int requeteUpdate(String req)
    {
        int a=0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/monBase","root","");
        }
        catch (ClassNotFoundException e){
            System.out.println("Driver loading error: "+e);
        }catch (SQLException e){
            System.out.println("Error opening");
        }
        try {
            stm = con.createStatement();
            a=stm.executeUpdate(req);

        }
        catch (Exception e){
            System.out.println("error: "+e);
        }
        try {
            if (stm != null) { stm.close(); con.close(); }
        } catch (SQLException e) {
            System.out.println("Error closing connection:"+e);
        }
        return a;
    }

    ResultSet requeteSelect(String req)
    {
        try {
            stm = con.createStatement();
        }catch (Exception e){
                System.out.println("error: "+e);
            }
        try {
            res = stm.executeQuery(req);
            return res;
        }
        catch (Exception e){
            System.out.println("error: "+e);
        }
        try {
                if (stm != null) { stm.close(); con.close(); }
        } catch (SQLException e) {
            System.out.println("Error closing connection:"+e);
        }
        return res;
    }
}