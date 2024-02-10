package com.projekt_biblioteka;
import java.sql.*;


public class ConnectionDB {
    public Connection connection;
    public Connection getConnection() throws SQLException{

            connection = DriverManager.getConnection("jdbc:postgresql://dumbo.db.elephantsql.com:5432/crvqnqkv",
                    "crvqnqkv", "password"); //password to DB won't be given
            return connection;
    }
}
