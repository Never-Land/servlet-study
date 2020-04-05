<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>商品列表</title>
</head>
<body>
    <div id="global">
        <table>
            <c:if test="${requestScope.productList != null}">
                <tr>
                    <td>商品名称</td>
                    <td>商品描述</td>
                    <td>商品价格</td>
                    <td>详情</td>
                </tr>
                <c:forEach items="${requestScope.productList}" var="product">
                    <tr>
                        <td>${product.name}</td>
                        <td>${product.description}</td>
                        <td>￥${product.price}</td>
                        <td><a href="productDetails?productId=${product.id}">查看</a></td>
                    </tr>
                </c:forEach>
            </c:if>
            <c:if test="${requestScope.productList == null}">
                <tr>
                    <td colspan="4" style="text-align: left">没有任何商品信息,请添加商品!</td>
                </tr>
            </c:if>
            <tr>
                <td colspan="4" style="text-align: left"><a href="productAdd">添加商品</a></td>
            </tr>
        </table>
    </div>
</body>
</html>
