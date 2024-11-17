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
        <span>Hello, ${username}</span>
        <a href="${pageContext.request.contextPath}/controller?command=logout">Logout</a>
    </header>
    <img src="${pageContext.request.contextPath}/images/dashboard.jpg" alt="main dashboard image">
</div>
</body>
</html>
