<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<html>
<head>
    <title>商品详情</title>
</head>
<body>
    <div id="global">
        <h4>商品保存成功,详情如下:</h4>
        <p>
            商品名称:${product.name}<br/>
            商品描述:${product.description}<br/>
            商品价格:￥${product.price}<br/>
        </p>
        <a href="productList">商品列表</a>
    </div>
</body>
</html>
