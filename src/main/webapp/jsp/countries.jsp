<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Country List</title>
</head>
<body>
    我们可以操作的国家:
        <ul>
            <c:forEach items="${countriesMap}" var="countries">
                <li>${countries.value}</li>
            </c:forEach>
        </ul>
</body>
</html>
