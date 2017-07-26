package com.jennbowers.model;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by jenniferbowers on 7/26/17.
 */
public class Stat {
    private String name;
    private int wins;
    private int losses;
    private Statement statement;

    public Stat(String name, int wins, int losses, Statement statement) {
        this.name = name;
        this.wins = wins;
        this.losses = losses;
        this.statement = statement;
    }

    public void save() throws SQLException{
        String formattedSql = String.format("INSERT INTO stats (name, wins, losses) VALUES ('%s', %s, %s)", name, wins, losses);
        statement.executeUpdate(formattedSql);
    }
}


