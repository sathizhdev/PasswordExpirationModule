
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3"
      xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">

<head>
    <title>Login</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body>


<div class="container">

    <form th:action="@{/login}" method="POST" class="form-signin">
        <h3 class="form-signin-heading" th:text="Welcome"></h3>
        <br/>

        <input type="text" id="userName" name="userName"  th:placeholder="Username"
               class="form-control" /> <br/>
        <input type="password"  th:placeholder="Password"
               id="password" name="password" class="form-control" /> <br />

        <div align="center" th:if="${param.error}">
            <p style="font-size:large; color: #FF1C19;">Email or Password invalid, please verify</p>
        </div>

        <div align="center" th:if="${param.expired}">
            <div class="alert alert-warning" role="alert">
               Your Password Expired
            </div>
            <a href="/resetPassword"> <button type="button" class="btn btn-link">Click to Change Password</button>
            </a>
        </div>

        <div class="alert alert-success" role="alert" th:if="${param.changed}">
            Your Password Changed Successfully!
        </div>
        <button class="btn btn-lg btn-primary btn-block" name="Submit" value="Login" type="Submit" th:text="Login"></button>
    </form>
</div>
</body>
</html>