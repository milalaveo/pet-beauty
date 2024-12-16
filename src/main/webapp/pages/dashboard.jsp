<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<html>
<head>
    <title>Hello, ${username}!</title>
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

    <div class="row">
        <div class="col-12">
            <table>
                <thead>
                <tr>
                    <th>Service</th>
                    <th>Status</th>
                    <th>Execution Date</th>
                    <th>Execution Time</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="request" items="${requests}">
                    <tr>
                        <td>${request.serviceId}</td>
                        <td>${request.status}</td>
                        <td>${request.executionDate}</td>
                        <td>${request.executionTime}</td>
                        <td>
                            <form action="${pageContext.request.contextPath}/controller" method="post" style="display:inline">
                                <input type="hidden" name="command" value="edit_request">
                                <input type="hidden" name="id" value="${request.id}">
                                <button type="submit">Edit</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <div class="row">
            <a href="${pageContext.request.contextPath}/controller?command=edit_request">Add request</a>
        </div>
    </div>
</div>
</body>
</html>
