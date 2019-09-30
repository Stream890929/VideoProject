<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>添加或修改视频</title>

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
        function showName(obj, id, type) {
            if (type == 1) {
                //想获取下拉列表选中的值
                var chooseName = $(obj).text();
                // 将获取的值显示在输入框内
                $("#speakerName").val(chooseName);
                //想给隐藏的文本赋值
                $("#speakerId").val(id);
            } else {
                var chooseName = $(obj).text();
                // 将获取的值显示在输入框内
                $("#courseTitle").val(chooseName);
                //想给隐藏的文本赋值
                $("#courseId").val(id);
            }
        }

        //页面加载完毕之后就执行以下代码片段
        $(function () {
            var speakerId = '${video.speakerId}';
            $("#selectSpeaker li").each(function () {
                if ($(this).val() == speakerId) {
                    $("#speakerName").val($(this).text());
                }
            });

            var courseId = '${video.courseId}';
            $("#selectCourse li").each(function () {

                if ($(this).val() == courseId) {
                    $("#courseTitle").val($(this).text());
                }
            });
        });

        function validateInput2(obj) {
            var value = $(obj).val();
            if (!/^http:\/\/[a-z]+\.[a-z0-9]+\.[a-z]{2,}$/.test(value)) {
                // alert("该输入不是一个网址，例如：http://www.alibaba.com");
                $("#msg").text("网址格式错误，例如：http://www.alibaba.com")
            }
        }

        function fileUpload() {
            $.ajaxFileUpload({
                url: "${pageContext.request.contextPath}/video/upload", //需要链接到服务器地址
                secureuri: false, //是否启用安全提交，默认为false。
                fileElementId: "uploadImgInput", //上传文件选择框的id属性
                dataType: 'text', //json，与后台对应，text和json
                success: function (data) { //后台ajax返回的数据 此处Imgurl
                    $("#photo").html("<img  width='100px' height='100px' src='" + data + "'/>");
                    $("#videoImageUrl").val(data);
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
                <li class="active"><a href="${pageContext.request.contextPath}/video/list">视频管理</a></li>
                <li><a href="${pageContext.request.contextPath}/speaker/list">主讲人管理</a></li>
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
        <c:if test="${empty video.id}"><h2>添加视频信息</h2></c:if>
        <c:if test="${not empty video.id}"><h2>修改视频信息</h2></c:if>
    </div>
</div>

<div class="container" style="margin-top: 20px;">
    <form id="formSimple" class="form-horizontal">
        <c:if test="${not empty video.id}"><input type="hidden" name="id" value="${video.id}"/></c:if>

        <div class="form-group">
            <label for="title" class="col-sm-2 control-label">名称</label>
            <div class="col-sm-10">
                <input type="text" id="title" name="title" class="form-control" placeholder="视频名称" value="${video.title}"/>
            </div>
        </div>

        <div class="form-group">
            <label for="speakerName" class="col-sm-2 control-label">教师名字</label>
            <div class="col-sm-10">
                <div class="input-group">
                    <div class="input-group-btn">
                        <button type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"
                                class="btn btn-default dropdown-toggle">下拉菜单<span class="caret"></span></button>
                        <ul id="selectSpeaker" class="dropdown-menu">
                            <c:forEach items="${speakerList}" var="speaker">
                                <li value='${speaker.id}'>
                                    <a href="#" onclick="showName(this,'${speaker.id}',1)">${speaker.speakerName}</a>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>

                    <c:if test="${empty video.speakerId}">
                        <input type="hidden" id="speakerId" name="speakerId" class="form-control" value="0"/>
                    </c:if>
                    <c:if test="${not empty video.speakerId}">
                        <input type="hidden" id="speakerId" name="speakerId" class="form-control"
                               value="${video.speakerId}"/>
                    </c:if>
                    <input type="text" id="speakerName" disabled aria-label="..." class="form-control"/>
                </div><!-- /input-group -->
            </div>
        </div>

        <div class="form-group">
            <label for="courseTitle" class="col-sm-2 control-label">所属课程</label>
            <div class="col-sm-10">
                <div class="input-group">
                    <div class="input-group-btn">
                        <button type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"
                                class="btn btn-default dropdown-toggle">下拉菜单<span class="caret"></span></button>
                        <ul id="selectCourse" class="dropdown-menu">
                            <c:forEach items="${courseList}" var="course">
                                <li value="${course.id}">
                                    <a href="#" onclick="showName(this,${course.id},2)">${course.courseTitle}</a>
                                </li>
                            </c:forEach>
                        </ul>
                    </div><!-- /btn-group -->
                    <c:if test="${empty video.courseId}">
                        <input type="hidden" id="courseId" name="courseId" class="form-control" value="0"/>
                    </c:if>
                    <c:if test="${not empty video.courseId}">
                        <input type="hidden" id="courseId" name="courseId" class="form-control"
                               value="${video.courseId}"/>
                    </c:if>
                    <input type="text" id="courseTitle" aria-label="..." disabled class="form-control"/>
                </div><!-- /input-group -->
            </div>
        </div>

        <div class="form-group">
            <label for="time" class="col-sm-2 control-label">时长</label>
            <div class="col-sm-10">
                <c:if test="${empty video.time}">
                    <input type="number" id="time" name="time" class="form-control" placeholder="精确到毫秒（正整数）" value="0"/>
                </c:if>
                <c:if test="${not empty video.time}">
                    <input type="number" name="time" class="form-control" placeholder="精确到毫秒（正整数）"
                           value="${video.time}"/>
                </c:if>
            </div>
        </div>

        <div class="form-group">
            <label for="videoUrl" class="col-sm-2 control-label">视频播放地址</label>
            <div class="col-sm-10">
                <input type="url" id="videoUrl" name="videoUrl" onblur="validateInput2(this)" class="form-control"
                       placeholder="具体的url" value="${video.videoUrl}"/>
                <span id="msg" style="color: red;"></span>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-2 control-label">备注</label>
            <div class="col-sm-10">
                <textarea class="form-control" name="detail" rows="3">${video.detail}</textarea>
            </div>
        </div>

        <div class="form-group">
            <label for="videoImageUrl" class="col-sm-2 control-label">封面图片地址</label>
            <div class="col-sm-10">
                <form action="" id="uploadForm" enctype="multipart/form-data" method="post">
                    <input type="file" id="uploadImgInput" name="headImg" onchange="fileUpload()"/>
                </form>
                <div id="photo">
                    <c:if test="${not empty video.videoImageUrl}">
                        <img src="${video.videoImageUrl}" style="width: 100px;height: 100px;" >
                    </c:if>
                </div>
                <input type="hidden" id="videoImageUrl" name="videoImageUrl" class="form-control"
                       value="${video.videoImageUrl}"/>
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
        $.post("${pageContext.request.contextPath}/video/saveOrUpdate", $("#formSimple").serialize(), function (data) {
            if (data == 'success') {
                location.href = "${pageContext.request.contextPath}/video/list";
            }
        });
    }
</script>

</body>
</html>