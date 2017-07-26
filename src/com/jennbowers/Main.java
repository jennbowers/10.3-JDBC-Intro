package com.jennbowers;

import com.jennbowers.helpers.DatabaseManager;
import com.jennbowers.model.Stat;

import java.sql.*;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException{
        Class.forName("org.sqlite.JDBC");

//        opens a connection to a database if it already exists or creates a database called stats and connecting to it
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:stats.db")) {
            DatabaseManager db = new DatabaseManager(connection);
            db.dropStatsTable();
            db.createStatsTable();
            Statement statement = db.getStatement();

            Stat joelStat = new Stat ("Peanut", 3, 10, statement);
            joelStat.save();

            Stat joeMontana = new Stat("Joe Montana", 750, 2, statement);
            joeMontana.save();

//          select and get all from table and store in a result set... means you can look through and                get info back one row at a time
            ResultSet rs = statement.executeQuery("SELECT * FROM stats");

//          iterates through results one row at a time while there is a next row
            while(rs.next()) {
//            assigned what you are getting back for each column to a variable
              String name = rs.getString("name");
              int wins = rs.getInt("wins");
              int losses = rs.getInt("losses");
//            prints variables for name, wins and losses
              System.out.printf("%s %s %s", name, wins, losses);
            }



        } catch (SQLException ex) {
            System.out.println("Something went wrong with your DB connection.");
            ex.printStackTrace();
        }

    }
}
