<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--
    include指令和include动作区别:
1.include指令发生在页面转换,而include动作发生在请求
2.include指令被包含的文件扩展名并不重要,而include动作文件
    扩展名必须是jsp文件
-->
<html>
<head>
    <title>jsp:include动作</title>
</head>
<body>
    <jsp:include page="lifeCycle.jsp">
        <jsp:param name="text" value="只是演示下"/>
    </jsp:include>
    <%--forward动作使页面跳转到另一个页面--%>
    <jsp:forward page="implicitObjects.jsp">
        <jsp:param name="text" value="jsp隐式对象演示"/>
    </jsp:forward>
</body>
</html>
