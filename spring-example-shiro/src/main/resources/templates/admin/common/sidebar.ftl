<#include "/admin/common/constant.ftl" />

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link href="${adminDomain}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${adminDomain}/font-awesome/css/font-awesome.css" rel="stylesheet">

    <link href="${adminDomain}/plugins/bootstrap-treetable/bootstrap-treetable.css" rel="stylesheet"/>

    <!-- FooTable -->
    <link href="${adminDomain}/css/plugins/footable/footable.core.css" rel="stylesheet">
    <link href="${adminDomain}/css/animate.css" rel="stylesheet">
    <link href="${adminDomain}/css/style.css" rel="stylesheet">

    <!-- Mainly scripts -->
    <script src="${adminDomain}/js/jquery-3.1.1.min.js"></script>
    <script src="${adminDomain}/js/bootstrap.min.js"></script>
    <script src="${adminDomain}/js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="${adminDomain}/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
    <!-- FooTable -->
    <script src="${adminDomain}/js/plugins/footable/footable.all.min.js"></script>
    <!-- Custom and plugin javascript -->
    <script src="${adminDomain}/js/inspinia.js"></script>
    <script src="${adminDomain}/js/plugins/pace/pace.min.js"></script>
    <script src="${adminDomain}/js/web/common.js"></script>

    <script src="${adminDomain}/plugins/jquery-ztree/3.5/js/jquery.ztree.all-3.5.js"></script>
    <script src="${adminDomain}/plugins/bootstrap-treetable/bootstrap-treetable.js"></script>
    <script src="${adminDomain}/plugins/fullscreen/jquery.fullscreen.js"></script>

    <!-- Bootstrap table -->
    <script src="${adminDomain}/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
    <script src="${adminDomain}/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
    <script src="${adminDomain}/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
    <script src="${adminDomain}/js/plugins/bootstrap-table/extensions/toolbar/bootstrap-table-toolbar.min.js"></script>

</head>

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
                <a href="javascript:;"><i class="fa fa-th-large"></i><span class="nav-label">系统管理</span></a>
                <ul class="nav nav-second-level">
                <@shiro.hasPermission name = "角色管理">
                    <li><a href="${adminDomain}/sys/role/list_0_0_0">角色管理</a></li>
                </@shiro.hasPermission>
                <@shiro.hasPermission name = "权限管理">
                    <li><a href="${adminDomain}/sys/menu/list">权限管理</a></li>
                </@shiro.hasPermission>
                </ul>
            </li>
        </@shiro.hasPermission>

        </ul>

    </div>
</nav>

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
                if ($(this).parent('ul')) {
                    $(this).parent('ul').addClass('in');
                }
                return;
            }
        });
    });
</script>