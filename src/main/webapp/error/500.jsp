<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Server Error</title>
</head>
<body>
<h1>Error 500 - Server Error</h1>
<p>Sorry, something went wrong on our end. Please try again later. Error details:</p>
<ul>
    <li><strong>Request from:</strong> ${pageContext.errorData.requestURI}</li>
    <li><strong>Servlet name:</strong> ${pageContext.errorData.servletName}</li>
    <li><strong>Status code:</strong> ${pageContext.errorData.statusCode}</li>
    <li><strong>Exception:</strong> ${pageContext.exception}</li>
    <li><strong>Message from exception:</strong> ${pageContext.exception.message}</li>
</ul>
</body>
</html>