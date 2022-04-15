<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (dates) -->
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Show a Dojo and all it's Ninjas</title>
</head>
<body>
	<h1>${dojo.name}</h1>
	<c:forEach items="${dojo.ninjas }" var="ninja"> 
	<ul>
		<li> First Name : ${ninja.firstName }</li>
		<li> Last Name  : ${ninja.lastName }</li>
		<li> Age : ${ninja.age }</li>
	</ul>
	</c:forEach>
</body>
</html>