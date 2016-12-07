<%@ page import="org.apache.catalina.Session" %><%--
  Created by IntelliJ IDEA.
  User: jimmyhsu
  Date: 2016/12/6
  Time: 23:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" href="../../static/style/weui.css"/>
    <link rel="stylesheet" href="../../static/example/example.css"/>
    <link rel="stylesheet" href="../../static/style/myStyle.css"/>
</head>
<table width="100%" >
    <tr>
        <td align="left"><a href="main.jsp"><img src="../../static/image/logo.png" height="60px" style="padding:10px"/></a></td>
        <td align="center" style="font-size:30px;color:#00ff00">青马班在线学习平台-管理员后台</td>
        <td align="right" style="vertical-align:bottom;padding:10px;font-size:10px">by Jimmy and Senia</td>
    </tr>
</table>
</div>
<div class="bd">
    <div class="weui_grids">
        <a href="import.jsp" class="weui_grid">
            <div class="weui_grid_icon">
                <i class="icon icon_actionSheet"></i>
            </div>
            <p class="weui_grid_label">
                导入
            </p>
        </a>
        <a href="attendance_index.jsp" class="weui_grid">
            <div class="weui_grid_icon">
                <i class="icon icon_panel"></i>
            </div>
            <p class="weui_grid_label">
                考勤
            </p>
        </a>
        <a href="credit.jsp" class="weui_grid">
            <div class="weui_grid_icon">
                <i class="icon icon_progress"></i>
            </div>
            <p class="weui_grid_label">
                学分
            </p>
        </a>
        <a href="student.jsp" class="weui_grid">
            <div class="weui_grid_icon">
                <i class="icon icon_search_bar"></i>
            </div>
            <p class="weui_grid_label">
                学生
            </p>
        </a>
        <a href="selectCourse.jsp" class="weui_grid">
            <div class="weui_grid_icon">
                <i class="icon icon_article"></i>
            </div>
            <p class="weui_grid_label">
                选课
            </p>
        </a>
        <a href="rating.jsp" class="weui_grid">
            <div class="weui_grid_icon">
                <i class="icon icon_dialog"></i>
            </div>
            <p class="weui_grid_label">
                评价
            </p>
        </a>
        <%
            HttpSession session1 = request.getSession();
            if(session1.getAttribute("admin") != null && session1.getAttribute("admin").equals("superadmin")) {%>
        <a href="manager.jsp" class="weui_grid">
            <div class="weui_grid_icon">
                <i class="icon icon_msg"></i>
            </div>
            <p class="weui_grid_label">
                管理员
            </p>
        </a>
        <% }%>
    </div>
</div>
</html>
