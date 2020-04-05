<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>单个文件上传</title>
</head>
<body>
    <h1>请选择一个文件上传</h1>
    <form action="singleUpload" enctype="multipart/form-data" method="post">
        上传者：<input type="text" name="uploader"/><br/>
        请选择一个要上传的文件：<input type="file" name="fileName"/><br/>
        <input type="submit" value="上传"/>
    </form>
</body>
</html>
