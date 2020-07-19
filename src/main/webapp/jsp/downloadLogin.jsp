<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<html>
<head>
    <%--下载文件没有登录界面--%>
    <title>Download Login</title>
</head>
<body>
    <form action="downloadLogin" method="post">
        <table>
            <tr>
                <td>姓名:</td>
                <td><input name="userName" type="text"/></td>
            </tr>
            <tr>
                <td>密码:</td>
                <td><input name="userPassword" type="password"/></td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="提交"/>
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
