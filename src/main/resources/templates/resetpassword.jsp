<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3"
      xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">
<head>
    <title>Reset Your Password</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body>

<div style="align-content: center" >
    <h2>Change Your Expired Password</h2>
</div>

<form th:action="@{/changePassword}" method="POST" style="max-width: 350px; margin: 0 auto;">
    <div class="border border-secondary rounded p-3">
        <div>
            <p>
                <input type="password" name="oldPassword" class="form-control"
                       placeholder="Old Password" required autofocus />
            </p>
            <p>
                <input type="password" name="newPassword" id="newPassword" class="form-control"
                       placeholder="New password" required />
            </p>
            <p>
                <input type="password" class="form-control" placeholder="Confirm new password"
                       required oninput="checkPasswordMatch(this);" />
            </p>
            <p class="text-center">
                <input type="submit" value="Change Password" class="btn btn-primary" />
            </p>
        </div>
    </div>
</form>

<script>

    function checkPasswordMatch(fieldConfirmPassword) {
        if (fieldConfirmPassword.value != $("#newPassword").val()) {
            fieldConfirmPassword.setCustomValidity("Passwords do not match!");
        } else {
            fieldConfirmPassword.setCustomValidity("");
        }
    }

</script>

</body>