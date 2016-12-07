<%@ page import="java.util.List" %>
<%@ page import="database.*" %>
<%@ page import="database.Class" %><%--
  Created by IntelliJ IDEA.
  User: jimmyhsu
  Date: 2016/12/7
  Time: 14:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>学分管理</title>
    <link rel="stylesheet" href="../../static/js/jquery.min.js"/>
    <link rel="stylesheet" href="../../static/style/weui.css"/>
    <link rel="stylesheet" href="../../static/example/example.css"/>
    <link rel="stylesheet" href="../../static/style/myStyle.css"/>
    <%
        int classId = 1;
        if (request.getParameter("classId") != null && !"".equals(request.getParameter("classId"))) {
            classId = Integer.parseInt(request.getParameter("classId"));
        }
        List<Class> classes = ClassDb.selectAllClasses();
        List<Credit> userCredits = CreditDb.selectUserCreditsByClassId(classId);

    %>
</head>
<body>
<%@include file="head.jsp"%>
<h1 class = "page_title">学分</h1>

<%
	for (Class aClass : classes) {
%>
<a href='credit.jsp?classId=<%=aClass.getId()%>'><%=aClass.getName()%></a>
<%
	}
%>
<table class = "zebra">
    <thead>
    <tr>
        <th>学号</th>
        <th>姓名</th>
        <th>已完成学分</th>
    </tr>
    </thead>
    <%
    for (Credit credit : userCredits){
        User user = UserDb.selectUserById(credit.getUserId());
        System.out.println(user.getId());
    %>
    <tr>
        <td><%=user.getId()%></td>
        <td><%=user.getName()%></td>
        <td><%=credit.getCredit()%></td>
    </tr>
    <%
    }%>
</table>
<br>

<button class="weui_btn weui_btn_default" style = "position: relative" type = "button" onclick="onTable('<%=classId%>')">导出表格...</button>

<a href="#" id="toTop" style="display: block;"> <span id="toTopHover" style="opacity: 1;"> </span></a>

</body>
<script>
    function onTable(classId){
        location.href='Do/creditTable.jsp?classId='+classId;
    }
</script>
<script type="text/javascript">
    $(document).ready(function() {
        $().UItoTop({ easingType: 'easeOutQuart' });
    });
</script>
</html>
