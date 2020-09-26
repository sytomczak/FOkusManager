package pl.sytomczak.fokusmanager.dbUtils;

import java.io.File;
import java.sql.*;

public class DBConnection {
    private static String USERNAME = "dbUser";
    private static String PASSWORD = "dbPassword";
    private static String MYSQLCONN = "jdbc:mysql://localhost/login";
    private static String SQLITECONN = "jdbc:sqlite:focusManager.sqllite";


    public static Connection getConnection(){
        try{
            Class.forName("org.sqlite.JDBC");
            File file = new File("focusManager.sqlite");
            if(!file.exists())
                CreateDatabase("fokusManager.sqlite");

            return DriverManager.getConnection(SQLITECONN);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    private static void CreateDatabase(String fileName){
        try(Connection conn = DriverManager.getConnection("jdbc:sqlite:"+fileName)){
            if(conn != null)
            {
                Statement statement = conn.createStatement();
                statement.setQueryTimeout(30);

                statement.executeUpdate("drop table if exists texts");
                statement.executeUpdate("create table texts (nr integer, text string)");

                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("Name Driver's database: " + meta.getDriverName());
                System.out.println("Database is created");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
