<%@ page import="database.CourseDb" %>
<%@ page import="java.util.List" %>
<%@ page import="database.Course" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.SimpleDateFormat" %><%--
  Created by IntelliJ IDEA.
  User: jimmyhsu
  Date: 2016/12/4
  Time: 14:32
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
    <%
        int pageNumber = 1;
        final int pageSize = 10;
        String status = "ing";
        if (request.getParameter("status") != null && !"".equals(request.getParameter("status"))) {
            status = request.getParameter("status");
        }
        if (request.getParameter("page") != null && !"".equals(request.getParameter("page"))) {
            pageNumber = Integer.parseInt(request.getParameter("page"));
        }
        int maxPage = (int) Math.ceil(CourseDb.selectCoursesAfterDurationCount(0, 999) * 1.0f / pageSize);
        String edSelected = "";
        String ingSelected = "";
        String todoSelected = "";
        List<Course> courses = new ArrayList<Course>();
        if (status.equals("ed")) {
            edSelected = "selected";
            courses = CourseDb.selectCoursesAfterDuration((pageNumber - 1) * pageSize, pageSize);
        } else if (status.equals("ing")) {
            ingSelected = "selected";
            courses = CourseDb.selectOngoingCourses(0, 999);
        } else if (status.equals("todo")) {
            todoSelected = "selected";
            courses = CourseDb.selectAllFutureCourses(0, 999);
        }
    %>
</head>
<body>
<%@include file="head.jsp"%>
<h1 class = "page_title">考勤</h1>
<select id='status' onchange='selectCourseStatus(this.value)'>
    <option value='todo'<%=todoSelected%>>未开始的课程</option>
    <option value='ing'<%=ingSelected%>>正在进行的课程</option>
    <option value='ed' <%=edSelected%>>已结束课程</option>
</select>
<table class='zebra'>
    <tr>
        <th>课程</th>
        <th>时间</th>
        <th>操作</th>
    </tr>
    <%
        SimpleDateFormat df = new SimpleDateFormat("MM-dd HH:mm:SS");
        for (Course course : courses) {
            String time = df.format(course.getDatetime());
    %>
    <tr>
        <td><%=course.getName()%></td>
        <td><%=time%></td>
        <td>
            <button class="weui_btn weui_btn_mini weui_btn_default" style = "position: relative" onclick="onQRCode('<%=course.getId()%>')">二维码签到</button>
            <button class="weui_btn weui_btn_mini weui_btn_default" style = "position: relative" onclick="onAttendance('<%=course.getId()%>')">出席统计</button>
            <button class="weui_btn weui_btn_mini weui_btn_default" style = "position: relative" onclick="onTable('<%=course.getId()%>')">导出签到表</button>
        </td>
    </tr>
    <% }%>
</table>
<%
    if (status.equals("ed")) {
%>
<br><br>
<div id="buttons" align="center">
    <%
		if(pageNumber != 1) {
			%>
    <button class="weui_btn weui_btn_mini weui_btn_plain_default" style="position: relative"
            onclick="onPreviousPage(<%=pageNumber%>)">上一页
    </button>&nbsp;&nbsp;&nbsp;
    <%
		}
		%>
    <%
		if(pageNumber != maxPage) {
			%>
    <button class="weui_btn weui_btn_mini weui_btn_plain_default" style="position: relative"
            onclick="onNextPage(<%=pageNumber%>,<%=maxPage%>)">下一页
    </button>
    <%
		}
		%>
</div><br>
<%
}
%>
</body>
<script>
    function onQRCode(id){
        location.href="qrcodeSignin.jsp?courseId="+id;
    }

    function selectCourseStatus(status){
        location.href = 'attendance_index.jsp?status='+status;
    }
    function onAttendance(courseId){
        location.href = 'attendance.jsp?courseId='+courseId;
    }
    function onTable(courseId){
        location.href = 'Do/attendanceTable.jsp?courseId='+courseId;
    }
    function onPreviousPage(page){
        if(page>=2)
            page--;
        location.href = 'attendance_index.jsp?status=ed&page='+page;
    }
    function onNextPage(page, maxPage){
        if(page<maxPage)
            page++;
        location.href = 'attendance_index.jsp?status=ed&page='+page;
    }
</script>
</html>
