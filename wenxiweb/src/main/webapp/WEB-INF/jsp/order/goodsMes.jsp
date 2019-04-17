<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 联想辉哥
  Date: 2019/4/17
  Time: 16:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <SCRIPT src="/js/jquery-1.9.1.min.js"></SCRIPT>
    <title>Title</title>
</head>
<body>
<c:if test="${sessionScope.user!=null}">
    <table>
        <tr>

            <Td>商品编号</Td>
            <Td>数量</Td>
            <TD>价格</TD>

        </tr>
            <%-- 集合存储的是map --%>

        <c:forEach items="${gmes}" var="goods">
            <tr>

                <Td>${goods.id}</Td>
                <Td>${goods.num}</Td>
                <Td>33</Td>

            </tr>
        </c:forEach>

    </table>
    总价:${num*33} <BUTTON id="submit">生成订单</BUTTON>
</c:if>
<c:if test="${sessionScope==null}">
     <a href="/index/login">请登录</a>
</c:if>

<script>
    $("#submit").click(function () {
           $.ajax({
               url:"${pageContext.request.contextPath}/goods/submit",
               success:function (data) {
                  if(data=="ok"){
                      alert("订单生成")
                  }
               }
           })
    })
</script>
</body>
</html>
