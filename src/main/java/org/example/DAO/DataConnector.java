package org.example.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataConnector {
    private Connection con;
    public DataConnector(){
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/webchat", "root", "root");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public Connection getConnection(){
        return con;
    }
}
