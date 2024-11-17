<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">

    <!-- Compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
</head>
<body>
<div class="container">
    <header class="row">
        <span>Hello, guest</span>
        <a href="${pageContext.request.contextPath}/pages/login.jsp">Login</a>
        <a href="${pageContext.request.contextPath}/pages/signup.jsp">Signup</a>
    </header>
    <img src="${pageContext.request.contextPath}/images/login.jpg" alt="main login image">
    <form action="${pageContext.request.contextPath}/controller" method="post">
        <label>
            <input type="text" name="username" value="" placeholder="enter username"/>
        </label>
        <label>
            <input type="password" name="password" value="" placeholder="enter password"/>
        </label>
        <input type="hidden" name="command" value="login">
        <input type="submit" name="sub" value="Login"/>
    </form>
    <div class="error-message">
        ${error}
    </div>
</div>
</body>
</html>
