package com.reclaite;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PostgresConnection {

    private static Connection CONNECTION;

    static {
        try {
            Class.forName("org.postgresql.Driver");
            Properties properties = new Properties();
            try (InputStream input = PostgresConnection.class.getClassLoader().getResourceAsStream("db.properties")) {
                if (input == null) {
                    throw new IOException("Unable to find db.properties");
                }
                properties.load(input);
            }

            String host = properties.getProperty("db.host");
            String port = properties.getProperty("db.port");
            String dbName = properties.getProperty("db.name");
            String username = properties.getProperty("db.username");
            String password = properties.getProperty("db.password");

            String url = "jdbc:postgresql://" + host + ":" + port + "/" + dbName;

            CONNECTION = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException | IOException exception) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Cannot establish SQL connection", exception);
        }
    }

    public static Connection getConnection() {
        return CONNECTION;
    }

}
