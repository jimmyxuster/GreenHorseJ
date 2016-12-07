<%@ page import="database.CourseDb" %>
<%@ page import="database.Course" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="utils.ExportExcelUtil" %>
<%@ page import="utils.Attendance" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="database.DatabaseBasic" %>
<%@ page import="java.text.SimpleDateFormat" %><%--
  Created by IntelliJ IDEA.
  User: jimmyhsu
  Date: 2016/12/7
  Time: 23:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>导出签到表</title>
    <%
        String courseId = request.getParameter("courseId");
        if (courseId == null || "".equals(courseId)) {
            response.getWriter().print("<script>location.href='../../error.jsp?error_code=101'</script>");
        } else {
            int cId = Integer.parseInt(courseId);
            Course course = CourseDb.selectCourseById(cId);
            List<Object> resultSetAndConnection = CourseDb.selectTakecourseAndUserInfoByCourseId(cId);
            ResultSet resultSet = (ResultSet) resultSetAndConnection.get(0);

            try {
                String header[] = new String[5];
                header[0] = "课程名称#courseName";
                header[1] = "上课时间#courseTime";
                header[2] = "学号#studentId";
                header[3] = "姓名#studentName";
                header[4] = "出席#attendance";
                String fileName = course.getName() + "("
                        + new SimpleDateFormat("yy年MM月dd日HH点mm分").format(course.getDatetime()) + ")出席统计";
                List<Attendance> dataList = new ArrayList<Attendance>();
                while (resultSet.next()) {
                    Attendance attendance = new Attendance(course.getName(), course.getDatetime()
                            , resultSet.getString("studentId"), resultSet.getString("name"),
                            resultSet.getString("attendance"));
                    dataList.add(attendance);
                }
                ExportExcelUtil.export(response, fileName, header, dataList);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                DatabaseBasic databaseBasic = (DatabaseBasic) resultSetAndConnection.get(1);
                databaseBasic.recycle();
            }
        }
//        response.getWriter().print("<script>location.href='../attendance.jsp?courseId=" + courseId + "\'</script>");
    %>
</head>
<body>

</body>
</html>
