<%--
  Created by IntelliJ IDEA.
  User: 联想辉哥
  Date: 2019/4/11
  Time: 18:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登陆页面</title>
    <%@include file="/static/join.jsp" %>
    <STYLE>
        .note {
            color: #EF5B00;
        }

        .login {
            color: gray;
        }
    </STYLE>
</head>
<body>
<div style="height: 50px;"></div>
<div align="center">
    <img src="/imge/xiaomi.png">
    <form id="form" class="form-horizontal" role="form" method="post">
        <div class="form-group">

            <div class="col-sm-10">
                <input type="text" style="width: 350px" class="form-control" name="tel" placeholder="邮箱/手机号/小米ID">
            </div>
        </div>
        <div class="form-group">

            <div class="col-sm-10">
                <input type="text" style="width: 350px" class="form-control" id="tel" name="password" placeholder="密码">
            </div>
        </div>


    </form>
    <button id="login" style="width: 350px;" class="btn btn-danger">登陆</button>
    <BR>
    <div align="center">
        <font style="color: red"><a href="" class="note">手机短信登陆</a></font>
        <font style="color: red">/<a href="" class="note">注册</a></font>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <font style="color: grey"><a href="" class="login">立即注册</a></font>|
        <font style="color: grey"><a href="" class="login">忘记密码?</a></font>
        <Br><br>
        <font style="color: gray"> 其他方式登录</font><BR><br>
        <a href=""> <img src="${pageContext.request.contextPath}/imge/qq.png" class="rounded-circle"
                         style="width: 40px;height: 35px;"></a> &nbsp;&nbsp;&nbsp;&nbsp;
        <a href="https://api.weibo.com/oauth2/authorize?client_id=258427282&response_type=code&redirect_uri=http://127.0.0.1:8080/login/weiboLogin"> <img src="${pageContext.request.contextPath}/imge/weibo.png" class="rounded-circle"
                         style="width: 40px;height: 35px;"></a> &nbsp;&nbsp;&nbsp;&nbsp;

        <a href=""> <img src="${pageContext.request.contextPath}/imge/zhifubao.png" class="rounded-circle"
                         style="width: 40px;height: 35px;"> </a> &nbsp;&nbsp;&nbsp;&nbsp;
        <a href=""> <img src="${pageContext.request.contextPath}/imge/wenxi.png" class="rounded-circle"
                         style="width: 40px;height: 35px;"> </a> &nbsp;&nbsp;&nbsp;&nbsp;


    </div>


</div>

<script>
    $("#login").click(function () {
        var login = $("form").serialize()
        $.ajax({
            url: "${pageContext.request.contextPath}/user/login",
            data: login,
            type: "post",
            success: function (data) {
                if (data == 1) {
                    alert("登陆成功")
                } else if (data == 2) {
                    alert("用户名或密码有误")
                } else {
                    alert("信息不能为空")
                }
            }
        })
    })

</script>

</body>
</html>
