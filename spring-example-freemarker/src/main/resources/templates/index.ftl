<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">
<head>
    <title>Hello World!</title>
</head>
<body>
    <p>
        welcome ${name} to freemarker!
    </p>

    <p>性别：
    <#if sex==0>
        女
    <#elseif sex==1>
        男
    <#else>
        保密
    </#if>
    </p>

    <h4>我的好友：</h4>
<#list users as item>
    姓名：${item.username} , 年龄${item.age} ，创建时间 ${item.createTime?string('yyyy-MM-dd hh:mm:ss')}
    <br>
</#list>
</body>
</html>