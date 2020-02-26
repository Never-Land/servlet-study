<%--雇员信息页面--%>
<!--isELIgnored指定该属性是因为JSP2.0之前不支持EL表达式-->
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<html>
<head>
    <title>Employee</title>
</head>
<body>
    accept-language : ${header['accept-language']}<br/>
    session id : ${pageContext.session.id}<br/>
    employee : ${requestScope.employee.name}, ${employee.address.city}<br/>
    capital : ${capitalMap.get("China")}
</body>
</html>
