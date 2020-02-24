<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.DateFormat" %>
<%--这是Jsp的注释不会在浏览器显示--%>
<!--该注释在Jsp文件中会在浏览器显示,推荐使用该注释-->
<!--
    Jsp中的隐式对象:
        request             javax.servlet.http.HttpServletRequest
        response            javax.servlet.http.HttpServletResponse
        out                 javax.servlet.jsp.JspWrite
        session             javax.servlet.http.HttpSession
        application         javax.servlet.ServletContext
        config              javax.servlet.ServletConfig
        pageContext         javax.servlet.jsp.PageContext
        page                javax.servlet.jsp.HttpJspPage
        exception           javax.lang.Throwable
    因Jsp实质上就是一个Servlet,那么这些隐式对象可以结合Servlet来记忆,有是从Servlet那边继承过来的,
有些是Jsp自己本身特有重新定义的
PageContext有四个范围域:page、request、session、application,其中page的域范围最窄.
Jsp实际上就是一个Servlet,编译过后通过查看tomcat下的work目录下的文件可以得到一个生成的Servlet文件,
实际上就是继承了org.apache.jasper.runtime.HttpJspBase和实现了org.apache.jasper.runtime.JspSourceDependent、
org.apache.jasper.runtime.JspSourceImports,而HttpJspBase是继承了javax.servlet.http.HttpServlet
并实现了javax.servlet.jsp.HttpJspPage的抽象类
-->
<html>
<head>
    <title>Today`s Date</title>
</head>
<body>
    <%
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.FULL);
        String dateStr = dateFormat.format(new Date());
        out.print("Today is " + dateStr);
    %>
</body>
</html>
