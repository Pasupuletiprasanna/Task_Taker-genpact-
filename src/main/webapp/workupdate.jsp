<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="System.model.Task" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Work Update</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: linear-gradient(90deg, #a6a6a6, #ffffff);
            text-align: center;
            margin: 20px;
            /* Removed background-image */
            background-size: cover;
            background-position: center;
            background-repeat: no-repeat;
            background-attachment: fixed;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
            font-size: 18px;
            text-align: left;
            background-color: rgba(255, 255, 255, 0.9); /* White with 90% opacity */
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
        }
        th {
            background-color: #f2f2f2;
        }
        button {
            padding: 5px 10px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        button:hover {
            background-color: #0056b3;
        }
        .back-button {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 20px;
            color: white;
            background-color: #f44336; /* Light red color */
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }
        .back-button:hover {
            background-color: #d32f2f;
        }
    </style>
</head>
<body>
    <h2>Update Task Approval Status</h2>
    <form action="WorkUpdateServlet" method="post">
        <table>
            <thead>
                <tr>
                    <th>Task ID</th>
                    <th>Project ID</th>
                    <th>Project Name</th>
                    <th>Task Date</th>
                    <th>Start Time</th>
                    <th>End Time</th>
                    <th>Task Category</th>
                    <th>Duration</th>
                    <th>Description</th>
                    <th>Approval Status</th>
                    <th>Update</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    Object tasksObj = request.getAttribute("tasks");
                    if (tasksObj instanceof List<?>) {
                        List<?> tasksList = (List<?>) tasksObj;
                        boolean hasTasks = false;
                        
                        for (Object obj : tasksList) {
                            if (obj instanceof Task) {
                                hasTasks = true;
                                Task task = (Task) obj;
                %>
                <tr>
                    <td><%= task.getTaskId() %></td>
                    <td><%= task.getProjectId() %></td>
                    <td><%= task.getProjectName() %></td>
                    <td><%= task.getTaskDate() %></td>
                    <td><%= task.getStartTime() %></td>
                    <td><%= task.getEndTime() %></td>
                    <td><%= task.getTaskCategory() %></td>
                    <td><%= task.getDuration() %></td>
                    <td><%= task.getDescription() %></td>
                    <td>
                            <select name="approvalStatus">
                                <option value="Pending" <%= task.getApprovalStatus().equals("Pending") ? "selected" : "" %>>Pending</option>
                                <option value="Approved" <%= task.getApprovalStatus().equals("Approved") ? "selected" : "" %>>Approved</option>
                                <option value="Rejected" <%= task.getApprovalStatus().equals("Rejected") ? "selected" : "" %>>Rejected</option>
                            </select>
                    </td>
                    <td>
                            <input type="hidden" name="taskId" value="<%= task.getTaskId() %>">
                            <button type="submit">Update Status</button>
                    </td>
                </tr>
                <% 
                            }
                        }
                        if (!hasTasks) {
                %>
                <tr>
                    <td colspan="11">No tasks available</td>
                </tr>
                <% 
                        }
                    } else {
                %>
                <tr>
                    <td colspan="11">No tasks available</td>
                </tr>
                <% 
                    }
                %>
            </tbody>
        </table>
    </form>
    <a href="associateHome.jsp" class="back-button">Back</a> <!-- Adjust href as needed -->
</body>
</html>