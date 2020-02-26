<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>获取、设置Java对象属性</title>
</head>
<body>
    <jsp:useBean id="employee" class="entity.Employee"/>
    <jsp:setProperty name="employee" property="name" value="大波妹"/>
    姓名:<jsp:getProperty name="employee" property="name"/>
</body>
</html>
