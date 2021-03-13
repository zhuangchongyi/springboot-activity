<!DOCTYPE html>
<html lang="zxx">
<head>
    <#assign basePath=request.contextPath />
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>登录</title>
</head>
<body>
简易登录，只需输入用户名称即可。</br>
无密码验证，登录只为了获取用户信息。</br>
现有用户：</br>
    员工用户：user</br>
    经理用户：manager</br>
    boss用户：boss</br>

<form name="loginForm" action="/login" method="post">
    <input name="loginName" value="user">
    <input name="loginPwd" value="123456" type="password">
    <button type="submit">登录</button>
</form>
<script>
</script>
</body>
</html>