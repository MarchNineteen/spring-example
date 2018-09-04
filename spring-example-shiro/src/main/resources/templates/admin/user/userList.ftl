<!DOCTYPE html>
<html>

<head>
<#include "/admin/common/constant.ftl" />
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>INSPINIA | 用户管理</title>

    <link href="${adminDomain}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${adminDomain}/font-awesome/css/font-awesome.css" rel="stylesheet">

    <!-- FooTable -->
    <link href="${adminDomain}/css/plugins/footable/footable.core.css" rel="stylesheet">

    <link href="${adminDomain}/css/animate.css" rel="stylesheet">
    <link href="${adminDomain}/css/style.css" rel="stylesheet">

</head>

<body>

<div id="wrapper">

<#include "/admin/common/sidebar.ftl" />

    <div id="page-wrapper" class="gray-bg">
    <#include "/admin/common/header.ftl" />

        <div class="row wrapper border-bottom white-bg page-heading">
            <div class="col-lg-10">
                <h2>用户管理列表</h2>
                <ol class="breadcrumb">
                    <li>
                        <a href="${adminDomain}/index">主页</a>
                    </li>
                    <li class="active">
                        <strong>用户管理</strong>
                    </li>
                </ol>
            </div>
            <div class="col-lg-2">

            </div>
        </div>
        <div class="wrapper wrapper-content animated fadeInRight ecommerce">
            <div class="ibox-content m-b-sm border-bottom">
                <div class="row">
                    <div class="col-sm-2">
                        <div class="form-group">
                            <label class="control-label" for="username">用户名</label>
                            <input type="text" id="username" name="username" value="" placeholder="用户名"
                                   class="form-control">
                        </div>
                    </div>
                    <div class="col-sm-2">
                        <div class="form-group">
                            <label class="control-label" for="phone">手机号</label>
                            <input type="text" id="phone" name="phone" value="" placeholder="+86"
                                   class="form-control">
                        </div>
                    </div>
                    <div class="col-sm-2">
                        <div class="form-group">
                            <label class="control-label" for="status">状态</label>
                            <select name="isDelete" id="isDelete" class="form-control">
                                <option value="0" selected>启用</option>
                                <option value="1">关闭</option>
                            </select>
                        </div>
                    </div>
                </div>

            </div>

            <div class="row">
                <div class="col-lg-12">
                    <div class="ibox">
                        <div class="ibox-content">

                            <table class="footable table table-stripped toggle-arrow-tiny">
                                <thead>
                                <tr>
                                    <th data-toggle="true">用户名</th>
                                    <th data-hide="phone">真实姓名</th>
                                    <th data-hide="all">密码</th>
                                    <th data-hide="phone">地址</th>
                                    <th data-hide="phone,tablet">手机号</th>
                                    <th data-hide="phone">用户状态</th>
                                    <th data-hide="phone">创建时间</th>
                                    <th data-hide="phone">修改时间</th>
                                    <th class="text-center" data-sort-ignore="true">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#list list! as ls>
                                <tr>
                                    <td>${ls.username!}</td>
                                    <td>${ls.realName!}</td>
                                    <td>****</td>
                                    <td>${ls.address!}</td>
                                    <td>${ls.phone!}</td>
                                    <td><#if ls.isDelete ==0><span class="label label-primary">Enable</span><#else><span
                                            class="label label-danger">禁用</span></#if>
                                    <#--<span class="label label-danger">关闭</span>-->
                                    <#--<span class="label label-warning">低库存</span>-->
                                    </td>
                                    <td>${ls.createTime!?string("yyyy-MM-dd HH:mm:ss")}</td>
                                    <td>${ls.updateTime!?string("yyyy-MM-dd HH:mm:ss")}</td>
                                    <td class="text-center">
                                        <div class="btn-group">
                                            <button class="btn-white btn btn-xs">查看</button>
                                            <button class="btn-white btn btn-xs">编辑</button>
                                        </div>
                                    </td>
                                </tr>
                                </#list>
                                </tbody>
                                <tfoot>
                                <tr>
                                    <td colspan="9">
                                        <ul id="pageHTML" class="pagination pull-right"></ul>
                                    </td>
                                </tr>
                                </tfoot>
                            </table>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    <#include "/admin/common/footer.ftl" />

    </div>
</div>


<!-- Mainly scripts -->
<script src="${adminDomain}/js/bootstrap.min.js"></script>
<script src="${adminDomain}/js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="${adminDomain}/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

<!-- FooTable -->
<script src="${adminDomain}/js/plugins/footable/footable.all.min.js"></script>

<!-- Custom and plugin javascript -->
<script src="${adminDomain}/js/inspinia.js"></script>
<script src="${adminDomain}/js/plugins/pace/pace.min.js"></script>

<script>
    var pageHTML = '${pageHTML}';
    $(document).ready(function() {
        $('#pageHTML').html(pageHTML);
        $('.footable').footable();
    });

</script>

</body>

</html>
