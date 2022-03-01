<%-- 
    Document   : list
    Created on : Feb 27, 2022, 10:01:35 PM
    Author     : PhuongNH
--%>

<%@page import="model.Job"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dal.JobDBContext"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%
            int department_id = Integer.parseInt(request.getParameter("department_id"));
            JobDBContext jobDBContext = new JobDBContext();
            ArrayList<Job> jobs = jobDBContext.getJobsByDepartmentID(department_id);
        %>
    </head>
    <body>
        <select class="form-control" name="job_id" id="job_id" required>
            <option value=""> -- Select Job -- </option>
            <%for (Job j : jobs) {%>
            <option value="<%=j.getJob_id()%>"><%=j.getJob_title()%></option>
            <%}%>
        </select>
    </body>
</html>