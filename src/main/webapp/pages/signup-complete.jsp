<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">

    <!-- Compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
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
<div class="container">
    <header class="row">
        <span>Hello, guest</span>
        <a href="${pageContext.request.contextPath}/pages/login.jsp">Login</a>
        <a href="${pageContext.request.contextPath}/pages/signup.jsp">Signup</a>
    </header>
    <div class="row">
        <img class="col s6" src="${pageContext.request.contextPath}/images/signup-complete.jpg" alt="main signup image">
        <div class="col s6">
            <h1>Signup complete!</h1>
            <p>Please check your inbox for activation link.</p>
        </div>
    </div>
</div>
</body>
</html>
