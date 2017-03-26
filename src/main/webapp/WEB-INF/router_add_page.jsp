<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>New router</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="container">
            <h3>New router</h3>
            <form role="form" class="form-horizontal" action="/router/add" method="post">
                        <select class="selectpicker form-control form-group" name="event">
                            <c:forEach items="${events}" var="event">
                                <option value="${event.id}">${event.name}</option>
                            </c:forEach>
                        </select>
                        <input class="form-control form-group" type="text" name="routerName" placeholder="Router name">
                        <input class="form-control form-group" type="text" name="apMac" placeholder="apMac">
                    <input type="submit" class="btn btn-primary" value="Add">
            </form>
        </div>

        <script>
            $('.selectpicker').selectpicker();
        </script>
    </body>
</html>