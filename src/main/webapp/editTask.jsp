<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="System.model.Task" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Task</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: linear-gradient(90deg, #a6a6a6, #ffffff);
            text-align: center;
            margin: 0;
            padding: 20px;
        }
        form {
            display: inline-block;
            text-align: left;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            margin-top: 20px;
        }
        label, input {
            display: block;
            margin: 10px 0;
            width: calc(100% - 22px); /* Adjusted for padding */
        }
        input[type="text"], input[type="date"], input[type="time"] {
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 14px;
            box-sizing: border-box;
        }
        .submit-button {
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 14px;
        }
        .submit-button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <h2>Edit Task</h2>
    <%
        Task task = (Task) request.getAttribute("task");
        if (task != null) {
    %>
    <form action="TaskServlet" method="post">
        <input type="hidden" name="taskId" value="<%= task.getTaskId() %>">
        <label for="taskDate">Task Date:</label>
        <input type="date" id="taskDate" name="taskDate" value="<%= task.getTaskDate() %>">
        
        <label for="startTime">Start Time:</label>
        <input type="time" id="startTime" name="startTime" value="<%= task.getStartTime() %>">
        
        <label for="endTime">End Time:</label>
        <input type="time" id="endTime" name="endTime" value="<%= task.getEndTime() %>">
        
        <label for="taskCategory">Task Category:</label>
        <input type="text" id="taskCategory" name="taskCategory" value="<%= task.getTaskCategory() %>">
        
        <button type="submit" class="submit-button">Save Changes</button>
    </form>
    <%
        }
    %>
</body>
</html>