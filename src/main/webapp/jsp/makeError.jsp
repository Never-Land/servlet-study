<%@ page contentType="text/html;charset=UTF-8" errorPage="errorHandler.jsp" language="java" %>
<html>
<head>
    <%--制造错误场景演示Jsp错误页面--%>
    <title>制造错误</title>
</head>
<body>
    抛出异常信息:
    <%
        Integer.parseInt("Throw me");
    %>
</body>
</html>
