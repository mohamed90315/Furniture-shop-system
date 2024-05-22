package org.project.furniture_shop;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection {

    public static Connection connectDatabase() {
        // Load the MySQL JDBC driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            PopUpWindow.showPopup("Error", "Error loading MySQL JDBC driver: " + e.getMessage());
            return null;
        }

        // JDBC URL for your database
        String jdbcUrl = "jdbc:mysql://localhost:3306/twelfth_project_db";
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
            connection.close();
        } catch (SQLException e) {
            PopUpWindow.showPopup("Error", "Error closing the connection: " + e.getMessage());
        }
    }

    public static ArrayList<String> getUser(String username) throws SQLException {

        try {
            Connection connection = connectDatabase();
            ArrayList<String> data = new ArrayList<>();

            // Define the Query for the statement for inserting data
            String sql = "SELECT * FROM users WHERE username = '" + username + "';";

            // Create statement and execute the query
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                data.add(resultSet.getString("id"));
                data.add(resultSet.getString("name"));
                data.add(resultSet.getString("username"));
                data.add(resultSet.getString("email"));
                data.add(resultSet.getString("password"));
            }

            closeConnection(connection);

            return data;
        }
        catch (Exception e) {
            PopUpWindow.showPopup("Error", "Error fetching data: " + e.getMessage());
            return null;
        }
    }

    public static class ShoppingCart {
        private List<FurnitureMain.Item> items;
        private List<Integer> quantities;

        public ShoppingCart() {
            items = new ArrayList<>();
            quantities = new ArrayList<>();
        }

        public void addItem(FurnitureMain.Item item, int quantity) {
            items.add(item);
            quantities.add(quantity);
        }

        public List<FurnitureMain.Item> getItems() {
            return items;
        }

        public List<Integer> getQuantities() {
            return quantities;
        }
    }
}
