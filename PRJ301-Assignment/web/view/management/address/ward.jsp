<%-- 
    Document   : ward
    Created on : Feb 1, 2022, 5:21:34 PM
    Author     : PhuongNH
--%>

<%@page import="model.Ward"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dal.AddressDBContext"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%
            String district_id = request.getParameter("district_id");
            AddressDBContext addressDBContext = new AddressDBContext();
            ArrayList<Ward> wards = addressDBContext.getAllWardsByDistrictID(district_id);
        %>
    </head>
    <body>
        <select class="form-control" name="ward" id="ward">
            <option disabled selected> -- Select Ward -- </option>
            <%for (Ward w : wards) {%>
            <option value="<%=w.getWard_id()%>"><%=w.getWard_name()%></option>
            <%}%>
        </select>
    </body>
</html>
