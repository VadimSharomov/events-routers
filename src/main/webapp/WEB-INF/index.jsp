<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Events of routers</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>

<body>

<div class="container">
    <h3>Events of routers</h3>

    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul id="groupList" class="nav navbar-nav">
                    <li>
                        <button type="button" id="add_router" class="btn btn-default navbar-btn">Add router</button>
                    </li>
                    <li>
                     <form action="/router/edit" method="post">
                            <input type="hidden" class="router_id" name="router_id" value="">
                            <button type="submit" id="edit_router" class="btn btn-default navbar-btn">Edit router
                            </button>
                     </form>
                    </li>
                    <li>
                        <button type="button" id="delete_router" class="btn btn-default navbar-btn">Delete router
                        </button>
                    </li>
                    <li>
                        <button type="button" id="add_event" class="btn btn-default navbar-btn">Add event</button>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                           aria-expanded="false">Events <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="/">All events</a></li>
                            <c:forEach items="${events}" var="event">
                                <li><a href="/event/${event.id}">${event.name}</a></li>
                            </c:forEach>
                        </ul>
                    </li>
                </ul>
                <form class="navbar-form navbar-left" role="search" action="/search" method="post">
                    <div class="form-group">
                        <input type="text" class="form-control" name="pattern" placeholder="Search router">
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
            <td><b>Router name</b></td>
            <td><b>apMac</b></td>
            <td><b>Event</b></td>
        </tr>
        </thead>
        <c:forEach items="${routers}" var="router">
            <tr>
                <td><input class="idCheckbox" type="checkbox" name="toDelete[]" value="${router.id}" id="checkbox_${router.id}"/></td>
                <td>${router.getName()}</td>
                <td>${router.apMac}</td>
                <td>${router.event}</td>
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

    $('#add_router').click(function () {
        window.location.href = '/router_add_page';
    });

    $('#edit_router').click(function () {
        var data = $('.idCheckbox:checked').val();
        $('.router_id').val(data)
    });

    $('#delete_router').click(function () {
        var data = {'toDelete[]': []};
        $(":checked").each(function () {
            data['toDelete[]'].push($(this).val());
        });
        $.post("/router/delete", data, function (data, status) {
            window.location.reload();
        });
    });

    $('#add_event').click(function () {
        window.location.href = '/event_add_page';
    });
</script>
</body>
</html>