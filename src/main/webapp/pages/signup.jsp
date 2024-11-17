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
        <img class="col s6" src="${pageContext.request.contextPath}/images/signup.jpg" alt="main signup image">
        <form class="col s6" name="signup_form" action="${pageContext.request.contextPath}/controller" method="post">
            <div class="row">
                <div class="input-field col s12">
                    <input id="username" type="text" name="username" placeholder="Enter username" required>
                    <label for="username">Username</label>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12">
                    <input id="password" type="password" name="password" placeholder="Enter password" required>
                    <label for="password">Password</label>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12">
                    <input id="repeat_password" type="password" name="repeat_password" placeholder="Repeat password" required>
                    <label for="repeat_password">Repeat Password</label>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12">
                    <input id="firstname" type="text" name="firstname" placeholder="Enter firstname" required>
                    <label for="firstname">First Name</label>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12">
                    <input id="lastname" type="text" name="lastname" placeholder="Enter lastname" required>
                    <label for="lastname">Last Name</label>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12">
                    <input id="email" type="email" name="email" placeholder="Enter email" required>
                    <label for="email">Email</label>
                </div>
            </div>
            <input type="hidden" name="command" value="signup">
            <div class="row">
                <div class="col s12">
                    <button class="waves-effect waves-light btn" type="submit" name="sub">Sign Up</button>
                </div>
            </div>
        </form>
        <div id="error-message" style="color: red;">
            ${error}
        </div>
    </div>
</div>
</body>
</html>
