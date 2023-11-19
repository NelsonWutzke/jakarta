package com.nwutzke.apiservlet.webapp.bd.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBaseDatos {

    private static String url = "jdbc:mysql://localhost:3306/java_curso?serverTimezone=America/Argentina/Buenos_Aires";

    private static String username = "root";
    private static String password = "";

    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        return  connection = DriverManager.getConnection(url, username, password);
    }
}
