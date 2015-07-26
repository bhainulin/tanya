<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Edit employee</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
</head>
<body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<div class="container">
<form:form method="PUT" action="/Task10-0.0.1-SNAPSHOT/employee" commandName="employee">

    <table width="100%">
		<tr>
			<td align="center"><a class="btn btn-default" href="/Task10-0.0.1-SNAPSHOT/employee" />Employee List</a></td>
		</tr>
	</table>
	<br/><br/>
	
	<form:hidden path="id" value="${employee.id}"/>
	<table class="table table-striped table-bordered">
		<tr>
			<td><b>name</b></td>
			<td><form:input path="name" size="30" value="${employee.name}"/></td>
		</tr>
		<tr>
			<td><b>gender</b></td>
			<td><form:input path="gender" size="5" value="${employee.gender}"/></td>
		</tr>
		<tr>
			<td><b>position</b></td>
			<td><form:input path="position" value="${employee.position}" size="30"/></td>
		</tr>
		<tr>
			<td colspan="2"><input class="btn btn-default" type="submit" value="Save" /></td>
		</tr>
	</table>
</form:form>
</div>
</body>
</html>