<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" language="java" %>
<%--使用page指令的属性标记是个错误页面--%>
<html>
<head>
    <title>Error Page</title>
</head>
<body>
    一个错误页面处理<br/>
    错误信息:
    <%
        out.println(exception.toString());
    %>
</body>
</html>
