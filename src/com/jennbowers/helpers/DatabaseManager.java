package com.jennbowers.helpers;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by jenniferbowers on 7/26/17.
 */
public class DatabaseManager {
    Statement statement;

    public DatabaseManager(Connection connection) throws SQLException{
        this.statement = connection.createStatement();
    }

    public Statement getStatement() {
        return statement;
    }

    //    creates the table and defines the columns
    public void createStatsTable() throws SQLException {
        statement.executeUpdate("CREATE TABLE stats (id INTEGER PRIMARY KEY, name STRING, wins INTEGER, losses INTEGER)");
    }

//    cleans up after itself, throw the exception to tell the method that it's being handled somewhere else
    public void dropStatsTable () throws SQLException{
        statement.executeUpdate("DROP TABLE IF EXISTS stats");
    }
}
