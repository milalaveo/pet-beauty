<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<img src="images/login.jpg" alt="main login image">
<form action="controller" method="post">
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
</body>
</html>
