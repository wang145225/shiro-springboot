<%@ page  contentType="text/html; UTF-8" pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <h1>用户界面</h1>
<a href="${pageContext.request.contextPath}/user/logout">退出登录</a>

<br>

    <ul>


        <%--设置访问用户的权限--%>
    <shiro:hasAnyRoles name="admin,user">
        <li> <a href="#">用户管理</a> </li>
        <ul>
            <shiro:hasPermission name="user:add:*">
                <li> <a href="#">添加</a> </li>
            </shiro:hasPermission>
            <shiro:hasPermission name="user:delete:*">
                <li> <a href="#">删除</a> </li>
            </shiro:hasPermission>
            <shiro:hasPermission name="user:update:*">
                <li> <a href="#">修改</a> </li>
            </shiro:hasPermission>
        </ul>
    </shiro:hasAnyRoles>
    <shiro:hasRole name="admin">
        <li> <a href="#">订单管理</a> </li>
        <li> <a href="#">商品管理</a> </li>
        <li> <a href="#">物流管理</a> </li>
    </shiro:hasRole>
</ul>
</body>
</html>