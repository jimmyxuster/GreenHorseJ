
<%@ page import="java.util.Iterator" %>
<%@ page import="database.CourseDb" %>
<%@ page import="org.json.JSONObject" %>
<%@ page import="utils.StringUtils" %><%--
  Created by IntelliJ IDEA.
  User: jimmyhsu
  Date: 2016/12/3
  Time: 19:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>保存中...</title>
    <%
        String studentAttendance = StringUtils.getUtf8String(request.getParameter("studentAttendance"));
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        JSONObject obj = new JSONObject(studentAttendance);

        Iterator it = obj.keys();
        while (it.hasNext()) {
            String studentId = (String) it.next();
            String attendance = obj.getString(studentId);
            CourseDb.updateTakecourse(studentId, courseId, attendance);
        }
        response.getWriter()
                .write("<script>alert('保存成功！');location.href='../attendance.jsp?courseId=" + courseId + "\';</script>");
    %>
</head>
<body>
    <h1>保存中...</h1>
</body>
</html>
