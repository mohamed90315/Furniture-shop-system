package org.project.furniture_shop;

import java.sql.*;
import java.util.ArrayList;

public class UsersDatabase {
    public static ArrayList<String> getUser(String username) throws SQLException {

        // Create an empty list to store the user data
        ArrayList<String> data = new ArrayList<>();

        try {
            // Open a connection to the database
            Connection connection = DatabaseConnection.openConnection();

            // if the connection is established
            if (connection != null) {
                // Define the Query for the statement for inserting data
                String sql = "SELECT * FROM users WHERE username = '" + username + "';";

                // Create statement and execute the query
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);

                // Populate the list with the data in order
                while (resultSet.next()) {
                    data.add(resultSet.getString("id"));
                    data.add(resultSet.getString("name"));
                    data.add(resultSet.getString("username"));
                    data.add(resultSet.getString("email"));
                    data.add(resultSet.getString("password"));
                }

                // Close the connection to the database
                DatabaseConnection.closeConnection(connection);
            }
        } catch (Exception e) {
            PopUpWindow.showPopup("Error", "Error fetching data: " + e.getMessage());
        }

        return data;
    }

    public static ArrayList<String> userExists(String username, String email) throws SQLException {

        // Create an empty list to store the user data
        ArrayList<String> data = new ArrayList<>();

        try {
            // Open a connection to the database
            Connection connection = DatabaseConnection.openConnection();

            // if the connection is established
            if (connection != null) {
                // Define the Query for the statement for inserting data
                String sql = "SELECT * FROM users WHERE username = '" + username + "' OR email = '" + email + "';";

                // Create statement and execute the query
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);

                // Populate the list with the data in order
                while (resultSet.next()) {
                    data.add(resultSet.getString("id"));
                    data.add(resultSet.getString("name"));
                    data.add(resultSet.getString("username"));
                    data.add(resultSet.getString("email"));
                    data.add(resultSet.getString("password"));
                }

                // Close the connection to the database
                DatabaseConnection.closeConnection(connection);
            }
        } catch (Exception e) {
            PopUpWindow.showPopup("Error", "Error fetching data: " + e.getMessage());
        }

        return data;
    }

    public static void addUser(String name, String username, String email, String password) throws SQLException {

        try {
            // Open a connection to the database
            Connection connection = DatabaseConnection.openConnection();

            // if the connection is established
            if (connection != null) {
                // Define the SQL statement for inserting data
                String sql = "INSERT INTO users (name, username, email, password) VALUES (?, ?, ?, ?)";

                // Create a PreparedStatement object
                PreparedStatement preparedStatement = connection.prepareStatement(sql);

                // Fill in the data
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, username);
                preparedStatement.setString(3, email);
                preparedStatement.setString(4, password);

                // Execute the insert statement
                int rowsAffected = preparedStatement.executeUpdate();

                // If the inserting was successful then display a success popup
                if (rowsAffected != 0) {
                    PopUpWindow.showPopup("Success", "User Registered Successfully");
                }

                // Close the database connection
                DatabaseConnection.closeConnection(connection);
            }
        } catch (Exception e) {
            PopUpWindow.showPopup("Error", "Error adding user: " + e.getMessage());
        }
    }
}
