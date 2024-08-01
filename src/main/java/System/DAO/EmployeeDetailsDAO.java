package System.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import System.model.Employee;

public class EmployeeDetailsDAO {
    // Database connection parameters
    private static final String DB_URL = "jdbc:mysql://localhost:3306/webapp"; // Make sure this matches your DB
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Prasanna@123";

    // SQL query to select all employees
    private static final String SQL_SELECT_ALL_EMPLOYEE = "SELECT * FROM employee"; // Ensure table name matches

    public List<Employee> getAllEmployees() throws SQLException {
        List<Employee> employeeList = new ArrayList<>();

        try (
            // Connect to MySQL database
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            // Create prepared statement with the SQL query
            PreparedStatement pstmt = conn.prepareStatement(SQL_SELECT_ALL_EMPLOYEE);
            // Execute query and get results
            ResultSet rs = pstmt.executeQuery()) {

            // Iterate over the result set
            while (rs.next()) {
                Employee employee = new Employee();
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

                employeeList.add(employee);
            }
        }

        return employeeList;
    }
}