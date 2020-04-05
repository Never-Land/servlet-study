<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>添加商品</title>
</head>
<body>
    <div id="global">
        <h3>添加一个商品</h3>
        <c:if test="${requestScope.errorList != null}">
            <p id="errorList">
                错误信息:
                <ul>
                    <c:forEach items="${requestScope.errorList}" var="error">
                        <li>${error}</li>
                    </c:forEach>
                </ul>
            </p>
        </c:if>
        <form method="post" action="productSave">
            <table>
                <tr>
                    <td>商品名称:</td>
                    <td><input type="text" name="name" value="${productForm.name}"/></td>
                </tr>
                <tr>
                    <td>商品描述:</td>
                    <td><input type="text" name="description" value="${productForm.description}"/></td>
                </tr>
                <tr>
                    <td>商品价格:</td>
                    <td><input type="text" name="price" value="${productForm.price}"/></td>
                </tr>
                <tr>
                    <td><input type="reset" value="重置"></td>
                    <td><input type="submit" value="添加"/></td>
                </tr>
                <tr>
                    <td colspan="2"><a href="productList">商品列表</a></td>
                </tr>
            </table>
        </form>
    </div>
</body>
</html>
