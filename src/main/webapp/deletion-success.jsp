<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Deletion Successful</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            margin: 0;
            padding: 0;
            height: 100vh;
            background: linear-gradient(90deg, #a6a6a6, #ffffff);
            /* Removed background-image */
            background-size: cover;
            background-position: center;
            background-repeat: no-repeat;
            background-attachment: fixed;
            display: flex;
            justify-content: center;
            align-items: flex-start; /* Align items to the top */
        }
        .container {
            width: 60%;
            max-width: 600px; /* Adjust max-width as needed */
            background-color: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); /* Optional: Adds a shadow */
            margin-top: 20px; /* Add margin-top to create some space from the top */
        }
        .success-message {
            color: green;
            font-size: 1.5em;
            margin-bottom: 20px;
        }
        .account-details {
            margin-top: 20px;
            font-size: 1.1em;
            color: #000000;
        }
        .redirect-link {
            display: inline-block;
            margin-top: 20px;
            color: #007bff;
            text-decoration: none;
            font-weight: bold;
            padding: 10px 20px;
            border: 2px solid #007bff;
            border-radius: 5px;
            transition: all 0.3s ease;
        }
        .redirect-link:hover {
            background-color: #007bff;
            color: white;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2 class="success-message">Account Deletion Successful!</h2> 
        <div class="account-details">
            <p>Your account has been deleted successfully.</p>
        </div>
        <a href="adminHome.jsp" class="redirect-link">Go Back to Home</a>
    </div>
</body>
</html>