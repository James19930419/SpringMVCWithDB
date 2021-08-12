<%--
  Created by IntelliJ IDEA.
  User: James
  Date: 2021/8/7
  Time: 14:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

    <title>Items</title>
</head>
<body>
    <div class="container">
        <h1>${id}</h1>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Item Code</th>
                <th>Item Description</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${itemsList}" var = "oneItem">
                <tr>
                    <td>${oneItem.itemcode}</td>
                    <td>${oneItem.itemdesc}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <form method="POST">
            <input class="btn btn-back" type="submit" value="Submit">
        </form>
    </div>
</body>
</html>
