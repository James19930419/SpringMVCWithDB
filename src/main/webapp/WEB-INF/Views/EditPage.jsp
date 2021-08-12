<%--
  Created by IntelliJ IDEA.
  User: James
  Date: 2021/7/28
  Time: 22:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
    <h1>Edit an category</h1>
    <form method="POST">
        <fieldset class="form-group">
            <label> Category code :</label>
            <label>${id}</label>
        </fieldset>
        <fieldset class="form-group">
            <label> Category description :</label>
            <input name="catdesc" type="text" class="form-control" required value="${desc}" />
        </fieldset>
        <input class="btn btn-success" type="submit" value="Submit" />
    </form>
</div>


<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>
</html>