package System.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import System.model.AssociateDetails;

public class AssociateDetailsDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/webapp";
    private static final String USER = "root";
    private static final String PASSWORD = "Prasanna@123";

    private Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public AssociateDetails getAssociateDetails(String employeeId) {
        AssociateDetails associateDetails = null;

        try (Connection connection = getConnection()) {
            String query = "SELECT employee_id, username, password, fullname, role, email, phoneNumber, dob, address, dateOfJoining " +
                           "FROM Employees WHERE employee_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, employeeId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                associateDetails = new AssociateDetails();
                associateDetails.setEmployeeId(resultSet.getString("employee_id"));
                associateDetails.setUsername(resultSet.getString("username"));
                associateDetails.setPassword(resultSet.getString("password"));
                associateDetails.setFullName(resultSet.getString("fullname"));
                associateDetails.setRole(resultSet.getString("role"));
                associateDetails.setEmail(resultSet.getString("email"));
                associateDetails.setPhoneNumber(resultSet.getString("phoneNumber"));
                
                // Handle potential NULL values for DATE fields
                java.sql.Date dob = resultSet.getDate("dob");
                associateDetails.setDob(dob != null ? dob.toString() : "N/A");

                java.sql.Date dateOfJoining = resultSet.getDate("dateOfJoining");
                associateDetails.setDateOfJoining(dateOfJoining != null ? dateOfJoining.toString() : "N/A");

                associateDetails.setAddress(resultSet.getString("address"));
            } else {
                System.out.println("No associate found with employee_id: " + employeeId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return associateDetails;
    }
}