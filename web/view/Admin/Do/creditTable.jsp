<%@ page import="java.util.List" %>
<%@ page import="utils.ExportExcelUtil" %>
<%@ page import="database.*" %>
<%@ page import="database.Class" %><%--
  Created by IntelliJ IDEA.
  User: jimmyhsu
  Date: 2016/12/8
  Time: 19:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>导出学分表</title>
</head>
<body>
<%
    String classIdStr = request.getParameter("classId");
    if (classIdStr == null || "".equals(classIdStr)) {
        response.getWriter().print("<script>location.href='../../error.jsp?error_code=101'</script>");
    } else {
        int classId = Integer.parseInt(classIdStr);
        Class mClass = ClassDb.selectClassById(classId);
        List<Credit> credits = CreditDb.selectUserCreditsByClassId(classId);
        for (Credit credit : credits) {
            credit.setName(UserDb.selectUserById(credit.getUserId()).getName());
        }
        try {
            String header[] = new String[3];
            header[0] = "学号#userId";
            header[1] = "姓名#name";
            header[2] = "学分#credit";
            String fileName = mClass.getName() + "学生学分统计";
            String bigTitle = fileName;
            ExportExcelUtil.export(response, fileName, bigTitle, header, credits);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
%>

</body>
</html>
