<#include "/admin/common/constant.ftl" />

<nav class="navbar-default navbar-static-side" role="navigation">
    <div class="sidebar-collapse">
        <ul class="nav metismenu" id="side-menu">
            <li class="nav-header">
                <div class="dropdown profile-element"> <span>
                            <img alt="image" class="img-circle" src="${adminDomain}/img/profile_small.jpg"/>
                             </span>
                    <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                            <span class="clear"> <span class="block m-t-xs"> <strong
                                    class="font-bold">${Session.user.username!''}</strong>
                             </span> <span class="text-muted text-xs block">Art Director <b
                                    class="caret"></b></span> </span> </a>
                    <ul class="dropdown-menu animated fadeInRight m-t-xs">
                        <li><a href="profile.html">Profile</a></li>
                        <li><a href="contacts.html">Contacts</a></li>
                        <li><a href="mailbox.html">Mailbox</a></li>
                        <li class="divider"></li>
                        <li><a href="login.html">Logout</a></li>
                    </ul>
                </div>
                <div class="logo-element">
                    IN+
                </div>
            </li>

        <@shiro.hasPermission name = "主页">
            <li>
                <a href="${adminDomain}/index"><i class="fa fa-th-large"></i> <span class="nav-label">主页</span>
                <#--<span class="fa arrow"></span>-->
                </a>
            <#--<ul class="nav nav-second-level">-->
            <#--<li class="active"><a href="index">Dashboard v.1</a></li>-->
            <#--<li><a href="dashboard_2.html">Dashboard v.2</a></li>-->
            <#--<li><a href="dashboard_3.html">Dashboard v.3</a></li>-->
            <#--<li><a href="dashboard_4_1.html">Dashboard v.4</a></li>-->
            <#--<li><a href="dashboard_5.html">Dashboard v.5 </a></li>-->
            </li>
        </@shiro.hasPermission>

        <@shiro.hasPermission name = "用户列表">
            <li>
                <a href="${adminDomain}/user/list"><i class="fa fa-th-large"></i><span class="nav-label">用户列表</span></a>
            </li>
        </@shiro.hasPermission>

        <@shiro.hasPermission name = "系统管理">
            <span class="fa arrow"></span>
            <li>
                <a href="${adminDomain}/user/list"><i class="fa fa-th-large"></i><span class="nav-label">系统管理</span></a>
                <ul class="nav nav-second-level">
                <@shiro.hasPermission name = "角色管理">
                    <li><a href="${adminDomain}/sys/role/list_0_0_0">角色管理</a></li>
                </@shiro.hasPermission>
                <@shiro.hasPermission name = "权限管理">
                    <li><a href="dashboard_3.html">权限管理</a></li>
                </@shiro.hasPermission>
                </ul>
            </li>
        </@shiro.hasPermission>

        </ul>

    </div>
</nav>

<script src="${adminDomain}/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
    $(function () {
//       $('.sidebar-collapse li').on('click',function () {
//           $(this).addClass('active').siblings().removeClass('active');
//           var href = $(this).find('a').attr['href'];
//           window.reload(href)
//       });
        $('.sidebar-collapse li').each(function (val, index) {
            var path = window.location.pathname;//当前URL的路径部分
            if ($(this).find('a').attr("href") == path) {
                $(this).addClass('active').siblings().removeClass('active');
                return;
            }
        });
    });
</script>