package System.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EditEmployeeDAO {

    public ResultSet getEmployeeDetails(String employee_id) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;

        // Load MySQL JDBC Driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Establish a connection
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/webapp", "root", "Prasanna@123");

        // Prepare SQL query
        String query = "SELECT * FROM employee WHERE employee_id = ?";
        ps = con.prepareStatement(query);
        ps.setString(1, employee_id);

        // Execute query and return result set
        return ps.executeQuery();
    }
}