<%@ page  contentType="text/html; UTF-8" pageEncoding="utf-8" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
   <h1>登录界面</h1>

   <form action="${pageContext.request.contextPath}/user/login" method="post" >
       用户名：
       <input type="text" name="username"><br>
       密码：
       <input type="text" name="password"><br>
       <input type="submit" value="登录">
   </form>


</body>
</html>