<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit event</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <h3>Edit event</h3>
    <form role="form" class="form-horizontal" action="/event/edit" method="post">
        <input class="form-control form-group" type="text" name="name" placeholder="Event name"
               value="${event.name}">
        <input class="form-control form-group" type="text" name="location" placeholder="Location" value="${event.location}">
        <input class="form-control form-group" type="datetime" name="dateFrom" placeholder="Date from" value="${dateFrom}">
        <input class="form-control form-group" type="datetime" name="dateTo" placeholder="Date to" value="${dateTo}">
        <input type="hidden" name="id" value="${event.id}">
        <input type="submit" class="btn btn-primary" value="Update">
    </form>
</div>
</body>
</html>