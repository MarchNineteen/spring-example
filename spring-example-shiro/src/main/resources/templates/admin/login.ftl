<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>INSPINIA | Login</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="font-awesome/css/font-awesome.css" rel="stylesheet">

    <link href="css/animate.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
    <!-- Sweet Alert -->
    <link href="css/plugins/sweetalert/sweetalert.css" rel="stylesheet"/>
</head>

<body class="gray-bg">

    <div class="middle-box text-center loginscreen animated fadeInDown">
        <div>
            <div>

                <h1 class="logo-name">IN+</h1>

            </div>
            <h3>欢迎进入后台管理系统</h3>
            <#--<p>Perfectly designed and precisely prepared admin theme with over 50 pages with extra new web app views.-->
                <#--<!--Continually expanded and constantly improved Inspinia Admin Them (IN+)&ndash;&gt;-->
            <#--</p>-->
            <p>Login in. To see it in action.</p>
            <form id="loginForm" class="m-t" role="form" action="login" method="post">
                <div class="form-group">
                    <input type="username" class="form-control" placeholder="Username" required="" name="username">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" placeholder="Password" required="" name="password">
                </div>
                <button id="loginBtn" type="button" class="btn btn-primary block full-width m-b">登  录</button>

                <a href="#"><small>忘记密码?</small></a>
                <p class="text-muted text-center"><small>没有账号？</small></p>
                <a class="btn btn-sm btn-white btn-block" href="register.html">注  册</a>
            </form>
            <#--<p class="m-t"> <small>Inspinia we app framework base on Bootstrap 3 &copy; 2014</small> </p>-->
        </div>
    </div>

    <!-- Mainly scripts -->
    <script src="js/jquery-3.1.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <!-- Sweet alert -->
    <script src="js/plugins/sweetalert/sweetalert.min.js"></script>
    <script type="text/javascript" >

        $(document).ready(function () {

            $('#loginBtn').on('click',function () {
                $.ajax({
                    type: 'post',
                    dataType: 'json',
                    data: $('#loginForm').serialize(),
                    async : true,// 默认为true 异步
                    error:function(){
                        alert('error');
                    },
                    success:function (data) {
                        if (data.success){
                            window.location.href = "index";
                        } else {
                            swal({
                                title : "温馨提示",
                                text : data.message
                            });
                        }
                    }
                });
            });

        });

    </script>
</body>


</html>
