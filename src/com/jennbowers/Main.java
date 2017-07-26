package com.jennbowers;

import com.jennbowers.helpers.DatabaseManager;
import com.jennbowers.model.Stat;

import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException{
        Class.forName("org.sqlite.JDBC");

//      opens a connection to a database if it already exists or creates a database called stats and             connecting to it
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:stats.db")) {
            DatabaseManager dbm = new DatabaseManager(connection);
            welcomeMenu(dbm);

        } catch (SQLException ex) {
            System.out.println("Something went wrong with your DB connection.");
            ex.printStackTrace();
        }

    }

    public static void welcomeMenu(DatabaseManager dbm) throws SQLException{
        System.out.println("-----------------------------------------------------------");
        System.out.println("Welcome to Stat Database 3000, what would you like to do?");
        System.out.println("1) Show all stats");
        System.out.println("2) Add a new stat");
        System.out.println("3) Update an existing stat");
        System.out.println("-----------------------------------------------------------");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        switch(choice) {
            case 1:
                System.out.println("Now showing all stat data");
                List<Stat> results = Stat.findAll(dbm);
                for (Stat stat: results) {
                    System.out.println(stat);
                }
                break;
            case 2:
                System.out.println("What is the name of the player?");
                String name = scanner.next();
                System.out.println("How many wins do they have?");
                int wins = scanner.nextInt();
                System.out.println("How many losses do they have?");
                int losses = scanner.nextInt();

                new Stat(name, wins, losses, dbm.getStatement()).save();

                break;
            case 3:
                System.out.println("Which player name would you like to update?");
                String searchName = scanner.next();
                Stat.findByName(dbm, searchName);
                break;
            default:
                System.out.println("Sorry, invalid input");
        }

        welcomeMenu(dbm);
    }
}
