<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add a Game!</title>
</head>
<body>
<form:form method="GET" modelAttribute="game" action="save-game">
    <form:label path="title">Title:</form:label>
    <form:input path="title"></form:input>

    <input value="Submit" type="submit">
</form:form>
</body>
</html>
