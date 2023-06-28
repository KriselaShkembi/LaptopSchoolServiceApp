<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<h1>Welcome,  ${user.username}!</h1>
<p>Books from everyone shelves:</p>
<a href="/logout">Logout</a>
<a href="/books/new">Add a to my shelf!</a>

<br>
<table class="table table-striped">
    <tr>
        <th class="text-primary">Id</th>
        <th class="text-primary">Title</th>
        <th class="text-primary">Author name</th>
        <th class="text-primary">Posted by</th>
    </tr>
    <c:forEach items="${books}" var="book">
        <tr>
            <td>${book.id}</td>
            <td><a href="/books/${book.id}">${book.title}</a></td>
            <td>${book.authorName}</td>
            <td><c:out value="${book.user.username}" /></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>