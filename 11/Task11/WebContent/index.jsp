<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Publish message</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
</head>
<body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>	

<div class="container">
<br/><br/><br/>
Publish message
<form method="POST" action="publish">
    <label>Topic</label> <input type="text" name="topic" value="${last}"><br/>
    <label>Message</label> <input type="text" name="message"><br/>
    <input class="btn btn-default" class="button" type="submit"/>
</form>
<hr/>
<c:if test="${respCode eq 200}">
    Message sent.
</c:if>
<c:if test="${respCode eq 500}">
    <c:out value="${respMess}"/>
</c:if>

</div>
</body>
</html>
