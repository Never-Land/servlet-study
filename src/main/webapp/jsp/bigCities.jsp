<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Big Cities</title>
    <style>
        table, tr, td{
            border: 1px solid #aaee77;
            padding: 3px;
        }
    </style>
</head>
<body>
    首都
    <table>
        <tr style="background: crimson;font-weight: bold">
            <td>国家</td>
            <td>首都</td>
        </tr>
        <c:forEach items="${requestScope.capitalMap}" var="capital">
            <tr>
                <td>${capital.key}</td>
                <td>${capital.value}</td>
            </tr>
        </c:forEach>
    </table>
    大城市
    <table>
        <tr style="background: darkviolet; font-weight: bold">
            <td>国家</td>
            <td>大城市</td>
        </tr>
        <c:forEach items="${requestScope.bigCitiesMap}" var="bigCities">
            <tr>
                <td>${bigCities.key}</td>
                <td>
                    <c:forEach items="${bigCities.value}" var="bigCity" varStatus="status">
                        ${bigCity}<c:if test="${!status.last}">,</c:if>
                    </c:forEach>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
