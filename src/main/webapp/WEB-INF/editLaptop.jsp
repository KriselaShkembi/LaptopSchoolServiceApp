<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>View and edit your laptop</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<h1>View and edit your laptop</h1>
<br>
<body>
<%--@elvariable id="laptop" type="projects"--%>
<form:form method="put" action="/laptop/${laptop.id}/edit" modelAttribute="laptop">
    <div class="form-group">
        <form:label path="description">Description:</form:label>
        <form:input path="description" class="form-control"></form:input>
        <form:errors path="description" class="text-danger"></form:errors>
    </div>
    <div class="form-group">
        <form:label path="screenSize">Screen Size (in inches):</form:label>
        <form:input path="screenSize" class="form-control" type="number"></form:input>
        <form:errors path="screenSize" class="text-danger"></form:errors>
    </div>
    <div class="form-group">
        <form:label path="RAM">RAM (in GB):</form:label>
        <form:input path="RAM" class="form-control" type="number"></form:input>
        <form:errors path="RAM" class="text-danger"></form:errors>
    </div>
    <div class="form-group">
        <form:label path="batteryTime">Battery time (in Hours):</form:label>
        <form:input path="batteryTime" class="form-control" type="number"></form:input>
        <form:errors path="batteryTime" class="text-danger"></form:errors>
    </div>
    <br>
    <button class="btn btn-info">Submit</button>
</form:form>
<a href="/dashboard" class="btn btn-warning">Cancel</a>

<form:form action="/laptop/${laptop.id}/delete" method="delete">
    <button class="btn btn-danger">Delete</button>
</form:form>

</body>
</html>