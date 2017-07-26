package com.jennbowers;

import com.jennbowers.helpers.DatabaseManager;
import com.jennbowers.model.Stat;

import java.sql.*;
import java.util.List;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException{
        Class.forName("org.sqlite.JDBC");

//      opens a connection to a database if it already exists or creates a database called stats and             connecting to it
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:stats.db")) {
            DatabaseManager db = new DatabaseManager(connection);
            db.dropStatsTable();
            db.createStatsTable();
            Statement statement = db.getStatement();

//          create new rows
            Stat joelStat = new Stat ("Peanut", 3, 10, statement);
            joelStat.save();

            Stat joeMontana = new Stat("Joe Montana", 750, 2, statement);
            joeMontana.save();

            Stat sarah = new Stat("Sarah Taddei", 100, 10, statement);
            sarah.save();

//          find all stats and update them
            List<Stat> results = Stat.findAll(db);
            for (Stat stat: results) {
                stat.setWins(0);
                stat.update();
            }

//          find all stats and sout them
            results = Stat.findAll(db);
            for (Stat stat: results) {
                System.out.println(stat);
            }

        } catch (SQLException ex) {
            System.out.println("Something went wrong with your DB connection.");
            ex.printStackTrace();
        }

    }
}
