<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Routers</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body>

<div class="container">
    <h3>Routers ${selectedEvent}</h3>

    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul id="groupList" class="nav navbar-nav">
                    <li class="active">
                        <button type="button" class="btn btn-primary navbar-btn"
                                onclick="window.location.href='/events'">Go to events
                        </button>
                    </li>
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
            <td><b>Router apMac</b></td>
            <td><b>Router event</b></td>
        </tr>
        </thead>
        <c:forEach items="${routers}" var="router">
            <tr>
                <td><input class="idCheckbox" type="checkbox" name="toDelete[]" value="${router.id}"
                           id="checkbox_${router.id}"/></td>
                <td>${router.getName()}</td>
                <td>${router.apMac}</td>
                <td>${router.event}</td>
            </tr>
        </c:forEach>
    </table>
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
        if (data.length!=0 && confirm('Are you sure want to delete router?')) {
            $.post("/router/delete", data, function (data, status) {
                window.location.reload();
            });
        }
    });
</script>
</body>
</html>