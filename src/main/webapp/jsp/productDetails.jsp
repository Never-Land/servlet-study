<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<html>
<head>
    <title>商品详情</title>
</head>
<body>
    <div id="global">
        <h4>商品保存成功,详情如下:</h4>
        <input type="hidden" name="id" value="${product.id}"/>
        <p>
            商品名称:${product.name}<br/>
            商品描述:${product.description}<br/>
            商品价格:￥${product.price}<br/>
        </p>
    </div>
</body>
</html>
