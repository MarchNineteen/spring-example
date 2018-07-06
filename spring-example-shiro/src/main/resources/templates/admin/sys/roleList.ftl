<!DOCTYPE html>
<html>

<head>
<#include "/admin/common/constant.ftl" />
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>INSPINIA | 角色列表</title>

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
            <div class="row">
                <div class="col-lg-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <div class="ibox-title">
                                <h5>搜索&amp;操作</h5>
                                <div class="ibox-tools">
                                    <a class="collapse-link">
                                        <i class="fa fa-chevron-up"></i>
                                    </a>
                                </div>
                            </div>
                            <div class="ibox-content" style="display: block;">
                                <form action="itemCategoryManage_0_0_0">
                                    <div class="row">
                                        <div class="col-sm-3 m-b-xs">
                                            <input name="role.name" value="${role.name}" placeholder="角色名称"
                                                   class="form-control" type="text"/>
                                        </div>
                                        <div class="col-sm-4">
                                            <input type="text" id="product_name" name="product_name" value=""
                                                   placeholder="Product Name" class="form-control">
                                        </div>
                                        <div class="col-sm-1 m-b-xs">
                                            <button id="submitButton" class="btn btn-primary btn-block" type="submit"><i
                                                    class="fa fa-search"></i>&nbsp;&nbsp;&nbsp;&nbsp;<strong>搜索</strong>
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

                                    <th data-toggle="true">Product Name</th>
                                    <th data-hide="phone">Model</th>
                                    <th data-hide="all">Description</th>
                                    <th data-hide="phone">Price</th>
                                    <th data-hide="phone,tablet">Quantity</th>
                                    <th data-hide="phone">Status</th>
                                    <th class="text-right" data-sort-ignore="true">Action</th>

                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>
                                        Example product 1
                                    </td>
                                    <td>
                                        Model 1
                                    </td>
                                    <td>
                                        It is a long established fact that a reader will be distracted by the readable
                                        content of a page when looking at its layout. The point of using Lorem Ipsum is
                                        that it has a more-or-less normal distribution of letters, as opposed to using
                                        'Content here, content here', making it look like readable English.
                                    </td>
                                    <td>
                                        $50.00
                                    </td>
                                    <td>
                                        1000
                                    </td>
                                    <td>
                                        <span class="label label-primary">Enable</span>
                                    </td>
                                    <td class="text-right">
                                        <div class="btn-group">
                                            <button class="btn-white btn btn-xs">View</button>
                                            <button class="btn-white btn btn-xs">Edit</button>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        Example product 2
                                    </td>
                                    <td>
                                        Model 2
                                    </td>
                                    <td>
                                        It is a long established fact that a reader will be distracted by the readable
                                        content of a page when looking at its layout. The point of using Lorem Ipsum is
                                        that it has a more-or-less normal distribution of letters, as opposed to using
                                        'Content here, content here', making it look like readable English.
                                    </td>
                                    <td>
                                        $40.00
                                    </td>
                                    <td>
                                        4300
                                    </td>
                                    <td>
                                        <span class="label label-primary">Enable</span>
                                    </td>
                                    <td class="text-right">
                                        <div class="btn-group">
                                            <button class="btn-white btn btn-xs">View</button>
                                            <button class="btn-white btn btn-xs">Edit</button>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
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
            </div>
        </div>
    <#include "/admin/common/footer.ftl" />

    </div>
</div>


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

<!-- Page-Level Scripts -->
<script>
    $(document).ready(function () {
        var pageHTML = ${pageHTML};
        $("#pageHTML").html(pageHTML);
        $('.footable').footable();
        $('.footable2').footable();

    });

</script>

</body>

</html>
