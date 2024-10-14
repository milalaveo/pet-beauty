<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Main</title>
</head>
<body>
<h2>Equation result:</h2>
<b>${input} * ${multiplier} = ${result}</b>
<br>
<form action="controller">
    <a href="${pageContext.request.contextPath}">Back to form</a>
</form>
</body>
</html>