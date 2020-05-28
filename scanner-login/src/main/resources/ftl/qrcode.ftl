<html>

<head>
    <script src="http://code.jquery.com/jquery-3.4.1.min.js"
            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
            crossorigin="anonymous"></script>
</head>

<body>

<#--<button id="btn">点击生成二维码二维码</button>-->

<img id="qrCode" src="/getQrCode?uuid=${uuid}"/>
<button id="btn"></button>
</body>

<script>
    var uuid = '${uuid}';

    $(function () {
        setInterval("check()", 3000);
    });

    function getQrCode() {

    }

    function check() {
        var url = "/checkQrCode?uuid=" + uuid;
        // var param = {'out_trade_no': out_trade_no};
        $.get(url, function (data) {
            var arr = data;

            if (arr == 'NO_VALID') {
                alert("二维码已失效");
                window.location.reload();
            }
            else if (arr == 'USE') {
                alert("登录成功 跳转")
            }
            else if (arr == 'PRE_USE') {
                alert("扫描成功待手机确认")
            }
        });
    }

</script>

<style>
    #qrCode {
        display: block;
        margin: auto;
    }
</style>
</html>
