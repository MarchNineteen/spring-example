<!DOCTYPE html>
<html>

<head>
    <#include "/admin/common/constant.ftl" />
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>INSPINIA | FooTable</title>

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
                <h2>FooTable</h2>
                <ol class="breadcrumb">
                    <li>
                        <a href="index">Home</a>
                    </li>
                    <li>
                        <a>Tables</a>
                    </li>
                    <li class="active">
                        <strong>FooTable</strong>
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
                            <h5>FooTable with row toggler, sorting and pagination</h5>

                            <div class="ibox-tools">
                                <a class="collapse-link">
                                    <i class="fa fa-chevron-up"></i>
                                </a>
                                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                                    <i class="fa fa-wrench"></i>
                                </a>
                                <ul class="dropdown-menu dropdown-user">
                                    <li><a href="#">Config option 1</a>
                                    </li>
                                    <li><a href="#">Config option 2</a>
                                    </li>
                                </ul>
                                <a class="close-link">
                                    <i class="fa fa-times"></i>
                                </a>
                            </div>
                        </div>
                        <div class="ibox-content">

                            <table class="footable table table-stripped toggle-arrow-tiny">
                                <thead>
                                <tr>

                                    <th data-toggle="true">Project</th>
                                    <th>Name</th>
                                    <th>Phone</th>
                                    <th data-hide="all">Company</th>
                                    <th data-hide="all">Completed</th>
                                    <th data-hide="all">Task</th>
                                    <th data-hide="all">Date</th>
                                    <th>Action</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>Project - This is example of project</td>
                                    <td>Patrick Smith</td>
                                    <td>0800 051213</td>
                                    <td>Inceptos Hymenaeos Ltd</td>
                                    <td><span class="pie">0.52/1.561</span></td>
                                    <td>20%</td>
                                    <td>Jul 14, 2013</td>
                                    <td><a href="#"><i class="fa fa-check text-navy"></i></a></td>
                                </tr>
                                <tr>
                                    <td>Alpha project</td>
                                    <td>Alice Jackson</td>
                                    <td>0500 780909</td>
                                    <td>Nec Euismod In Company</td>
                                    <td><span class="pie">6,9</span></td>
                                    <td>40%</td>
                                    <td>Jul 16, 2013</td>
                                    <td><a href="#"><i class="fa fa-check text-navy"></i></a></td>
                                </tr>
                                <tr>
                                    <td>Betha project</td>
                                    <td>John Smith</td>
                                    <td>0800 1111</td>
                                    <td>Erat Volutpat</td>
                                    <td><span class="pie">3,1</span></td>
                                    <td>75%</td>
                                    <td>Jul 18, 2013</td>
                                    <td><a href="#"><i class="fa fa-check text-navy"></i></a></td>
                                </tr>
                                <tr>
                                    <td>Gamma project</td>
                                    <td>Anna Jordan</td>
                                    <td>(016977) 0648</td>
                                    <td>Tellus Ltd</td>
                                    <td><span class="pie">4,9</span></td>
                                    <td>18%</td>
                                    <td>Jul 22, 2013</td>
                                    <td><a href="#"><i class="fa fa-check text-navy"></i></a></td>
                                </tr>
                                <tr>
                                    <td>Alpha project</td>
                                    <td>Alice Jackson</td>
                                    <td>0500 780909</td>
                                    <td>Nec Euismod In Company</td>
                                    <td><span class="pie">6,9</span></td>
                                    <td>40%</td>
                                    <td>Jul 16, 2013</td>
                                    <td><a href="#"><i class="fa fa-check text-navy"></i></a></td>
                                </tr>
                                <tr>
                                    <td>Project
                                        <small>This is example of project</small>
                                    </td>
                                    <td>Patrick Smith</td>
                                    <td>0800 051213</td>
                                    <td>Inceptos Hymenaeos Ltd</td>
                                    <td><span class="pie">0.52/1.561</span></td>
                                    <td>20%</td>
                                    <td>Jul 14, 2013</td>
                                    <td><a href="#"><i class="fa fa-check text-navy"></i></a></td>
                                </tr>
                                <tr>
                                    <td>Gamma project</td>
                                    <td>Anna Jordan</td>
                                    <td>(016977) 0648</td>
                                    <td>Tellus Ltd</td>
                                    <td><span class="pie">4,9</span></td>
                                    <td>18%</td>
                                    <td>Jul 22, 2013</td>
                                    <td><a href="#"><i class="fa fa-check text-navy"></i></a></td>
                                </tr>
                                <tr>
                                    <td>Project
                                        <small>This is example of project</small>
                                    </td>
                                    <td>Patrick Smith</td>
                                    <td>0800 051213</td>
                                    <td>Inceptos Hymenaeos Ltd</td>
                                    <td><span class="pie">0.52/1.561</span></td>
                                    <td>20%</td>
                                    <td>Jul 14, 2013</td>
                                    <td><a href="#"><i class="fa fa-check text-navy"></i></a></td>
                                </tr>
                                <tr>
                                    <td>Alpha project</td>
                                    <td>Alice Jackson</td>
                                    <td>0500 780909</td>
                                    <td>Nec Euismod In Company</td>
                                    <td><span class="pie">6,9</span></td>
                                    <td>40%</td>
                                    <td>Jul 16, 2013</td>
                                    <td><a href="#"><i class="fa fa-check text-navy"></i></a></td>
                                </tr>
                                <tr>
                                    <td>Betha project</td>
                                    <td>John Smith</td>
                                    <td>0800 1111</td>
                                    <td>Erat Volutpat</td>
                                    <td><span class="pie">3,1</span></td>
                                    <td>75%</td>
                                    <td>Jul 18, 2013</td>
                                    <td><a href="#"><i class="fa fa-check text-navy"></i></a></td>
                                </tr>
                                <tr>
                                    <td>Gamma project</td>
                                    <td>Anna Jordan</td>
                                    <td>(016977) 0648</td>
                                    <td>Tellus Ltd</td>
                                    <td><span class="pie">4,9</span></td>
                                    <td>18%</td>
                                    <td>Jul 22, 2013</td>
                                    <td><a href="#"><i class="fa fa-check text-navy"></i></a></td>
                                </tr>
                                <tr>
                                    <td>Alpha project</td>
                                    <td>Alice Jackson</td>
                                    <td>0500 780909</td>
                                    <td>Nec Euismod In Company</td>
                                    <td><span class="pie">6,9</span></td>
                                    <td>40%</td>
                                    <td>Jul 16, 2013</td>
                                    <td><a href="#"><i class="fa fa-check text-navy"></i></a></td>
                                </tr>
                                <tr>
                                    <td>Project
                                        <small>This is example of project</small>
                                    </td>
                                    <td>Patrick Smith</td>
                                    <td>0800 051213</td>
                                    <td>Inceptos Hymenaeos Ltd</td>
                                    <td><span class="pie">0.52/1.561</span></td>
                                    <td>20%</td>
                                    <td>Jul 14, 2013</td>
                                    <td><a href="#"><i class="fa fa-check text-navy"></i></a></td>
                                </tr>
                                <tr>
                                    <td>Gamma project</td>
                                    <td>Anna Jordan</td>
                                    <td>(016977) 0648</td>
                                    <td>Tellus Ltd</td>
                                    <td><span class="pie">4,9</span></td>
                                    <td>18%</td>
                                    <td>Jul 22, 2013</td>
                                    <td><a href="#"><i class="fa fa-check text-navy"></i></a></td>
                                </tr>
                                </tbody>
                                <tfoot>
                                <tr>
                                    <td colspan="5">
                                        <ul class="pagination pull-right"></ul>
                                    </td>
                                </tr>
                                </tfoot>
                            </table>

                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>Simple FooTable with pagination, sorting and filter</h5>

                            <div class="ibox-tools">
                                <a class="collapse-link">
                                    <i class="fa fa-chevron-up"></i>
                                </a>
                                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                                    <i class="fa fa-wrench"></i>
                                </a>
                                <ul class="dropdown-menu dropdown-user">
                                    <li><a href="#">Config option 1</a>
                                    </li>
                                    <li><a href="#">Config option 2</a>
                                    </li>
                                </ul>
                                <a class="close-link">
                                    <i class="fa fa-times"></i>
                                </a>
                            </div>
                        </div>
                        <div class="ibox-content">
                            <input type="text" class="form-control input-sm m-b-xs" id="filter"
                                   placeholder="Search in table">

                            <table class="footable table table-stripped" data-page-size="8" data-filter=#filter>
                                <thead>
                                <tr>
                                    <th>Rendering engine</th>
                                    <th>Browser</th>
                                    <th data-hide="phone,tablet">Platform(s)</th>
                                    <th data-hide="phone,tablet">Engine version</th>
                                    <th data-hide="phone,tablet">CSS grade</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr class="gradeX">
                                    <td>Trident</td>
                                    <td>Internet
                                        Explorer 4.0
                                    </td>
                                    <td>Win 95+</td>
                                    <td class="center">4</td>
                                    <td class="center">X</td>
                                </tr>
                                <tr class="gradeC">
                                    <td>Trident</td>
                                    <td>Internet
                                        Explorer 5.0
                                    </td>
                                    <td>Win 95+</td>
                                    <td class="center">5</td>
                                    <td class="center">C</td>
                                </tr>
                                <tr class="gradeA">
                                    <td>Trident</td>
                                    <td>Internet
                                        Explorer 5.5
                                    </td>
                                    <td>Win 95+</td>
                                    <td class="center">5.5</td>
                                    <td class="center">A</td>
                                </tr>
                                <tr class="gradeA">
                                    <td>Gecko</td>
                                    <td>Netscape Navigator 9</td>
                                    <td>Win 98+ / OSX.2+</td>
                                    <td class="center">1.8</td>
                                    <td class="center">A</td>
                                </tr>

                                <tr class="gradeA">
                                    <td>Webkit</td>
                                    <td>Safari 1.3</td>
                                    <td>OSX.3</td>
                                    <td class="center">312.8</td>
                                    <td class="center">A</td>
                                </tr>
                                <tr class="gradeA">
                                    <td>Webkit</td>
                                    <td>Safari 2.0</td>
                                    <td>OSX.4+</td>
                                    <td class="center">419.3</td>
                                    <td class="center">A</td>
                                </tr>
                                <tr class="gradeA">
                                    <td>Webkit</td>
                                    <td>Safari 3.0</td>
                                    <td>OSX.4+</td>
                                    <td class="center">522.1</td>
                                    <td class="center">A</td>
                                </tr>
                                <tr class="gradeA">
                                    <td>Webkit</td>
                                    <td>OmniWeb 5.5</td>
                                    <td>OSX.4+</td>
                                    <td class="center">420</td>
                                    <td class="center">A</td>
                                </tr>
                                <tr class="gradeA">
                                    <td>Webkit</td>
                                    <td>iPod Touch / iPhone</td>
                                    <td>iPod</td>
                                    <td class="center">420.1</td>
                                    <td class="center">A</td>
                                </tr>
                                <tr class="gradeA">
                                    <td>Webkit</td>
                                    <td>S60</td>
                                    <td>S60</td>
                                    <td class="center">413</td>
                                    <td class="center">A</td>
                                </tr>
                                <tr class="gradeA">
                                    <td>Presto</td>
                                    <td>Opera 7.0</td>
                                    <td>Win 95+ / OSX.1+</td>
                                    <td class="center">-</td>
                                    <td class="center">A</td>
                                </tr>
                                <tr class="gradeA">
                                    <td>Presto</td>
                                    <td>Opera 7.5</td>
                                    <td>Win 95+ / OSX.2+</td>
                                    <td class="center">-</td>
                                    <td class="center">A</td>
                                </tr>
                                <tr class="gradeA">
                                    <td>Presto</td>
                                    <td>Opera 8.0</td>
                                    <td>Win 95+ / OSX.2+</td>
                                    <td class="center">-</td>
                                    <td class="center">A</td>
                                </tr>
                                <tr class="gradeA">
                                    <td>Presto</td>
                                    <td>Opera 8.5</td>
                                    <td>Win 95+ / OSX.2+</td>
                                    <td class="center">-</td>
                                    <td class="center">A</td>
                                </tr>
                                <tr class="gradeA">
                                    <td>Presto</td>
                                    <td>Opera 9.0</td>
                                    <td>Win 95+ / OSX.3+</td>
                                    <td class="center">-</td>
                                    <td class="center">A</td>
                                </tr>
                                <tr class="gradeA">
                                    <td>Presto</td>
                                    <td>Opera 9.2</td>
                                    <td>Win 88+ / OSX.3+</td>
                                    <td class="center">-</td>
                                    <td class="center">A</td>
                                </tr>
                                <tr class="gradeA">
                                    <td>Presto</td>
                                    <td>Opera 9.5</td>
                                    <td>Win 88+ / OSX.3+</td>
                                    <td class="center">-</td>
                                    <td class="center">A</td>
                                </tr>
                                <tr class="gradeA">
                                    <td>Presto</td>
                                    <td>Opera for Wii</td>
                                    <td>Wii</td>
                                    <td class="center">-</td>
                                    <td class="center">A</td>
                                </tr>
                                <tr class="gradeA">
                                    <td>Presto</td>
                                    <td>Nokia N800</td>
                                    <td>N800</td>
                                    <td class="center">-</td>
                                    <td class="center">A</td>
                                </tr>
                                <tr class="gradeA">
                                    <td>Presto</td>
                                    <td>Nintendo DS browser</td>
                                    <td>Nintendo DS</td>
                                    <td class="center">8.5</td>
                                    <td class="center">C/A<sup>1</sup></td>
                                </tr>
                                <tr class="gradeC">
                                    <td>KHTML</td>
                                    <td>Konqureror 3.1</td>
                                    <td>KDE 3.1</td>
                                    <td class="center">3.1</td>
                                    <td class="center">C</td>
                                </tr>
                                <tr class="gradeA">
                                    <td>KHTML</td>
                                    <td>Konqureror 3.3</td>
                                    <td>KDE 3.3</td>
                                    <td class="center">3.3</td>
                                    <td class="center">A</td>
                                </tr>
                                <tr class="gradeA">
                                    <td>KHTML</td>
                                    <td>Konqureror 3.5</td>
                                    <td>KDE 3.5</td>
                                    <td class="center">3.5</td>
                                    <td class="center">A</td>
                                </tr>
                                <tr class="gradeX">
                                    <td>Tasman</td>
                                    <td>Internet Explorer 4.5</td>
                                    <td>Mac OS 8-9</td>
                                    <td class="center">-</td>
                                    <td class="center">X</td>
                                </tr>
                                <tr class="gradeC">
                                    <td>Tasman</td>
                                    <td>Internet Explorer 5.1</td>
                                    <td>Mac OS 7.6-9</td>
                                    <td class="center">1</td>
                                    <td class="center">C</td>
                                </tr>
                                <tr class="gradeC">
                                    <td>Tasman</td>
                                    <td>Internet Explorer 5.2</td>
                                    <td>Mac OS 8-X</td>
                                    <td class="center">1</td>
                                    <td class="center">C</td>
                                </tr>
                                <tr class="gradeA">
                                    <td>Misc</td>
                                    <td>NetFront 3.1</td>
                                    <td>Embedded devices</td>
                                    <td class="center">-</td>
                                    <td class="center">C</td>
                                </tr>
                                <tr class="gradeA">
                                    <td>Misc</td>
                                    <td>NetFront 3.4</td>
                                    <td>Embedded devices</td>
                                    <td class="center">-</td>
                                    <td class="center">A</td>
                                </tr>
                                <tr class="gradeX">
                                    <td>Misc</td>
                                    <td>Dillo 0.8</td>
                                    <td>Embedded devices</td>
                                    <td class="center">-</td>
                                    <td class="center">X</td>
                                </tr>
                                <tr class="gradeX">
                                    <td>Misc</td>
                                    <td>Links</td>
                                    <td>Text only</td>
                                    <td class="center">-</td>
                                    <td class="center">X</td>
                                </tr>
                                <tr class="gradeX">
                                    <td>Misc</td>
                                    <td>Lynx</td>
                                    <td>Text only</td>
                                    <td class="center">-</td>
                                    <td class="center">X</td>
                                </tr>
                                <tr class="gradeC">
                                    <td>Misc</td>
                                    <td>IE Mobile</td>
                                    <td>Windows Mobile 6</td>
                                    <td class="center">-</td>
                                    <td class="center">C</td>
                                </tr>
                                <tr class="gradeC">
                                    <td>Misc</td>
                                    <td>PSP browser</td>
                                    <td>PSP</td>
                                    <td class="center">-</td>
                                    <td class="center">C</td>
                                </tr>
                                <tr class="gradeU">
                                    <td>Other browsers</td>
                                    <td>All others</td>
                                    <td>-</td>
                                    <td class="center">-</td>
                                    <td class="center">U</td>
                                </tr>
                                </tbody>
                                <tfoot>
                                <tr>
                                    <td colspan="5">
                                        <ul class="pagination pull-right"></ul>
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
    $(document).ready(function() {

        $('.footable').footable();
        $('.footable2').footable();

    });

</script>

</body>

</html>
