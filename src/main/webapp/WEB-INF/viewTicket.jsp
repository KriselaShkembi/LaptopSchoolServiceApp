<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Ticket Details</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<h1>Ticket details</h1>
<a href="/dashboard" >Back to dashboard</a>

<p>Id: ${ticket.id}</p>
<p>Description: ${ticket.description}</p>
<p>Ticket status: ${ticket.status}</p>
<p>The laptop that will be repaired has the id: ${ticket.laptop.id} </p>
<p>The laptop part that will be used has the id: ${ticket.laptopPart.id} </p>

<br>

<button class="btn btn-info"><a href="/ticket/${ticket.id}/edit" >Edit</a></button>


<form:form action="/ticket/${ticket.id}/delete" method="delete">
    <button class="btn btn-danger">Delete</button>
</form:form>
</body>
</html>