package com.jennbowers;

import java.sql.*;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException{
        Class.forName("org.sqlite.JDBC");

//        opens a connection to a database if it already exists or creates a database called stats and connecting to it
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:stats.db")) {
            Statement statement = connection.createStatement();
//            cleans up after itself
            statement.executeUpdate("DROP TABLE IF EXISTS stats");
            statement.executeUpdate("CREATE TABLE stats (id INTEGER PRIMARY, name STRING, wins INTEGER, losses INTEGER)");
        } catch (SQLException ex) {
            System.out.println("Something went wrong with your DB connection.");
            ex.printStackTrace();
        }

    }
}
