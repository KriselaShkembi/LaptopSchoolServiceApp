<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Ticket</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<h1>Edit the information regarding the Ticket:</h1>
<br>
<body>
<%--@elvariable id="ticket" type="projects"--%>
<form:form method="put" action="/ticket/${ticket.id}/edit" modelAttribute="ticket">

    <div class="form-group">
        <form:label path="description">Description:</form:label>
        <form:input path="description" class="form-control"></form:input>
        <form:errors path="description" class="text-danger"></form:errors>
    </div>

    <div>
        <form:label path="laptop">Choose the laptop:</form:label>
        <form:select path="laptop">
            <c:forEach items="${laptops}" var="laptop">
                <form:option value="${laptop.id}" label="${laptop.description}" />
            </c:forEach>
        </form:select>
    </div>

    <div>
        <form:label path="laptopPart">Choose the laptop part:</form:label>
        <form:select path="laptopPart">
            <c:forEach items="${parts}" var="part">
                <form:option value="${part.id}" label="${part.name}" />
            </c:forEach>
        </form:select>
    </div>

    <div>
        <label for="status">Status:</label>
        <form:select path="status">
            <form:option value="OPEN" label="Open" />
            <form:option value="CLOSED" label="Closed" />
        </form:select>
    </div>



    <br>
    <button class="btn btn-info">Submit</button>
</form:form>
<a href="/dashboard" class="btn btn-warning">Cancel</a>



</body>
</html>