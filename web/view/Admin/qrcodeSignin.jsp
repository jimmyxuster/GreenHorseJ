<%@ page import="database.Course" %>
<%@ page import="database.CourseDb" %>
<%@ page import="utils.QrCodeUtil" %>
<%@ page import="utils.QrCode" %><%--
  Created by IntelliJ IDEA.
  User: jimmyhsu
  Date: 2016/12/9
  Time: 0:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>二维码签到</title>
    <link rel="stylesheet" href="../../static/style/weui.css"/>
    <link rel="stylesheet" href="../../static/example/example.css"/>
    <link rel="stylesheet" href="../../static/style/myStyle.css"/>
    <%
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        Course course = CourseDb.selectCourseById(courseId);
        String qrcodeKey = QrCodeUtil.createPassword(10);
        QrCode code = new QrCode();
        code.insertQRCode(courseId, qrcodeKey, 60 * 1000);


    %>
</head>
<body>
<h1 class = "page_title">二维码签到</h1>
<h3 align='center'><%=course.getName()%></h3>
<br/>
<div align="center"><img src="https://api.qrserver.com/v1/create-qr-code/?size=300x300&data=<%=qrcodeKey%>"/></div>
<h5 align='center'>*二维码有效时长 一分钟</h5>

</body>
</html>
