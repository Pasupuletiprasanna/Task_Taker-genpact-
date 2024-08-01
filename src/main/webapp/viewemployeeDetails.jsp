<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Employee Details</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            background: linear-gradient(90deg, #a6a6a6, #ffffff);
            /* Removed background-image */
            background-size: cover;
            background-position: center;
            background-repeat: no-repeat;
            background-attachment: fixed;
        }
        .employee-container {
            width: 60%;
            margin: 0 auto;
            background-color: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .employee-item {
            margin: 10px 0;
        }
        .employee-item a {
            text-decoration: none;
            color: #007bff;
        }
        .employee-item a:hover {
            text-decoration: underline;
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
    <div class="employee-container">
        <h2>Employees Under You</h2>
        <c:forEach var="employee" items="${employees}">
            <div class="employee-item">
                <a href="ViewEmployeeDetailServlet1?employeeId=${employee.employeeId}">
                    ${employee.employeeId} - ${employee.fullname}
                </a>
            </div>
        </c:forEach>
    </div>
    <a href="associateHome.jsp" class="back-button">Back</a> <!-- Adjust href as needed -->
</body>
</html>
