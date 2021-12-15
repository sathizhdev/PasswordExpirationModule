<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">

<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body>
    <h1>Welcome</h1>
    <div th:fragment="name(message)">
            <div th:if="${message != null}" class="alert alert-warning">
                <span th:text="${message}"></span>
                <a th:if="${message != null}" href="/resetPassword">Click to reset your Password</a>
        </div>
    </div>
</body>
</html>