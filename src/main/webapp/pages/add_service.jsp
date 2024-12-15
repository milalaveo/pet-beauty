<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<html>
<head>
    <title>Add service!</title>
    <!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">

    <!-- Compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
</head>
<body>
<div class="container">
    <div class="row">
        <h1>Add New Service</h1>
    </div>

    <div class="row">
        <c:if test="${not empty error}">
            <div class="error">${error}</div>
        </c:if>
    </div>

    <div class="row">
        <form action="${pageContext.request.contextPath}/controller" method="post">
            <input type="hidden" name="command" value="saveService">
            <label>Name: <input type="text" name="name" required></label><br>
            <label>Description: <textarea name="description"></textarea></label><br>
            <label>Price: <input type="number" name="price" step="0.01" required></label><br>
            <button type="submit">Add Service</button>
        </form>
    </div>
</div>
</body>
</html>
