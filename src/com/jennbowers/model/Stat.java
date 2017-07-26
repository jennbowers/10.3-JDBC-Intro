package com.jennbowers.model;

import com.jennbowers.helpers.DatabaseManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jenniferbowers on 7/26/17.
 */
public class Stat {
    private String name;
    private int wins;
    private int losses;
    private Statement statement;

//    constructor
    public Stat(String name, int wins, int losses, Statement statement) {
        this.name = name;
        this.wins = wins;
        this.losses = losses;
        this.statement = statement;
    }

//    save the row you add to the column
    public void save() throws SQLException{
        String formattedSql = String.format("INSERT INTO stats (name, wins, losses) VALUES ('%s', %s, %s)", name, wins, losses);
        statement.executeUpdate(formattedSql);
    }


    public static List<Stat> findAll(DatabaseManager dbm) throws SQLException{
        ResultSet rs= dbm.findAll("stats");
        List<Stat> tempCollection = new ArrayList<>();
        Statement tempStatement = dbm.getStatement();

        while(rs.next()) {
            String name = rs.getString("name");
            int wins = rs.getInt("wins");
            int losses = rs.getInt("losses");
            Stat tempStat = new Stat(name, wins, losses, tempStatement);
            tempCollection.add(tempStat);
        }
        return tempCollection;
    }

    @Override
    public String toString() {
        return "Stat{" +
                "name='" + name + '\'' +
                ", wins=" + wins +
                ", losses=" + losses +
                '}';
    }
}


