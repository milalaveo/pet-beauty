<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1>"Let's do some math!"</h1>
<%--<form action="controller" method="get">--%>
<%--    <label>--%>
<%--        <input type="text" name="num" value="" placeholder="enter any number here"/>--%>
<%--    </label>--%>
<%--    <input type="hidden" name="command" value="multiply">--%>
<%--    <input type="submit" name="sub" value="Multiply"/>--%>
<%--</form>--%>
<jsp:forward page="pages/login.jsp"/>
</body>
</html>