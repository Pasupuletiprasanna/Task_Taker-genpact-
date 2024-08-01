package System.DAO;

import System.model.EmployeeDetails;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewProfileDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/webapp";
    private static final String USER = "root";
    private static final String PASSWORD = "Prasanna@123";

    private Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        return connection;
    }

    public EmployeeDetails getEmployeeDetails(String employeeId) {
        EmployeeDetails employeeDetails = null;

        String query = "SELECT employee_id, username, password, fullname, role, email, phoneNumber, dob, address, dateOfJoining " +
                       "FROM employee WHERE employee_id = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, employeeId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                employeeDetails = new EmployeeDetails();
                employeeDetails.setEmployeeId(resultSet.getString("employee_id"));
                employeeDetails.setUsername(resultSet.getString("username"));
                employeeDetails.setPassword(resultSet.getString("password"));
                employeeDetails.setFullName(resultSet.getString("fullname"));
                employeeDetails.setRole(resultSet.getString("role"));
                employeeDetails.setEmail(resultSet.getString("email"));
                employeeDetails.setPhoneNumber(resultSet.getString("phoneNumber"));

                java.sql.Date dob = resultSet.getDate("dob");
                employeeDetails.setDob(dob != null ? dob.toString() : "N/A");

                java.sql.Date dateOfJoining = resultSet.getDate("dateOfJoining");
                employeeDetails.setDateOfJoining(dateOfJoining != null ? dateOfJoining.toString() : "N/A");

                employeeDetails.setAddress(resultSet.getString("address"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employeeDetails;
    }
}