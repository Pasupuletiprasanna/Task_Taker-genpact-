package System.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import System.model.Employee;

public class GetEmployeeDetailsDAO {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/webapp";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Prasanna@123";

    /**
     * Retrieves employee details by employee ID.
     *
     * @param employeeId the ID of the employee to retrieve
     * @return an Employee object containing the details, or null if not found
     */
    public Employee getEmployeeById(String employeeId) {
        String sql = "SELECT * FROM employee WHERE employee_id = ?";
        Employee employee = null;

        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, employeeId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    employee = new Employee();
                    employee.setEmployeeId(rs.getString("employee_id"));
                    employee.setUsername(rs.getString("username"));
                    employee.setPassword(rs.getString("password"));
                    employee.setFullname(rs.getString("fullname"));
                    employee.setRole(rs.getString("role"));
                    employee.setEmail(rs.getString("email"));
                    employee.setPhoneNumber(rs.getString("phoneNumber"));
                    employee.setDob(rs.getString("dob"));
                    employee.setAddress(rs.getString("address"));
                    employee.setDateOfJoining(rs.getString("dateOfJoining"));
                    employee.setManagerAlloted(rs.getString("managerAlloted"));
                } else {
                    System.out.println("No employee found with ID: " + employeeId); // Debugging log
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Optionally log the exception or handle it as needed
        }

        return employee;
    }
}