<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login and registration</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<h1 class="text-primary">Book Club</h1>
<h2>A place of friends to share thoughts on books</h2>
<br>
<div class="container d-flex">
    <div class="container d-flex">
        <%--@elvariable id="newUser" type="javax"--%>
        <form:form action="/register" method="post" modelAttribute="newUser">
            <p class="text-info">Register</p>
            <p>
                <form:label path="username">Username:</form:label>
                <form:input path="username" class="form-control" type="text"></form:input>
                <form:errors path="username" class="text-danger"></form:errors>
            </p>
            <p>
                <form:label path="email">Email:</form:label>
                <form:input path="email" class="form-control" type="text"></form:input>
                <form:errors path="email" class="text-danger"></form:errors>
            </p>
            <p>
                <form:label path="password">Password:</form:label>
                <form:input path="password" class="form-control" type="password"></form:input>
                <form:errors path="password" class="text-danger"></form:errors>
            </p>
            <p>
                <form:label path="confirm">Confirm Password:</form:label>
                <form:input path="confirm" class="form-control" type="password"></form:input>
                <form:errors path="confirm" class="text-danger"></form:errors>
            </p>
            <button class="btn btn-primary">Submit</button>
        </form:form>
    </div>
    <br>
    <div class="container d-flex">
        <%--@elvariable id="newLogin" type="newLogin"--%>
        <form:form action="/login" method="post" modelAttribute="newLogin">
            <p class="text-info">Log in</p>
            <p>
                <form:label path="email">Email:</form:label>
                <form:input path="email" class="form-control" type="text"></form:input>
                <form:errors path="email" class="text-danger"></form:errors>
            </p>
            <p>
                <form:label path="password">Password:</form:label>
                <form:input path="password" class="form-control" type="password"></form:input>
                <form:errors path="password" class="text-danger"></form:errors>
            </p>
            <button class="btn btn-primary">Submit</button>
        </form:form>
    </div>
</div>
</body>
</html>