package System.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import System.model.Employee;

public class ViewEmployeeDetailsDAO {
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

    public List<Employee> getEmployeesUnderManager(String managerId) {
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT employee_id, fullname FROM employee WHERE managerAlloted = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            
            preparedStatement.setString(1, managerId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setEmployeeId(resultSet.getString("employee_id"));
                employee.setFullname(resultSet.getString("fullname"));
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employees;
    }

    public Employee getEmployeeDetails(String employeeId) {
        Employee employeeDetails = null;
        String query = "SELECT employee_id, username, fullname, role, email, phoneNumber, dob, address, dateOfJoining " +
                       "FROM employee WHERE employee_id = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            
            preparedStatement.setString(1, employeeId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                employeeDetails = new Employee();
                employeeDetails.setEmployeeId(resultSet.getString("employee_id"));
                employeeDetails.setUsername(resultSet.getString("username"));
                employeeDetails.setFullname(resultSet.getString("fullname"));
                employeeDetails.setRole(resultSet.getString("role"));
                employeeDetails.setEmail(resultSet.getString("email"));
                employeeDetails.setPhoneNumber(resultSet.getString("phoneNumber"));

                // Handle potential NULL values for DATE fields
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
