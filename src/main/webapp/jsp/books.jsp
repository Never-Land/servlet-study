<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--JSTL标签实现显示书本列表--%>
<html>
<head>
    <title>Book List</title>
    <style>
        table, tr, td{
            border: 1px solid brown;
        }
    </style>
</head>
<body>
    书本显示在普通表格
    <table>
        <tr>
            <td>书本编号</td>
            <td>书本名称</td>
            <td>书本价格</td>
        </tr>
        <c:forEach items="${requestScope.bookList}" var="book">
            <tr>
                <td>${book.bookNo}</td>
                <td>${book.bookName}</td>
                <td>${book.bookPrice}</td>
            </tr>
        </c:forEach>
    </table>
<br/>
    书本样式变化显示在表格
    <table>
        <tr style="background: #ababff">
            <td>书本编号</td>
            <td>书本名称</td>
            <td>书本价格</td>
        </tr>
        <c:forEach items="${requestScope.bookList}" var="book" varStatus="status">
            <c:if test="${status.count % 2 == 0}">
                <tr style="background: #474bff">
            </c:if>
            <c:if test="${status.count % 2 != 0}">
                <tr style="background: #ff4382">
            </c:if>
                <td>${book.bookNo}</td>
                <td>${book.bookName}</td>
                <td>${book.bookPrice}</td>
            </tr>
        </c:forEach>
    </table>
<br/>
    所有书本编号
    <c:forEach items="${requestScope.bookList}" var="book" varStatus="status">
        ${book.bookNo}<c:if test="${!status.last}">,</c:if>
    </c:forEach>
</body>
</html>
