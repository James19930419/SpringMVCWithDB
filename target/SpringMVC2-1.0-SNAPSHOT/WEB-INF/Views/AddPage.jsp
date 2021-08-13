


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<html>
<head>
    <title>Title</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
    <h1>Add an entry</h1>
    <form method="POST">
        <fieldset class="form-group">
            <label> Customer number :</label>
            <input name="custno" type="text" class="form-control" required value="${custno}" />
        </fieldset>

        <fieldset class="form-group">
            <label> Customer name :</label>
            <input name="custname" type="text" class="form-control" required value="${custname}" />

        </fieldset>

        <fieldset class="form-group">
            <label> Customer deposit :</label>
            <input name="cdep" type="text" class="form-control" required value="${cdep}" />

        </fieldset>
        <fieldset class="form-group">
            <label> Number of years :</label>
            <input name="nyears" type="text" class="form-control" required value="${nyears}" />

        </fieldset>
        <fieldset class="form-group">
                    <form:select path = "savingType" name="savingType">
                        <form:option value = "NONE" label = "Please Select"/>
                        <form:options items = "${savingType}" />
                    </form:select>
        </fieldset>

        <input class="btn btn-success" type="submit" value="Submit" />
    </form>

</div>


<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>
</html>
