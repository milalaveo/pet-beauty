<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script>
        function validateForm() {
            const password = document.forms["signup_form"]["password"].value;
            const repeatPassword = document.forms["signup_form"]["repeat_password"].value;
            const errorDiv = document.getElementById("error-message");

            if (password !== repeatPassword) {
                errorDiv.textContent = "Passwords do not match. Please try again.";
                return false;
            } else {
                errorDiv.textContent = "";
            }
            return true;
        }
    </script>
</head>
<body>
<img src="../images/signup.jpg" alt="main signup image">
<form name="signup_form" action="controller" method="post">
    <label>
        <input type="text" name="username" value="" placeholder="enter username"/>
    </label>
    <label>
        <input type="password" name="password" value="" placeholder="enter password"/>
    </label>
    <label>
        <input type="password" name="repeat_password" value="" placeholder="repeat password"/>
    </label>
    <label>
        <input type="text" name="firstname" value="" placeholder="enter firstname"/>
    </label>
    <label>
        <input type="text" name="lastname" value="" placeholder="enter lastname"/>
    </label>
    <label>
        <input type="text" name="email" value="" placeholder="enter email"/>
    </label>
    <input type="hidden" name="command" value="signup">
    <input type="submit" name="sub" value="Sign up"/>
</form>
<div id="error-message" style="color: red;">
    ${error}
</div>
</body>
</html>
