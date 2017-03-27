<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Edit router</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="container">
            <h3>Edit router</h3>
            <form role="form" class="form-horizontal" action="/router/editRouter" method="post">
                        <select class="selectpicker form-control form-group" name="event">
                            <c:forEach items="${events}" var="event">
                                <option value="${event.id}">${event.name}</option>
                            </c:forEach>
                        </select>
                        <input class="form-control form-group" type="text" name="routerName" placeholder="Router name" value="${router.getName()}">
                        <input class="form-control form-group" type="text" name="apMac" placeholder="apMac" value="${router.apMac}">
                        <input type="hidden" name="id" value="${router.id}">
                    <input type="submit" class="btn btn-primary" value="Update">
            </form>
        </div>

        <script>
            $('select[name=event]').val('${router_event.id}');
            $('.selectpicker form-control form-group').selectpicker('refresh');
        </script>
    </body>
</html>