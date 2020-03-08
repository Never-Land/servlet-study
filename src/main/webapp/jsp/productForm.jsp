<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<html>
<head>
    <title>添加商品</title>
</head>
<body>
    <div id="global">
        <h3>添加一个商品</h3>
        <form method="post" action="productSave">
            <table>
                <tr>
                    <td>商品名称:</td>
                    <td><input type="text" name="name"/></td>
                </tr>
                <tr>
                    <td>商品描述:</td>
                    <td><input type="text" name="description"/></td>
                </tr>
                <tr>
                    <td>商品价格:</td>
                    <td><input type="text" name="price"/></td>
                </tr>
                <tr>
                    <td><input type="reset" value="重置"></td>
                    <td><input type="submit" value="添加"/></td>
                </tr>
            </table>
        </form>
    </div>
</body>
</html>
