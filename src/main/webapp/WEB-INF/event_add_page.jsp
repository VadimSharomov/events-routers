<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>New event</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="container">
            <h3>New event</h3>
            <form role="form" class="form-horizontal" action="/event/add" method="post">
                <div class="form-event"></div>
                <div class="form-event"><input type="text" class="form-control" name="name" placeholder="Name"></div>
                <div class="form-event"><input type="text" class="form-control" name="location" placeholder="Location"></div>
                <div class="form-event"><input type="date" class="form-control" name="dateFrom" placeholder="Date from"></div>
                <div class="form-event"><input type="date" class="form-control" name="dateTo" placeholder="Date to"></div>
                <div class="form-event"><input type="submit" class="btn btn-primary" value="Add"></div>
            </form>
        </div>
    </body>
</html>