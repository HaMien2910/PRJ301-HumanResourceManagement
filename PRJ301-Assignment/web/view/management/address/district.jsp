<%-- 
    Document   : district
    Created on : Feb 1, 2022, 5:21:52 PM
    Author     : PhuongNH
--%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page import="model.District"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dal.AddressDBContext"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%
            String province_id = request.getParameter("province_id");
            AddressDBContext addressDBContext = new AddressDBContext();
            ArrayList<District> districts = addressDBContext.getAllDistrictsByProvinceID(province_id);
        %>
    </head>
    <body>
        <select class="form-control" name="district_id" id="district_id" required>
            <option value=""> -- Select District -- </option>
            <%for (District d : districts) { %>
            <option value="<%=d.getDistrict_id()%>"><%=d.getDistrict_name()%></option>
            <%}%>
        </select>
    </body>
</html>
<script>
    $(document).ready(function () {
        $("#district_id").on('change', function () {
            var district_id = $("#district_id").val();
            $("#error").html("");
            $.ajax({
                url: "../../../PRJ301-Assignment/view/management/address/ward.jsp",
                data: {district_id: district_id},
                method: "POST",
                success: function (data) {
                    $("#ward_id").html(data);
                }
            });
        });
    });
</script>
