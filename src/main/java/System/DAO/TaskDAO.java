package System.DAO;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import System.model.Task;

public class TaskDAO {
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

    public List<Task> getTasksByEmployeeId(String employeeId) {
        List<Task> tasks = new ArrayList<>();
        String query = "SELECT * FROM tasks WHERE employee_id = ?";
        
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, employeeId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Task task = new Task();
                task.setTaskId(resultSet.getInt("task_id"));
                task.setEmployeeId(resultSet.getString("employee_id"));
                task.setProjectId(resultSet.getInt("project_id"));
                task.setProjectName(resultSet.getString("project_name"));
                task.setTaskDate(resultSet.getDate("task_date"));
                task.setStartTime(resultSet.getTime("start_time"));
                task.setEndTime(resultSet.getTime("end_time"));
                task.setDuration(resultSet.getBigDecimal("duration"));
                task.setTaskCategory(resultSet.getString("task_category"));
                task.setDescription(resultSet.getString("description"));
                task.setApprovalStatus(resultSet.getString("approval_status"));
                tasks.add(task);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    public Task getTaskById(int taskId) {
        Task task = null;
        String query = "SELECT * FROM Tasks WHERE task_id = ?";
        
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, taskId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                task = new Task();
                task.setTaskId(resultSet.getInt("task_id"));
                task.setEmployeeId(resultSet.getString("employee_id"));
                task.setProjectId(resultSet.getInt("project_id"));
                task.setProjectName(resultSet.getString("project_name"));
                task.setTaskDate(resultSet.getDate("task_date"));
                task.setStartTime(resultSet.getTime("start_time"));
                task.setEndTime(resultSet.getTime("end_time"));
                task.setTaskCategory(resultSet.getString("task_category"));
                task.setDescription(resultSet.getString("description"));
                task.setApprovalStatus(resultSet.getString("approval_status"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return task;
    }

    public boolean updateTask(int taskId, Date taskDate, Time startTime, Time endTime, String taskCategory) {
        String query = "UPDATE Tasks SET task_date = ?, start_time = ?, end_time = ?, task_category = ? WHERE task_id = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDate(1, taskDate);
            preparedStatement.setTime(2, startTime);
            preparedStatement.setTime(3, endTime);
            preparedStatement.setString(4, taskCategory);
            preparedStatement.setInt(5, taskId);

            int result = preparedStatement.executeUpdate();
            return result > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Updated method to update task approval status
    public boolean updateTaskApprovalStatus(int taskId, String approvalStatus) {
        String query = "UPDATE Tasks SET approval_status = ? WHERE task_id = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, approvalStatus);
            preparedStatement.setInt(2, taskId);

            int result = preparedStatement.executeUpdate();
            return result > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Updated method to get tasks for supervised employees
    public List<Task> getTasksForSupervisedEmployees(String associateId) {
        List<Task> tasks = new ArrayList<>();
        // Ensure the table name is correct; e.g., Employees if that's the correct name
        String query = "SELECT * FROM Tasks WHERE employee_id IN (SELECT employee_id FROM Employees WHERE managerAlloted = ?)";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, associateId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Task task = new Task();
                task.setTaskId(resultSet.getInt("task_id"));
                task.setEmployeeId(resultSet.getString("employee_id"));
                task.setProjectId(resultSet.getInt("project_id"));
                task.setProjectName(resultSet.getString("project_name"));
                task.setTaskDate(resultSet.getDate("task_date"));
                task.setStartTime(resultSet.getTime("start_time"));
                task.setEndTime(resultSet.getTime("end_time"));
                
                // Fetch duration as BigDecimal
                BigDecimal duration = resultSet.getBigDecimal("duration");
                task.setDuration(duration);
                
                task.setTaskCategory(resultSet.getString("task_category"));
                task.setDescription(resultSet.getString("description"));
                task.setApprovalStatus(resultSet.getString("approval_status"));
                tasks.add(task);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    // Updated method to check if a task is under supervision
    public boolean isTaskUnderSupervision(int taskId, String associateId) {
        // Ensure the table name is correct; e.g., Employees if that's the correct name
        String query = "SELECT COUNT(*) FROM Tasks t JOIN Employees e ON t.employee_id = e.employee_id WHERE t.task_id = ? AND e.managerAlloted = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, taskId);
            preparedStatement.setString(2, associateId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}