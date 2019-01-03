<!DOCTYPE html>
<html>

<head>
<#include "/admin/common/constant.ftl" />
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>INSPINIA | 资源列表</title>

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
                    <table id="bootstrap-table" class="footable table table-stripped toggle-arrow-tiny"></table>
                </div>
            </div>
        </div>
    <#include "/admin/common/footer.ftl" />

    </div>

</div>

<script type="text/javascript">
    $(function() {
        var ctx = '${adminDomain}';
        var options = {
            url:ctx + "/sys/menu/list",
            code:"id",
            parentCode:"pid",
            uniqueId:"id",
            createUrl:ctx + "resource/add/{id}",
            updateUrl: ctx + "resource/edit/{id}",
            removeUrl: ctx + "resource/delete/{id}",
            sortName: "id",
            modalName: "资源",
            expandAll:false,
            expandFirst:false,
            columns: [{
                field: 'selectItem',
                radio: true
            },
                {
                    field: 'menuName',
                    title: '菜单名称',
                    width: '20%',
                    formatter: function(value, row, index) {
                        if ($.common.isEmpty(row.icon)) {
                            return row.menuName;
                        } else {
                            return '<i class="' + row.icon + '"></i> <span class="nav-label">' + row.name + '</span>';
                        }
                    }
                },
                {
                    field: 'priority',
                    title: '排序',
                    width: '10%',
                    align: "left"
                },
                {
                    field: 'url',
                    title: '请求地址',
                    width: '15%',
                    align: "left"
                },
                {
                    field: 'type',
                    title: '类型',
                    width: '10%',
                    align: "left",
                    formatter: function(value, item, index) {
                        switch (item.type){
                            case 0 :  return '<span class="label label-success">目录</span>';
                            case 1 :  return '<span class="label label-primary">菜单</span>';
                            case 2 :  return '<span class="label label-warning">按钮</span>';
                        }
                    }
                },
                {
                    field: 'status',
                    title: '状态',
                    width: '10%',
                    align: "left",
                    formatter: function(value, row, index) {
                        switch (row.isDelete){
                            case 0 : return '<span class="badge badge-info">有效</span>';
                            case 1 : return '<span class="badge badge-info"><font style="color: red">无效</font></span>';
                        }
                    }
                },
                {
                    field: 'permission',
                    title: '权限标识',
                    width: '15%',
                    align: "left",
                },
                {
                    title: '操作',
                    width: '20%',
                    align: "left",
                    formatter: function(value, row, index) {
                        var actions = [];
                        actions.push('<a class="btn btn-success btn-xs" href="#" onclick="$.operate.edit('+row.id+')"><i class="fa fa-edit"></i>编辑</a> ');
                        actions.push('<a class="btn btn-info btn-xs"  href="#" onclick="$.operate.add('+row.id+')"><i class="fa fa-plus"></i>新增</a>');
                        actions.push('<a class="btn btn-danger btn-xs" href="#" onclick="deleteBy('+row.id+')"><i class="fa fa-remove"></i>删除</a>');
                        return actions.join('');
                    }
                }]
        };
        $.treeTable.init(options);
    });
</script>
</body>
</html>
