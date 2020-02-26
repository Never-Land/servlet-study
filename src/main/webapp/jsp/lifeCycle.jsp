<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--jsp中声明方法和变量--%>
<!--
    随着JSP2.0中的Expression Language发展,建议使用EL表达式
来访问服务器的对象,而不在JSP中书写Java代码,可以通过标签关闭开
启的脚本元素.
    <jsp-property-group>
        <url-pattern>*.jsp</url-pattern>
        <scripting-invalid>true</scripting-invalid>
    </jsp-property-group>
-->
<%!
    public void jspInit(){
        System.out.println("jspInit...");
    }
    public void jspDestroy(){
        System.out.println("jspDestroy...");
    }
    private String variable = "变量";
%>
<html>
<head>
    <title>Life Cycle</title>
</head>
<body>
    继承并且覆盖Jsp中的jspInit和jspDestroy<br/>
    <%
        variable = "定义" + variable;
        out.println("输出Jsp:" + variable);
    %>
</body>
</html>
