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
    <script>
        function showAlert() {
            alert("PDF successfully generated!");
        }
    </script>
</head>
<body>
<h1>Welcome,  ${user.username}!</h1>
<p>Take a look at our school's laptop service!</p>
<a href="/logout">Logout</a>
<br>
<div class="p-3 mb-2 bg-light text-dark">
    <h2>Your personal laptops:</h2>
    <table class="table table-striped">
        <tr>
            <th class="text-primary">Description:</th>
            <th class="text-primary">Screen Size (in inches):</th>
            <th class="text-primary">RAM (in GB):</th>
            <th class="text-primary">Battery time (in Hours):</th>
        </tr>
        <c:forEach items="${userLaptops}" var="laptop">
            <tr>
                <td><a href="/laptop/${laptop.id}/edit"><c:out value="${laptop.description}" /></a></td>
                <td><c:out value="${laptop.screenSize}" /></td>
                <td><c:out value="${laptop.RAM}" /></td>
                <td><c:out value="${laptop.batteryTime}" /></td>
            </tr>
        </c:forEach>
    </table>
    <a href="/laptop/new">Add your new laptop</a>
</div>
<br>
<div>
    <h2>All the laptop parts that are available in the School-Service:</h2>
    <table class="table table-striped">
        <tr>
            <th class="text-primary">Id:</th>
            <th class="text-primary">Name:</th>
            <th class="text-primary">Description:</th>
            <th class="text-primary">Price:</th>
            <th class="text-primary">Stock:</th>
        </tr>
        <c:forEach items="${parts}" var="part">
            <tr>
                <td><c:out value="${part.id}" /></td>
                <td><a href="/part/${part.id}"><c:out value="${part.name}" /></a></td>
                <td><c:out value="${part.description}" /></td>
                <td><c:out value="${part.price}" /></td>
                <td><c:out value="${part.stock}" /></td>
            </tr>
        </c:forEach>
    </table>
    <a href="/part/new">Add a new laptop part</a>
</div>
<br>
<div>
    <h2>Tickets</h2>
    <table class="table table-striped">
        <tr>
            <th class="text-primary">Ticket id:</th>
            <th class="text-primary">Description:</th>
            <th class="text-primary">Status:</th>
            <th class="text-primary">Laptop Id:</th>
            <th class="text-primary">Laptop part used Id:</th>
            <th class="text-primary">Generate PDF</th>
        </tr>
        <c:forEach items="${tickets}" var="ticket">
            <tr>
                <td><a href="/ticket/${ticket.id}"><c:out value="${ticket.id}" /></a></td>
                <td><c:out value="${ticket.description}" /></td>
                <td><c:out value="${ticket.status}" /></td>
                <td><c:out value="${ticket.laptop.id}" /></td>
                <td><c:out value="${ticket.laptopPart.id}" /></td>
                <td>
                    <form:form action="/ticket/${ticket.id}/generatePdf" method="post">
                        <button onclick="showAlert()" class="btn btn-success">Generate PDF</button>
                    </form:form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <a href="/ticket/new">Add a new ticket</a>
</div>

</body>
</html>