<!DOCTYPE html>
<html>

<head>
<#include "/admin/common/constant.ftl" />
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>INSPINIA | 角色列表</title>
</head>

<body>

<div id="wrapper">

<#include "/admin/common/sidebar.ftl" />

    <div id="page-wrapper" class="gray-bg">
    <#include "/admin/common/header.ftl" />

        <div class="row wrapper border-bottom white-bg page-heading">
            <div class="col-lg-10">
                <h2>系统管理</h2>
                <ol class="breadcrumb">
                    <li>
                        <a href="index">Home</a>
                    </li>
                    <li>
                        <a>角色管理</a>
                    </li>
                    <li class="active">
                        <strong>角色列表</strong>
                    </li>
                </ol>
            </div>
            <div class="col-lg-2">

            </div>
        </div>

        <div class="wrapper wrapper-content animated fadeInRight">
            <div class="col-lg-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>搜索&amp;操作</h5>
                        <div class="ibox-tools">
                            <a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content" style="display: block;">
                        <form action="${adminDomain}/sys/role/list_${pageCurrent}_${pageSize}_${pageCount}" method="get">
                            <div class="row">
                                <div class="col-sm-3 m-b-xs">
                                    <input name="roleName" value="${role.roleName!}" placeholder="角色名称"
                                           class="form-control" type="text"/>
                                </div>
                            <#--<div class="col-sm-4">-->
                            <#--<input type="text" id="product_name" name="product_name" value=""-->
                            <#--placeholder="Product Name" class="form-control"/>-->
                            <#--</div>-->
                                <div class="col-sm-1 m-b-xs">
                                    <button id="submitButton" class="btn btn-primary btn-block" type="submit">
                                        <i class="fa fa-search"></i>&nbsp;&nbsp;&nbsp;&nbsp;<strong>搜索</strong>
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="ibox-content">
                    <table class="footable table table-stripped toggle-arrow-tiny">
                        <thead>
                        <tr>
                            <th width="10%" data-toggle="true">角色名称</th>
                            <th width="20%" data-hide="phone">角色描述</th>
                            <th width="20%" data-hide="phone">添加时间</th>
                            <th width="20%" data-hide="phone,tablet">修改时间</th>
                            <th width="10%" data-hide="phone">状态</th>
                            <th width="10%" class="text-center" data-sort-ignore="true">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list list! as ls>
                        <tr>
                            <td>${ls.roleName!}</td>
                            <td>${ls.description!}</td>
                            <td>${ls.createTime!?string("yyyy-MM-dd HH:mm:ss")}</td>
                            <td>${ls.updateTime!?string("yyyy-MM-dd HH:mm:ss")}</td>
                            <td><#if ls.isDelete ==0><span class="label label-primary">Enable</span><#else><span
                                    class="label label-danger">禁用</span></#if>
                            </td>
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
                            <td colspan="6">
                                <ul id="pageHTML" class="pagination pull-right"></ul>
                            </td>
                        </tr>
                        </tfoot>
                    </table>
                </div>
            </div>
        </div>
    <#include "/admin/common/footer.ftl" />

    </div>

</div>

<!-- Page-Level Scripts -->
<script type="text/javascript">
    var pageHTML = '${pageHTML}';
    $(document).ready(function () {
        $(document).ready(function() {
            $('#pageHTML').html(pageHTML);
            $('.footable').footable();
        });
    });

</script>
</body>
</html>
