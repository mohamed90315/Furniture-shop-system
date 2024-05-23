package org.project.furniture_shop;

import java.sql.*;

public class DatabaseConnection {

    public static Connection openConnection() {
        // Load the MySQL JDBC driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            PopUpWindow.showPopup("Error", "Error loading MySQL JDBC driver: " + e.getMessage());
            return null;
        }

        // JDBC URL for your database
        String jdbcUrl = "jdbc:mysql://localhost:3306/javadb";
        String username = "root";
        String password = "";

        // Connection object for managing the connection to the database
        Connection connection = null;

        try {
            // Establish a connection to the database
            connection = DriverManager.getConnection(jdbcUrl, username, password);

            return connection;
        } catch (SQLException e) {
            PopUpWindow.showPopup("Error", "Failed to connect to Database: " + e.getMessage());
            return null;
        }
    }

    public static void closeConnection(Connection connection) {
        try {
            // Close the connection with the database
            connection.close();
        } catch (SQLException e) {
            PopUpWindow.showPopup("Error", "Error closing the connection: " + e.getMessage());
        }
    }
}
