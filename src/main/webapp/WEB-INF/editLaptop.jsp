<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit my task</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<h1><c:out value="${show.title}"></c:out></h1>
<br>
<body>
<%--@elvariable id="show" type="projects"--%>
<form:form method="put" action="/shows/${show.id}/edit" modelAttribute="show">
    <div class="form-group">
        <p class="font-italic text-warning">Title must be unique</p>
        <form:label path="title">Title:</form:label>
        <form:input path="title" class="form-control"></form:input>
        <form:errors path="title" class="text-danger"></form:errors>
    </div>
    <div class="form-group">
        <form:label path="network">Network:</form:label>
        <form:input path="network" class="form-control" ></form:input>
        <form:errors path="network" class="text-danger"></form:errors>
    </div>
    <div class="form-group">
        <form:label path="description">Project Description:</form:label>
        <form:input path="description" class="form-control" type="textarea"></form:input>
        <form:errors path="description" class="text-danger"></form:errors>
    </div>
    <br>
    <button class="btn btn-info">Submit</button>
</form:form>
<a href="/shows" class="btn btn-warning">Cancel</a>

<%--
<a href="/shows/${show.id}/delete" class="btn btn-danger">Delete</a>
--%>
<form:form action="/shows/${show.id}/delete" method="delete">
    <button class="btn btn-danger">Delete</button>
</form:form>

</body>
</html>