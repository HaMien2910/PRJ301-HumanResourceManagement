<%-- 
    Document   : all-employees
    Created on : Jan 9, 2022, 10:21:28 PM
    Author     : PhuongNH
--%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <title>HRM - List All Employees</title>
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
                                        <h4 class="page-title"><strong>All Employees</strong></h4>
                                    </li>
                                    <li class="breadcrumb-item bcrumb-1">
                                        <a href="../../index.html">
                                            <i class="fas fa-home"></i>Home</a>
                                    </li>
                                    <li class="breadcrumb-item bcrumb-2">
                                        <a href="#" onClick="return false;">Employee</a>
                                    </li>
                                    <li class="breadcrumb-item active">All Employees</li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="row" style="margin: 0">
                        <div class="col-lg-12 col-md-12 col-sm-12 table-responsive item-display">
                            <div class="content-title row" style="margin: 0 -12px;">
                                <div class="col-md-4">
                                    <h2 style="font-size: 16px">
                                        <strong>All Employees</strong> | <a href="addEmployee" style="font-size: 14px;color: #337ab7">+ Add Employee</a>
                                    </h2>
                                    <p>
                                        Total employees: ${requestScope.all_records} - Search results: ${requestScope.total_records_search_by_message}
                                </p>
                            </div>
                            <div class="col-md-8">
                                <form action="listAllEmployees" method="GET" style="box-shadow: none;">
                                    <div class="form-row">
                                        <div class="col-sm-4 my-1">
                                            <input type="text" width="200px;" class="form-control" id="inlineFormInputName" value="${requestScope.message}" name="message">
                                        </div>
                                        <div class="col-auto my-1">
                                            <input type="submit" class="btn btn-primary" onclick="doSearch()" value="Search">
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <div>
                            <table class="table table-hover">
                                <thead>
                                    <tr>
                                        <th class="text-center">#</th>
                                        <th class="text-center"><a href="listAllEmployees?page=${requestScope.page_index}&message=${requestScope.message}&field=e_first_name&sortIs=${requestScope.status}"> Name </a></th>
                                        <th class="text-center"><a href="listAllEmployees?page=${requestScope.page_index}&message=${requestScope.message}&field=job_title&sortIs=${requestScope.status}"> Job </a></th>
                                        <th class="text-center"><a href="listAllEmployees?page=${requestScope.page_index}&message=${requestScope.message}&field=dapartment_name&sortIs=${requestScope.status}"> Department </a></th>
                                        <th class="text-center"><a href="listAllEmployees?page=${requestScope.page_index}&message=${requestScope.message}&field=e_phone&sortIs=${requestScope.status}"> Mobile </a></th>
                                        <th class="text-center"><a href="listAllEmployees?page=${requestScope.page_index}&message=${requestScope.message}&field=e_email&sortIs=${requestScope.status}"> Email </a></th>
                                        <th class="text-center"><a href="listAllEmployees?page=${requestScope.page_index}&message=${requestScope.message}&field=province_name&sortIs=${requestScope.status}"> Address </a></th>
                                        <th class="text-center"><a href="listAllEmployees?page=${requestScope.page_index}&message=${requestScope.message}&field=e_join_date&sortIs=${requestScope.status}"> Joining Date </a></th>
                                        <th class="text-center"><a href="#"> Action </a></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%!int i = 0;%>
                                    <c:forEach items="${requestScope.employees}" var="e">
                                        <tr class="" style="font-size: 12px;<%=(i % 2 == 0) ? "background-color: rgb(239, 239, 241);" : ""%>">
                                            <td></td>
                                            <td class="text-center">${e.e_last_name} ${e.e_first_name}</td>
                                            <td class="text-center">${e.job.job_title}</td>
                                            <td class="text-center">${e.department.department_name}</td>
                                            <td class="text-center">${e.e_phone}</td>
                                            <td class="text-center">${e.e_email}</td>
                                            <td class="text-center">${e.location.ward.district.province.province_name}</td>
                                            <td class="text-center">${e.e_join_date}</td>
                                            <td class="text-center">
                                                <a href="viewEmployee?eid=${e.e_id}" class="btn btn-tbl-view">
                                                    <i class="fas fa-eye tbl-icon"></i>
                                                </a>
                                                <a href="editEmployee?eid=${e.e_id}" class="btn btn-tbl-edit">
                                                    <i class="fas fa-pen tbl-icon"></i>
                                                </a>
                                                <a class="btn btn-tbl-delete" onclick="onDelete()">
                                                    <i class="fas fa-trash tbl-icon"></i>
                                                </a>
                                                <input type="hidden" id="eid" name="eid" value="${e.e_id}">
                                            </td>
                                        </tr>
                                    <input type="hidden" value="<%=i++%>">
                                </c:forEach>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <th class="text-center">#</th>
                                        <th class="text-center"><a href="listAllEmployees?page=${requestScope.page_index}&message=${requestScope.message}&field=e_first_name&sortIs=${requestScope.status}"> Name </a></th>
                                        <th class="text-center"><a href="listAllEmployees?page=${requestScope.page_index}&message=${requestScope.message}&field=job_title&sortIs=${requestScope.status}"> Job </a></th>
                                        <th class="text-center"><a href="listAllEmployees?page=${requestScope.page_index}&message=${requestScope.message}&field=dapartment_name&sortIs=${requestScope.status}"> Department </a></th>
                                        <th class="text-center"><a href="listAllEmployees?page=${requestScope.page_index}&message=${requestScope.message}&field=e_phone&sortIs=${requestScope.status}"> Mobile </a></th>
                                        <th class="text-center"><a href="listAllEmployees?page=${requestScope.page_index}&message=${requestScope.message}&field=e_email&sortIs=${requestScope.status}"> Email </a></th>
                                        <th class="text-center"><a href="listAllEmployees?page=${requestScope.page_index}&message=${requestScope.message}&field=province_name&sortIs=${requestScope.status}"> Address </a></th>
                                        <th class="text-center"><a href="listAllEmployees?page=${requestScope.page_index}&message=${requestScope.message}&field=e_join_date&sortIs=${requestScope.status}"> Joining Date </a></th>
                                        <th class="text-center"><a href="#"> Action </a></th>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <div class="row" style="margin: 0 -12px;display: flex;align-items: center;">
                            <div class="col-sm-8">
                                <c:if test="${requestScope.total_pages > 1}">
                                    <p>Page: ${requestScope.page_index} / ${requestScope.total_pages}</p>
                                </c:if>
                            </div>
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
                                                    doPagging("block-footer", ${requestScope.page_index}, ${requestScope.total_pages}, "${requestScope.message}", 2, 'listAllEmployees');
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
            var id = $(this).parent().find("#eid").val();
            var myHeading = "<p><strong>The employee has employeeid = " + id + "</strong></p><p>This action cannot be undone</p><input type=\"hidden\" id=\"eid\" value=\"" + id + "\" name=\"eid\"/>";
            $('.modal--content').html(myHeading);
            $('#confirmation .modal-confirm-btn').on('click', function () {
                $('.modal-form').attr('action', 'deleteEmployee');
            });
        });
    });
</script>