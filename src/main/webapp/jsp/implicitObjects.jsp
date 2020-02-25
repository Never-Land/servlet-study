<!--Jsp隐式对象演示-->
<!--
    page指令中有contentType、pageEncoding属性时必须放所有的模板数据
之前,因为必须在发送之前设置内容类型和字符编码
-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Enumeration" %>
<html>
<head>
    <title>Jsp隐式对象演示</title>
</head>
<body>
<b>Http headers:</b><br/>
<%
    Enumeration enumeration = request.getHeaderNames();
    for(;enumeration.hasMoreElements();){
        String header = (String) enumeration.nextElement();
        out.print(header + ":" + request.getHeader(header));
        out.print("<br/>");
    }
%>
<hr/>
<%
    out.println("Buffer size:" + response.getBufferSize());
    out.print("<br/>");
    out.println("Session id:" + session.getId());
    out.print("<br/>");
    out.println("Servlet name:" + config.getServletName());
    out.print("<br/>");
    out.println("Servlet info:" + application.getServerInfo());
%>
</body>
</html>
