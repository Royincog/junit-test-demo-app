package com.application.testapp.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtilsImpl implements DbUtils{

    private static final String URL = "jdbc:h2:tcp://localhost/~/test";
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "";

    public Connection getConnection() {

        try {
            Class.forName("org.h2.Driver");
            Connection connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            return connection;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
