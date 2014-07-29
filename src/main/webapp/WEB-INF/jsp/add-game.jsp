<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add a Game!</title>
</head>
<body>
<form:form method="post" modelAttribute="game" action="save-game">
    <form:label path="title">Title:</form:label>
    <form:input path="title"/><br>

    <input value="Submit" type="submit">
</form:form>

<br><br>
<a href="/">Take me back home</a>
</body>
</html>
