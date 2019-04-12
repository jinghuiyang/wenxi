<%--
  Created by IntelliJ IDEA.
  User: 联想辉哥
  Date: 2019/4/11
  Time: 15:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册信息</title>
    <%@include file="/static/join.jsp" %>
</head>
<body>
<div style="height: 200px;"></div>

<div align="center">
    <h2>注册页面</h2>

    <form id="form" class="form-horizontal" role="form" method="post">
        <div class="form-group">

            <div class="col-sm-10">

                <input type="text" id="mail" style="width: auto" class="form-control" name="mail" placeholder="邮箱"><span id="mailsp"></span>
            </div>
        </div>
        <div class="form-group">

            <div class="col-sm-10">

                <input type="text" id="tel" style="width: auto"  class="form-control" id="tel" name="tel" placeholder="手机号"><span id="telsp"></span>
            </div>
        </div>
        <div class="form-group">

            <div class="col-sm-10">

                <input  type="text" style="width: auto"  class="form-control" id="password" name="password"
                       placeholder="密码"><span id="pasp"></span>
            </div>
        </div>
      <input type="hidden" name="socialUid" value="${user.id}">
      <input type="hidden" name="headurl" value="${user.url}">
      <input type="hidden" name="sex" value="${user.gender}">
      <input type="hidden" name="address" value="${user.location}">

    </form>







    <button type="submit" id="register" class="btn btn-danger">注册</button>
    <button type="button" id="back" class="btn btn-primary">返回主页面</button>

</div>


<script>
    var pas=false;
    //密码验证
    $("#password").blur(function () {
        var str= $(this).val()
        if(str==""){
            $("#pasp").html("密码不能为空")
            $("#pasp").css("color","red")

        }else{
            var test=/^(\w){6,20}$/
            var tr= test.test(str)
            if(tr){
                pas=true;
                $("#pasp").html("")
            }else{

                $("#pasp").html("密码格式错误")
                $("#pasp").css("color","red")
            }
        }
    })


     var telRe=false
    //手机号验证
    $("#tel").blur(function () {
        var str= $(this).val()
        if(str==""){
            $("#telsp").html("手机号不能为空")
            $("#telsp").css("color","red")

        }else{
            var test=/^1[34578]\d{9}$/
            var tr= test.test(str)
            if(tr){
                telRe=true;
                $("#telsp").html("")
            }else{

                $("#telsp").html("手机格式错误")
                $("#telsp").css("color","red")
            }
        }
    })



    var result=false;
    //mail的验证
    $("#mail").blur(function () {
       var str= $(this).val()
        if(str==""){
           $("#mailsp").html("邮箱不能为空")
            $("#mailsp").css("color","red")

        }else{
           var test=/^[A-Za-z\d]+([-_.][A-Za-z\d]+)*@([A-Za-z\d]+[-.])+[A-Za-z\d]{2,4}$/
           var tr= test.test(str)
            if(tr){
               result=true;
                $("#mailsp").html("")
            }else{

                $("#mailsp").html("邮箱格式错误")
                $("#mailsp").css("color","red")
            }
        }
    })



    //返回主页面
    $("#back").click(function () {
        location.href = "${pageContext.request.contextPath}/index.jsp"
    })

    //点击注册按钮
    $("#register").click(function () {
        var user = $("#form").serialize()
        $("#mail").blur();
        $("#tel").blur();
        $("#password").blur()
       if(result==true && telRe==true && pas==true){
           $.ajax({
               url: "${pageContext.request.contextPath}/user/register",
               data: user,
               type: "post",
               success: function (data) {
                   if (data > 0) {
                       alert("注册成功")
                       location.reload();
                   } else {
                       alert("信息不能为空")
                   }
               }
           })
       }

    })


</script>

</body>
</html>
