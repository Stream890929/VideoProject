<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>添加或修改讲师</title>

    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/ajaxfileupload.js"></script>

    <style type="text/css">
        th {
            text-align: center;
        }
    </style>
    <script type="text/javascript">
        function fileUpload() {
            $.ajaxFileUpload({
                url: "${pageContext.request.contextPath}/video/upload", //需要链接到服务器地址
                secureuri: false, //是否启用安全提交，默认为false。
                fileElementId: "uploadImgInput", //上传文件选择框的id属性
                dataType: 'text', //json，与后台对应，text和json
                success: function (data) { //后台ajax返回的数据 此处Imgurl
                    $("#photo").html("<img  width='100px' height='100px' src='" + data + "'/>");
                    $("#headImgUrl").val(data);
                }
            });
        }
    </script>
</head>

<body>
<nav class="navbar-inverse">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/video/list">视频管理系统</a>
        </div>

        <div class="collapse navbar-collapse"
             id="bs-example-navbar-collapse-9">
            <ul class="nav navbar-nav">
                <li><a href="${pageContext.request.contextPath}/video/list">视频管理</a></li>
                <li class="active"><a href="${pageContext.request.contextPath}/speaker/list">主讲人管理</a></li>
                <li><a href="${pageContext.request.contextPath}/course/list">课程管理</a></li>
            </ul>
            <p class="navbar-text navbar-right">
                <span>${sessionScope.userName}</span>
                <i class="glyphicon glyphicon-log-in" aria-hidden="true"></i>&nbsp;&nbsp;
                <a href="${pageContext.request.contextPath}/admin/logout" class="navbar-link">退出</a>
            </p>
        </div>
    </div>
</nav>

<div class="jumbotron" style="padding-top: 15px;padding-bottom: 15px;">
    <div class="container">
        <c:if test="${empty speaker.id}"><h2>添加讲师信息</h2></c:if>
        <c:if test="${not empty speaker.id}"><h2>修改讲师信息</h2></c:if>
    </div>
</div>

<div class="container" style="margin-top: 20px;">
    <form id="formSimple" class="form-horizontal">
        <c:if test="${not empty speaker.id}"><input type="hidden" name="id" value="${speaker.id}"/></c:if>

        <div class="form-group">
            <label for="speakerName" class="col-sm-2 control-label">姓名</label>
            <div class="col-sm-10">
                <input type="text" id="speakerName" name="speakerName" class="form-control" placeholder="讲师姓名"
                       value="${speaker.speakerName}"/>
            </div>
        </div>

        <div class="form-group">
            <label for="speakerJob" class="col-sm-2 control-label">职称</label>
            <div class="col-sm-10">
                <input type="text" id="speakerJob" name="speakerJob" class="form-control" placeholder="讲师职称"
                       value="${speaker.speakerJob}"/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-2 control-label">简介</label>
            <div class="col-sm-10">
                <textarea class="form-control" name="speakerDesc" rows="3"
                          placeholder="请输入关于讲师的介绍">${speaker.speakerDesc}</textarea>
            </div>
        </div>

        <div class="form-group">
            <label for="headImgUrl" class="col-sm-2 control-label">照片</label>
            <div class="col-sm-10">
                <form action="" id="uploadForm" enctype="multipart/form-data" method="post">
                    <input type="file" id="uploadImgInput" name="headImg" onchange="fileUpload()"/>
                </form>
                <div id="photo">
                    <c:if test="${not empty speaker.headImgUrl}">
                        <img src="${speaker.headImgUrl}" style="width: 100px;height: 100px;"></c:if>
                </div>
                <input type="hidden" id="headImgUrl" name="headImgUrl" class="form-control"
                       value="${speaker.headImgUrl}"/>
            </div>
        </div>
    </form>

    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="button" onclick="commitForm()" class="btn btn-default">保存</button>
        </div>
    </div>
</div>

<script>
    function commitForm() {
        $.post("${pageContext.request.contextPath}/speaker/saveOrUpdate", $("#formSimple").serialize(), function (data) {
            if (data == 'success') {
                location.href = "${pageContext.request.contextPath}/speaker/list";
            } else {
                alert("添加失败");
            }
        });
    }
</script>

</body>
</html>