<%@ page import="database.CourseDb" %>
<%@ page import="database.Course" %>
<%@ page import="database.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: jimmyhsu
  Date: 2016/12/3
  Time: 0:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>考勤管理</title>
    <link rel="stylesheet" href="../../static/style/weui.css"/>
    <link rel="stylesheet" href="../../static/example/example.css"/>
    <link rel="stylesheet" href="../../static/style/myStyle.css"/>
    <script type="text/javascript" src="../../static/js/myjs.js"></script>
    <%
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        Course course = CourseDb.selectCourseById(courseId);
        List<User> students = CourseDb.selectStudentsInCourse(courseId);
        System.out.println("student count: " + students.size());
    %>
</head>
<body>
<%@include file="head.jsp"%>
<h1 class = "page_title"><%=course.getName()%>考勤</h1>
<table class = "zebra">
    <thead>
    <tr>
        <th>学号</th>
        <th>姓名</th>
        <th>出勤情况&nbsp;</th>
    </tr>
    </thead>
    <%
        for (User user : students) {
            boolean present = false;
            boolean ill = false;
            boolean absent = false;
            String takecourse = CourseDb.selectTakecourse(user.getId(), courseId);
            present = takecourse.equals("出席");
            ill = takecourse.equals("请假");
            absent = takecourse.equals("缺席");
    %>
    <tr>
        <td><%=user.getId()%></td>
        <td><%=user.getName()%></td>
        <td>
            <input type = "radio" value = "出席" name = "attendance<%=user.getId()%>>" onclick="onChangeAttendance('<%=user.getId()%>',this.value)" <%=present%>/>出席
            <input type = "radio" value = "请假" name = "attendance<%=user.getId()%>" onclick="onChangeAttendance('<%=user.getId()%>',this.value)"<%=ill%>/>请假
            <input type = "radio" value = "缺席" name = "attendance<%=user.getId()%>" onclick="onChangeAttendance('<%=user.getId()%>',this.value)"<%=absent%>/>缺席
        </td>
    </tr>
    <% }%>
</table>
<form name='form' id='form' method='post' action='Do/saveAttendance.jsp'>
    <input type='hidden' name='studentAttendance' id='studentAttendance'>
    <input type='hidden' name='courseId' value='<%=courseId%>'>

    <br>
    <button class="weui_btn weui_btn_default" style = "position: relative" onclick = 'onSave()'>保存</button>
</form>
<button class="weui_btn weui_btn_default" style = "position: relative" type = "button" onclick="clickExport('<%=courseId%>')">导出签到表</button>
</body>
<script>
    var attendance = {};
    function clickExport(courseId){
        location.href='Do/attendanceTable.php?courseId='+courseId;
    }
    function onSave(){
        document.getElementById('studentAttendance').value = JSON.stringify(attendance);
        document.form.submit();
    }
    function onChangeAttendance(studentId,value){
        attendance[studentId] = value;
    }
</script>
</html>
