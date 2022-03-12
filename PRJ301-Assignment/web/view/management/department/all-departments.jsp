<%-- 
    Document   : all-departments
    Created on : Feb 7, 2022, 5:25:27 PM
    Author     : PhuongNH
--%>

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <title>HRM - List All Departments</title>
        <link href="${pageContext.request.contextPath}/Bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    </head>

    <body>

        <div class="wrapper">

            <!-- Left Sidebar -->
            <nav id="sidebar">
                <!-- Top Left Sidebar -->
                <div class="sidebar-header text-center">
                    <h3 style="color: #181c32">Human Resource</h3>
                </div>
                <!-- #End Top Left Sidebar -->
                <jsp:include page="../menu.jsp"></jsp:include>
                </nav>
                <!-- #End Left Sidebar -->

                <!-- Content -->
                <div id="content" style="background-color: #f1f2f7;">
                    <!-- Header -->
                <jsp:include page="../header.jsp"></jsp:include>
                    <!-- #END Header -->


                    <div class="block-header">
                        <div class="row">
                            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                <ul class="breadcrumb breadcrumb-style" style="background-color: #f1f2f7; margin-bottom: 14px">
                                    <li class="breadcrumb-item">
                                        <h4 class="page-title"><strong>All Departments</strong></h4>
                                    </li>
                                    <li class="breadcrumb-item bcrumb-1">
                                        <a href="../../index.html">
                                            <i class="fas fa-home"></i>Home</a>
                                    </li>
                                    <li class="breadcrumb-item bcrumb-2">
                                        <a href="#" onClick="return false;">Department</a>
                                    </li>
                                    <li class="breadcrumb-item active">All Departments</li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="row" style="margin: 0">
                        <div class="col-lg-12 col-md-12 col-sm-12 table-responsive item-display">
                            <div>
                                <h2 style="font-size: 16px">
                                    <strong>All Departments</strong>
                                </h2>
                            </div>
                            <table class="table table-hover">
                                <thead>
                                    <tr>
                                        <th class="text-center">#</th>
                                        <th class="text-center"> Dept. Name </th>
                                        <th class="text-center"> Manager </th>
                                        <th class="text-center"> Phone </th>
                                        <th class="text-center"> Email </th>
                                        <th class="text-center"> Total Emp. </th>
                                        <th class="text-center"> Action </th>
                                    </tr>
                                </thead>
                                <tbody>
                                <%!int i = 0;%>
                                <c:forEach items="${requestScope.departments}" var="d">
                                    <tr class="" style="font-size: 12px;<%=(i % 2 == 0) ? "background-color: rgb(239, 239, 241);" : ""%>">
                                        <td></td>
                                        <td class="text-center">${d.department_name}</td>
                                        <td class="text-center">${d.manager.e_last_name} ${d.manager.e_first_name}</td>
                                        <td class="text-center">${d.department_phone}</td>
                                        <td class="text-center">${d.department_email}</td>
                                        <td class="text-center">${d.employees.size()}</td>
                                        <td class="text-center">
                                            <a href="viewDepartment?did=${d.department_id}" class="btn btn-tbl-view">
                                                <i class="fas fa-eye tbl-icon"></i>
                                            </a>
                                            <a href="editDepartment?did=${d.department_id}" class="btn btn-tbl-edit">
                                                <i class="fas fa-pen tbl-icon"></i>
                                            </a>
                                            <a class="btn btn-tbl-delete" onclick="onDelete()">
                                                <i class="fas fa-trash tbl-icon"></i>
                                            </a>
                                            <input type="hidden" id="did" name="did" value="${d.department_id}">
                                        </td>
                                    </tr>
                                <input type="hidden" value="<%=i++%>">
                            </c:forEach>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <th class="text-center">#</th>
                                    <th class="text-center"> Dept. Name </th>
                                    <th class="text-center"> Manager </th>
                                    <th class="text-center"> Phone </th>
                                    <th class="text-center"> Email </th>
                                    <th class="text-center"> Total Emp. </th>
                                    <th class="text-center"> Action </th>
                                </tr>
                            </tfoot>
                        </table>
                        <div class="row"style="margin: 0 -12px;">
                            <div class="col-sm-8"></div>
                            <div id="block-footer"class="col-sm-4">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- #End Content -->
    </div>

    <!-- #Modal To Delete An Employee -->
    <jsp:include page="../modal-delete.jsp"></jsp:include>
    </nav>
    <!-- #End Modal To Delete An Employee -->
    <script src="${pageContext.request.contextPath}/assets/js/pagger.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/management.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/Bootstrap/js/Jquery.js"></script>
<script src="${pageContext.request.contextPath}/Bootstrap/js/bootstrap.min.js"></script>
<script>
                                                doPagging("block-footer", ${requestScope.page_index}, ${requestScope.total_pages}, 2, 'listAllDepartments');
</script>
</body>
</html>
<script>
    $(document).ready(function () {
        $('#sidebarCollapse').on('click', function () {
            $('#sidebar').toggleClass('active');
        });
    });
    $(document).ready(function () {
        $('table .btn-tbl-delete').on('click', function () {
            var id = $(this).parent().find("#did").val();
            var myHeading = "<p><strong>The department has departmentid = " + id + "</strong></p>\n\
                                                                        <p>This action cannot be undone</p>\n\
                                                                        <input type=\"hidden\" id=\"did\" value=\"" + id + "\" name=\"did\"/>";
            $('.modal--content').html(myHeading);
            $('#confirmation .modal-confirm-btn').on('click', function () {
                $('.modal-form').attr('action', 'deleteDepartment');
            });
        });
    });
</script>