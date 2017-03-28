<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Events</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body>

<div class="container">
    <h3>Events</h3>

    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul id="groupList" class="nav navbar-nav">
                    <li class="active">
                    <button type="button" class="btn btn-primary navbar-btn" onclick="window.location.href='/'">Go to routers</button>
                    </li>
                    <li>
                        <button type="button" id="add_event" class="btn btn-default navbar-btn">Add event</button>
                    </li>
                    <li>
                     <form action="/event/edit_page" method="post">
                            <input type="hidden" class="event_id" name="event_id" value="">
                            <button type="submit" id="edit_event" class="btn btn-default navbar-btn">Edit event
                            </button>
                     </form>
                    </li>
                    <li>
                        <button type="button" id="delete_event" class="btn btn-default navbar-btn">Delete event
                        </button>
                    </li>
                </ul>
                <form class="navbar-form navbar-left" role="search" action="/event/search" method="post">
                    <div class="form-group">
                        <input type="text" class="form-control" name="pattern" placeholder="Search event">
                    </div>
                    <button type="submit" class="btn btn-default">Submit</button>
                </form>

            </div>
        </div>
    </nav>

    <table class="table table-striped">
        <thead>
        <tr>
            <td></td>
            <td><b>Event name</b></td>
            <td><b>Event location</b></td>
            <td><b>Event dateFrom</b></td>
            <td><b>Event dateTo</b></td>
        </tr>
        </thead>
        <c:forEach items="${events}" var="event">
            <tr>
                <td><input class="idCheckbox" type="checkbox" name="toDelete[]" value="${event.id}" id="checkbox_${event.id}"/></td>
                <td>${event.name}</td>
                <td>${event.location}</td>
                <td>${event.dateFrom}</td>
                <td>${event.dateTo}</td>
            </tr>
        </c:forEach>
    </table>

    <nav aria-label="Page navigation">
        <ul class="pagination">
            <c:if test="${allPages ne null}">
                <c:forEach var="i" begin="1" end="${allPages}">
                    <li><a href="/?page=<c:out value="${i - 1}"/>"><c:out value="${i}"/></a></li>
                </c:forEach>
            </c:if>
            <c:if test="${byGroupPages ne null}">
                <c:forEach var="i" begin="1" end="${byGroupPages}">
                    <li><a href="/event/${event_id}?page=<c:out value="${i - 1}"/>"><c:out value="${i}"/></a></li>
                </c:forEach>
            </c:if>
        </ul>
    </nav>
</div>

<script>
    $('.dropdown-toggle').dropdown();

    $('#add_event').click(function () {
        window.location.href = '/event_add_page';
    });

    $('#edit_event').click(function () {
        var data = $('.idCheckbox:checked').val();
        $('.event_id').val(data)
    });

    $('#delete_event').click(function () {
        var data = {'toDelete[]': []};
        $(":checked").each(function () {
            data['toDelete[]'].push($(this).val());
        });
        if (confirm('Are you sure want to delete event?')) {
            $.post("/event/delete", data, function (data, status) {
                window.location.reload();
            });
        }
    });

</script>
</body>
</html>