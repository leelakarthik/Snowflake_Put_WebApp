package com.example.put_api.put_api;

import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.Properties;
@Component
public class SnowConnection {
    static Connection con;

    SnowConnection() throws Exception {
        con = getSnowflakeConnection();
    }

    private Connection getSnowflakeConnection() throws SQLException {
        try {
            Class.forName("com.snowflake.client.jdbc.SnowflakeDriver");
        } catch (ClassNotFoundException ex) {
            System.err.println("Driver not found");
        }
        // build connection properties
        Properties properties = new Properties();
        properties.put("user", "leela21"); // replace "" with your username
        properties.put("password", "Snow@leela21"); // replace "" with your password
        properties.put("account", "lc58460.ap-southeast-1"); // replace "" with your account name
        // properties.put("db", "OUR_FIRST_DATABASE"); // replace "" with target
        // database name
        // properties.put("schema", "public"); // replace "" with target schema name
        // properties.put("tracing", "on");

        // create a new connection
        String connectStr = System.getenv("SF_JDBC_CONNECT_STRING");
        // use the default connection string if it is not set in environment
        if (connectStr == null) {
            connectStr = "jdbc:snowflake://lc58460.ap-southeast-1.snowflakecomputing.com"; // replace accountName with
            // your account
            // name
        }
        return DriverManager.getConnection(connectStr, properties);
    }

    public static Connection getConnection() throws SQLException {
        return con;
    }

    public static void closeConnection() throws SQLException {
        con.close();
        System.out.println("Connection closed");
    }
}
