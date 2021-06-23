package DB;

import java.sql.*;

public class DBConnection {
    public Connection conn;
    public Statement stat;
    public ResultSet result;
    public PreparedStatement pstat;

    public DBConnection(){
        try{
            String url = "jdbc:sqlserver://localhost;database=AntrianKTP_Kel05;user=sa;password=polman";
            //String url = "jdbc:sqlserver://localhost;database=AntrianKTP_Kel05;integratedSecurity=true";
            conn = DriverManager.getConnection(url);
            stat = conn.createStatement();
        }
        catch (Exception e){
            System.out.println("Error saat connect database: "+e);
        }
    }

    public static void main(String[] args){
        DBConnection connection = new DBConnection();
        System.out.println("Connection berhasil");
    }
}
