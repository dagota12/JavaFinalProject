/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connection;

/**
 *
 * @author DAGIM
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;
import java.util.List;

public class DatabaseUpdater {

    // Generic method for executing updates in the database
    public static int executeUpdate(String query, List<Object> parameterValues) {
        
        int affected = 0;
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            // Set parameter values based on their types
            setParameters(preparedStatement, parameterValues);

            // Execute the update
            affected = preparedStatement.executeUpdate();

            System.out.println("Update executed successfully!");

        } catch (SQLException e) {
            System.err.println("Error executing update: " + e.getMessage());
        }
        
        return affected;
    }

    // Helper method to set parameters in the prepared statement
    private static void setParameters(PreparedStatement preparedStatement, List<Object> parameterValues) throws SQLException {
        for (int i = 0; i < parameterValues.size(); i++) {
            Object parameterValue = parameterValues.get(i);

            // Handle different data types
            if (parameterValue instanceof Integer) {
                preparedStatement.setInt(i + 1, (Integer) parameterValue);
            } else if (parameterValue instanceof String) {
                preparedStatement.setString(i + 1, (String) parameterValue);
            } else if (parameterValue instanceof Double) {
                preparedStatement.setDouble(i + 1, (Double) parameterValue);
            } else if (parameterValue instanceof Date || parameterValue instanceof java.sql.Date) {
                preparedStatement.setDate(i + 1, (java.sql.Date) parameterValue);
            }
            // Add more cases as needed
            

            // Handle other data types as necessary
        }
    }

    public static void main(String[] args) {
        // Example usage
        String updateQuery = "UPDATE your_table SET column1 = ?, column2 = ? WHERE condition_column = ?";
        List<Object> parameterValues = List.of("NewValue1", 42, "ConditionValue");

        executeUpdate(updateQuery, parameterValues);
    }
}

