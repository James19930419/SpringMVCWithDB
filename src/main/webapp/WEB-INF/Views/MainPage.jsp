<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %><%--
  Created by IntelliJ IDEA.
  User: James
  Date: 2021/7/28
  Time: 22:37
  To change this template use File | Settings | File Templates.
--%>



<%--/**--%>
<%--* 1:~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~--%>
<%--* ModelMap 就是NSDictionary 用来从java里向JSP传参。--%>
<%--* ModelMap.addAttribute("key",object) 或 ModelMap.put("key",object)是一样的，不过put可传递NULL--%>
<%--*--%>
<%--* jsp调取时通过${"key"}来实现--%>
<%--*--%>
<%--* 2:~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~--%>
<%--* @RequestParam 可以从JSP调取参数，--%>
<%--* 如果 @RequestParam(value="age"，required=true)Integer age1 则age参数为空是报错--%>
<%--*--%>
<%--* 注意只能取出有指针的object，所以不能用int(用Integer)--%>
<%--*--%>
<%--* 需要赋值一个默认值，写成如下的形式：--%>
<%--* @RequestParam(value = "age", required = false, defaultValue = "0") int age1)--%>
<%--*--%>
<%--* 可以通过 href="update-todo?id=${todo.catcode}" 里面的 ?key=value 来向@RequestParam 传参--%>
<%--*--%>
<%--* 也可以在任何表格等用到POST 或 GET的部件里传参，参数名写在 name='key' 里， 参数值写在 value='value'里即可--%>
<%--*--%>
<%--* 只要发送请求的时候URL上有 ? key=value 即可用 @RequestParam 取到 value,--%>
<%--* 所以只要注意浏览器的url就知道能取出来什么参数了！！！！！！！--%>
<%--* 3:~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~--%>
<%--*   @Autowired--%>
<%--*   attribute 相当于自动写gettter setter--%>
<%--*--%>
<%--* 4:~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~--%>
<%--* @RequestMapping(value = "/", method = RequestMethod.GET)--%>
<%--* 可以在url尾缀里有对应的字段时（这里是/ 也就是根目录），并且是GET/POST 请求时 trigger下面的方法--%>
<%--* 注意尾缀要和 JSP 中的 href 或者 browser上的URL尾缀相同--%>
<%--*/--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%--prefix就是前缀的意思。里面的bean,html,c 等是可以自己随便命名的，但是编码规范一般都那样定了，别人看了容易理解。主要uri里的内容决定了这个标签是干嘛用的。就想导入一个类一样，bean，html，c等都是你自定义的对象。--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<%--org.apache.catalina.core.ContainerBase.[Catalina].level=INFO--%>
<%--org.apache.catalina.core.ContainerBase.[Catalina].handlers=java.util.logging.ConsoleHandler--%>

<%--@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })--%>

<html>
<head>
    <title>Category Page</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

    <style>
        h1 {
            text-align:center;
            background-color: cyan;

        }

        .btn {

            width: 100%;
        }


    </style>
</head>
<body>
<h1>Item Category</h1>



<div class="container">

    <form method="POST">
        <div class="form-group">
            <label for="ccode">Category Code:</label>
            <%--用${key}来调取来自controller里的model里的参数layout--%>
            <%--用name 和 value 来向 @RequestParam里传参--%>
            <%--@RequestParam String catcode, @RequestParam String catdesc--%>
                <input type="text" name="catcode" class="form-control" id="ccode" value="${id}">
        </div>
        <div class="form-group">
            <label for="cdesc">Description:</label>
            <input type="text" name="catdesc" class="form-control" id="cdesc" value="${desc}">
        </div>

        <form:select path = "country" name="country">
            <form:option value = "NONE" label = "Please Select"/>
            <form:options items = "${countryList}" />
        </form:select>
        <br/>
        <%--href里的尾缀触发Controller去处理--%>
        <%--已经在tags里宏定义了此URL尾缀，更改即可--%>
        <%--        <a class="btn btn-success" href="add-todo">Add</a>--%>
        <input class="btn btn-success" type="submit" value="Add" />

    </form>
    <h1 id="mes">${errorMessage}</h1>
    <div class="container2">
        <h2>Categories</h2>

        <table class="table table-striped">
            <thead>
            <tr>
                <th>Category Code</th>
                <th>Category Description</th>
            </tr>
            </thead>
            <tbody>
            <%--此处将key为todos的参数取出来重命名为todo存入model里--%>
            <c:forEach items="${todos}" var="todo">

                <tr>
                    <%--通过点式调用--%>
                    <td>${todo.catcode}</td>
                    <td>${todo.catdesc}</td>

                    <td>    <a type="button" class="btn btn-primary"
                               href="update-todo?id=${todo.catcode}" >Edit</a> </td>
                                    <%--href="update-todo?" 里面的值要写得和Java里@RequestMapping里的路由URL后缀一致  --%>
                    <td>    <a type="button" class="btn btn-primary"
                               href="delete-todo?id=${todo.catcode}" >Delete</a> </td>

                    <td>    <a type="button" class="btn btn-primary" onmouseout="myFunction()"
                               href="see-todo?id=${todo.catcode}" >Items</a> </td>
                </tr>

            </c:forEach>
            </tbody>
        </table>
    </div>

</div>

<script>
function myFunction(){
    document.getElementById("mes").innerHTML = "";
}




</script>

</body>
</html>