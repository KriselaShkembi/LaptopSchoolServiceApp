<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Laptop Part</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<h1>Edit the information regarding the laptop part:</h1>
<br>
<body>
<%--@elvariable id="part" type="projects"--%>
<form:form method="put" action="/part/${part.id}/edit" modelAttribute="part">
    <div class="form-group">
        <form:label path="name">Name of the laptop part:</form:label>
        <form:input path="name" class="form-control" ></form:input>
        <form:errors path="name" class="text-danger"></form:errors>
    </div>
    <div class="form-group">
        <form:label path="description">Description:</form:label>
        <form:input path="description" class="form-control"></form:input>
        <form:errors path="description" class="text-danger"></form:errors>
    </div>

    <div class="form-group">
        <form:label path="price">Price:</form:label>
        <form:input path="price" class="form-control" type="number"></form:input>
        <form:errors path="price" class="text-danger"></form:errors>
    </div>
    <div class="form-group">
        <form:label path="stock">Stock:</form:label>
        <form:input path="stock" class="form-control" type="number"></form:input>
        <form:errors path="stock" class="text-danger"></form:errors>
    </div>
    <br>
    <button class="btn btn-info">Submit</button>
</form:form>
<a href="/dashboard" class="btn btn-warning">Cancel</a>



</body>
</html>