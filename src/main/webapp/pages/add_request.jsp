<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<html>
<head>
    <title>Hello, ${username}!</title>
    <!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">

    <script src="https://code.jquery.com/jquery-3.7.1.slim.min.js" integrity="sha256-kmHvs0B+OpCW5GVHUNjv9rOmY0IvSIRcf7zGUDTDQM8=" crossorigin="anonymous"></script>

    <!-- Compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
</head>
<body>
<div class="container">
    <div class="row">
        <h1>${request != null ? "Edit Request" : "Add New Request"}</h1>
    </div>


    <div class="row">
        <div class="col s12">
            <c:if test="${not empty error}">
                <div class="error">${error}</div>
            </c:if>
        </div>
    </div>

    <div class="row">
        <div class="col s12">
            <div class="row">
                <form class="col s12" action="${pageContext.request.contextPath}/controller" method="post">
                    <input type="hidden" name="command" value="save_request">
                    <c:if test="${request != null}">
                        <input type="hidden" name="id" value="${request.id}">
                    </c:if>
                    <div class="row">
                        <div class="input-field col s12">
                            <select name="service">
                                <c:forEach var="service" items="${services}">
                                    <option value="${service.id}" <c:if
                                            test="${service.id == request.serviceId}">selected</c:if>>${service.name}</option>
                                </c:forEach>
                            </select>
                            <label>Service</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12">
                            <input type="date" name="executionDate"
                                   value="${request != null ? request.executionDate : ''}"
                                   required>
                            <label>Execution Date</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12">
                            <input type="time" name="executionTime"
                                   value="${request != null ? request.executionTime : ''}"
                                   required>
                            <label>Execution Time</label>
                        </div>
                    </div>
                    <button type="submit">${request != null ? "Save Changes" : "Add Request"}</button>
                </form>
            </div>
        </div>
    </div>
</div>
<script>
    $(document).ready(function(){
        $('select').formSelect();
    });
</script>
</body>
</html>
