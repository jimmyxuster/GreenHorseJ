<%@ page import="database.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="database.UserDb" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="utils.StringUtils" %><%--
  Created by IntelliJ IDEA.
  User: jimmyhsu
  Date: 2016/11/30
  Time: 12:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script type="text/javascript" src="../../static/js/myjs.js"></script>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <title>学生</title>
    <link rel="stylesheet" href="../../static/style/weui.css"/>
    <link rel="stylesheet" href="../../static/example/example.css"/>
    <link rel="stylesheet" href="../../static/style/myStyle.css"/>
    <%
        int pageNum = 0;
        if (request.getParameter("p") != null) {
            pageNum = Integer.parseInt(request.getParameter("p"));
        }
        int limit = 20;
        List<User> users;
        Map<String, String> conditions = new HashMap<String, String>();
        String orderby = "";

        if(request.getParameter("searchId") != null){
            String searchId = StringUtils.getUtf8String(request.getParameter("searchId"));
            if (!"".equals(searchId)) {
                conditions.put("id", request.getParameter("searchId"));
            }
        }
        if (request.getParameter("searchName") != null) {
            String searchName = StringUtils.getUtf8String(request.getParameter("searchName"));
            if (!searchName.equals("")){
                conditions.put("name", searchName);
            }
        }
        if (request.getParameter("searchClassName") != null) {
            String searchClassName = StringUtils.getUtf8String(request.getParameter("searchClassName"));
            if (!searchClassName.equals("")){
                conditions.put("className", searchClassName);
            }
        }
        if(request.getParameter("orderby") != null && !request.getParameter("orderby").equals("")){
            orderby = StringUtils.getUtf8String(request.getParameter("orderby"));
        }
        users = UserDb.selectUserByConditions(conditions, orderby, pageNum * limit, limit);
        int next = pageNum;
        int previous = pageNum;
        if (users != null && users.size() == limit) {
            next++;
        }
        if (pageNum > 0) {
            previous--;
        }
    %>
</head>
<body>
<%@include file="head.jsp"%>
<h1 class = "page_title">学生&nbsp;<i class="icon icon_actionSheet" onclick='onImport()'></i></h1>

<div align='center'>
    <form action='student.jsp' method='get'>
        学号<input type='text' placeholder='学号' name='searchId'/>
        姓名<input type='text' placeholder='姓名' name='searchName'/>
        班级<input type='text' placeholder='班级' name='searchClassName' value=
            "<%=request.getParameter("searchClassName")==null || request.getParameter("searchClassName").equals("null")?"":request.getParameter("searchClassName")%>"/>
        <input class="weui_btn weui_btn_mini weui_btn_primary" style = "position: relative" type='submit' value='查找'/>
    </form>
</div>
<table class='zebra'>
    <tr>
        <th width="8%"><a href='student.jsp?searchClassName=<%=request.getParameter("searchClassName")%>&orderby=id'>学号</a></th>
        <th width="9%"><a href='student.jsp?searchClassName=<%=request.getParameter("searchClassName")%>&orderby=name'>姓名</a></th>
        <th width="8%"><a href='student.jsp?searchClassName=<%=request.getParameter("searchClassName")%>&orderby=sex'>性别</a></th>
        <th width="12%"><a href='student.jsp?searchClassName=<%=request.getParameter("searchClassName")%>&orderby=classId'>班级</a></th>
        <th width="17%"><a href='student.jsp?searchClassName=<%=request.getParameter("searchClassName")%>&orderby=institute'>学院</a></th>
        <!--		<th width="14%"><a href='students.php?searchClassName=--><?php //echo $_GET['searchClassName']?><!--&orderby=branch'>支部</a></th>-->
        <!--		<th width="10%"><a href='students.php?searchClassName=--><?php //echo $_GET['searchClassName']?><!--&orderby=grade'>年级</a></th>-->
        <!--		<th width="10%"><a href='students.php?searchClassName=--><?php //echo $_GET['searchClassName']?><!--&orderby=dormitory'>宿舍</a></th>-->
        <th><a href='student.jsp?searchClassName=<%=request.getParameter("searchClassName")%>&orderby=tel'>电话</a></th>
        <th><a href='student.jsp?searchClassName=<%=request.getParameter("searchClassName")%>&orderby=email'>邮件</a></th>
        <th width="8%"><a href='student.jsp?searchClassName=<%=request.getParameter("searchClassName")%>&orderby=credit'>学分</a></th>
        <th width="5%"></th>
    </tr>
    <%
	for (User user : users){
//		if(!user.getPermission().equals("superadmin")){
    %>
    <tr>
        <td ondblclick="onName(<%=user.getId()%>)" id="name<%=user.getId()%>"><%=user.getId()%></td>
        <td><%=user.getName()%></td>
        <td><%=user.getSex()%></td>
        <td><%=user.getUserInClass()%></td>
        <td><%=user.getInstitute()%></td>
        <!--		<td>--><?php //echo $user['branch']?><!--</td>-->
        <!--		<td>--><?php //echo $user['grade']?><!--</td>-->
        <!--		<td>--><?php //echo $user['dormitory']?><!--</td>-->
        <td><%=user.getTel()%></td>
        <td><%=user.getEmail()%></td>
        <td><%=user.getCredit()%></td>
        <td>
            <button class="weui_btn weui_btn_mini weui_btn_default" style = "position: relative" type = "button"  onclick="removeStudent(<%=user.getId()%>)">×</button>
        </td>
    </tr>
    <%
//	    }
    }
    %>
</table>
<div align='center'>
    <a href='student.jsp?p=<%=previous%>&searchClassName=<%=request.getParameter("searchClassName")%>&orderby=<%=orderby%>'>上一页</a>
    <a href='student.jsp?p=<%=next%>&searchClassName=<%=request.getParameter("searchClassName")%>&orderby=<%=orderby%>'>下一页</a>
</div>
</body>
<script>

    function removeStudent(studentId)
    {
        if (window.confirm("将要把这个学生从数据库中删除。确定吗?"))
        {
            location.href = 'Do/doRemoveStudent.jsp?studentId='+studentId;
        }
    }
    function onName(userId){

    }
    function onImport(){
        location.href='importStudent.jsp';
    }
</script>
</html>
