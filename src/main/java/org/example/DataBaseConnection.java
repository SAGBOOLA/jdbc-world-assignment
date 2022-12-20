package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/world";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASS = "";

    public static Connection getConnection(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASS);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return connection;
    }

}
