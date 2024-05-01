package org.example.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class jdbcUtils {
    private static final String DB_URL = "jdbc:h2:~/db;AUTO_SERVER=TRUE";
    private static final String user = "admin";
    private static final String passwd = "admin";

    private static Connection connection;

    public static boolean createConnection(){
        try{
            connection = DriverManager.getConnection(DB_URL, user, passwd);
            return true;
        }
        catch(SQLException ex){
            System.out.println("Error connection database " + ex.getMessage());
        }
        return false;
    }
    private static void executeUpdate(String query) {
        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        }
        catch (SQLException ex){
            System.out.println("Error executeUpdate " + ex.getMessage());
        }
    }
    private static void createTables(){
        executeUpdate("DROP TABLE usersUrls");
        executeUpdate("DROP TABLE urls");
        executeUpdate("DROP TABLE users");
        String createUrls = "CREATE TABLE urls " +
                "(shortUrl TEXT PRIMARY KEY, longUrl TEXT)";
        String users = "CREATE TABLE users " +
                "(id INTEGER PRIMARY KEY,  login TEXT PRIMARY KEY, password INT)";
        String usersUrls = "CREATE TABLE usersUrls " +
                "(id INTEGER, shortUrl TEXT, " +
                "FOREIGN KEY (id) REFERENCES users(id)," +
                "FOREIGN KEY (shortUrl) REFERENCES urls(shortUrl))";
        executeUpdate(users);
        executeUpdate(createUrls);
        executeUpdate(usersUrls);
    }

    public static Connection getConnection() { return connection;}

    public static void closeConnection(){
        try{
            connection.close();
        }
        catch (SQLException ex){
            System.out.println("Error close database " + ex.getMessage());
        }
    }
}
