<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Laptop Part Details</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<h1>Laptop Part details</h1>
<a href="/dashboard" >Back to dashboard</a>

<p>Name: ${part.name}</p>
<p>Description: ${part.description}</p>
<p>Price: ${part.price} $</p>
<p>Stock: ${part.stock} </p>
<br>

<button class="btn btn-info"><a href="/part/${part.id}/edit" >Edit</a></button>


<form:form action="/part/${part.id}/delete" method="delete">
    <button class="btn btn-danger">Delete</button>
</form:form>
</body>
</html>