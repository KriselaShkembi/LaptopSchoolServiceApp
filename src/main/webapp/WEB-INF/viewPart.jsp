<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Project Detail</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<h1>Project details</h1>
<a href="/dashboard" >Back to dashboard</a>

<p>Project: ${project.projectName}</p>
<p>Description: ${project.description}</p>
<p>Due date: <fmt:formatDate value="${project.dueDate}" pattern="MMMM dd"/></p>
<br>
<a href="/projects/${project.id}/tasks">See tasks!</a>
<br>
<c:if test="${project.user.equals(user)}">
    <form:form action="/projects/${project.id}/delete" method="delete">
        <button class="btn btn-danger">Delete</button>
    </form:form>

</c:if>
</body>
</html>