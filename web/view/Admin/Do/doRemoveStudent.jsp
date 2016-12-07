<%@ page import="database.UserDb" %><%--
  Created by IntelliJ IDEA.
  User: jimmyhsu
  Date: 2016/12/6
  Time: 20:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>移除学生</title>
</head>
<body>
<%
    if (request.getParameter("studentId") == null) {
        response.getWriter().print("<script>location.href='../../error.jsp?error_code=103'</script>");
    } else {
        String studentId = request.getParameter("studentId");
        UserDb.removeStudent(studentId);
        response.getWriter().print("<script>location.href='../student.jsp'</script>");
    }
%>

</body>
</html>
