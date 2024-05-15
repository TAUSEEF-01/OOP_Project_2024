package com.example.hellofx;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class databaseconnection {
    public Connection databaseLink;

    public Connection getConnection() {
        String databaseName = "project";
        String databaseUser = "root";
        String databasePassword = "baby1_2panda";
        String url = "jdbc:mysql://127.0.0.1:3306/" + databaseName;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return databaseLink;
    }

   /* public static void main(String[] args) {
        databaseconnection connection = new databaseconnection();
        Connection conn = connection.getConnection();
        if (conn != null) {
            System.out.println("Connected to database.");
            try {
                // Now, you can execute SQL queries
                // Example: Select data from logintable in the project schema
                String connectquery = "SELECT * FROM logintable";
                Statement statement = conn.createStatement();
                ResultSet queryoutput = statement.executeQuery(connectquery);
                while(queryoutput.next()){
                    System.out.println(queryoutput);
                }
                // Execute the query and process the result set
                // (code for executing queries and processing results goes here)
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Failed to connect to database.");
        }
    }*/
}
