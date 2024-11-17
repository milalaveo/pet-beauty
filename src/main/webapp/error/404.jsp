<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Page Not Found</title>
</head>
<body>
<h1>Error 404 - Page Not Found</h1>
<p>Sorry, the page you are looking for does not exist. Error details:</p>
<ul>
  <li><strong>Request from:</strong> ${pageContext.errorData.requestURI}</li>
  <li><strong>Servlet name:</strong> ${pageContext.errorData.servletName}</li>
  <li><strong>Status code:</strong> ${pageContext.errorData.statusCode}</li>
</ul>
</body>
</html>