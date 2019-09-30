<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>登录</title>

    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>

    <script src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script>
        function validateLogin() {
            var data = $("form").serialize();
            $.post(
                "${pageContext.request.contextPath}/admin/login", data, function (data) {
                    if (data == 'success') {
                        //alert("登录成功");
                        location.href = "${pageContext.request.contextPath}/video/list";
                    } else {
                        //alert("用户民密码错误");
                        $("#msg").text("用户名密码错误，请检查后输入");
                    }
                }
            );
            return false;
        }
    </script>
</head>
<body>
<form>
    <div style="margin-top: 10%;">
        <img src="${pageContext.request.contextPath}/images/logo.png" alt="Responsive image"
             class="img-responsive center-block" style="width: 20%;"/>

        <div class="container" style="margin-top: 10px;">
            <div class="row">
                <div class="col-md-4"></div>
                <div class="form-group col-md-4">
                    <input type="text" id="username" name="username" class="form-control" placeholder="用户名"
                           style="border:1px solid #5CB85C"/>
                </div>
                <div class="col-md-4"></div>
            </div>

            <div class="row">
                <div class="col-md-4"></div>
                <div class="form-group col-md-4">
                    <input type="password" id="password" name="password" class="form-control" placeholder="密码"
                           style="border:1px solid #5CB85C"/>
                </div>
                <div class="col-md-4"></div>
            </div>

            <div class="row">
                <div class="col-md-4"></div>
                <div class="form-group col-md-4"><span id="msg" style="color:darkred;"></span></div>
                <div class="col-md-4"></div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-4"></div>
            <div class="col-md-4">&nbsp;&nbsp;&nbsp;&nbsp;
                <button type="submit" id="loginBtn" onclick="return validateLogin()"
                        class="btn btn-success center-block" style="width:80%;"> 登 录
                </button>
            </div>
            <div class="col-md-4"></div>
        </div>
    </div>
</form>

</body>
</html>