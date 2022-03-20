<%-- 
    Document   : all-jobs
    Created on : Mar 2, 2022, 11:19:26 PM
    Author     : PhuongNH
--%>

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <title>HRM - List All Jobs</title>
        <link href="${pageContext.request.contextPath}/Bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    </head>

    <body>

        <div class="wrapper">

            <!-- Left Sidebar -->
            <nav id="sidebar">
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
                                        <h4 class="page-title"><strong>All Jobs</strong></h4>
                                    </li>
                                    <li class="breadcrumb-item bcrumb-1">
                                        <a href="../../index.html">
                                            <i class="fas fa-home"></i>Home</a>
                                    </li>
                                    <li class="breadcrumb-item bcrumb-2">
                                        <a href="#" onClick="return false;">Job</a>
                                    </li>
                                    <li class="breadcrumb-item active">All Jobs</li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="row" style="margin: 0">
                        <div class="col-lg-12 col-md-12 col-sm-12 table-responsive item-display">
                            <div class="content-title row" style="margin: 0 -12px;">
                                <div class="col-md-4">
                                    <h2 style="font-size: 16px">
                                        <strong>All Jobs</strong> | <a href="addJob" style="font-size: 14px;color: #337ab7">+ Add Job</a>
                                    </h2>
                                    <p>
                                        Total jobs ${requestScope.all_records} - Search results: ${requestScope.total_records_search_by_message}
                                </p>
                            </div>
                            <div class="col-md-8">
                                <form action="listAllJobs" method="GET" style="box-shadow: none;">
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
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th class="text-center">#</th>
                                    <th class="text-center"><a href="listAllJobs?page=${requestScope.page_index}&message=${requestScope.message}&field=job_title&sortIs=${requestScope.status}&column=${(requestScope.column eq "1") ? 1 :0}"> Job Title </a></th>
                                    <th class="text-center"><a href="listAllJobs?page=${requestScope.page_index}&message=${requestScope.message}&field=min_salary&sortIs=${requestScope.status}&column=${(requestScope.column eq "2") ? 2 :0}"> Min Salary </a></th>
                                    <th class="text-center"><a href="listAllJobs?page=${requestScope.page_index}&message=${requestScope.message}&field=max_salary&sortIs=${requestScope.status}&column=${(requestScope.column eq "3") ? 3 :0}"> Max Salary </a></th>
                                    <th class="text-center"><a href="listAllJobs?page=${requestScope.page_index}&message=${requestScope.message}&field=department_name&sortIs=${requestScope.status}&column=${(requestScope.column eq "4") ? 4 :0}"> Department </a></th>
                                    <th class="text-center"> Action </th>
                                </tr>
                            </thead>
                            <tbody>
                                <%!int i = 0;%>
                                <c:forEach items="${requestScope.jobs}" var="j">
                                    <tr class="" style="font-size: 12px;<%=(i % 2 == 0) ? "background-color: rgb(239, 239, 241);" : ""%>">
                                        <td></td>
                                        <td class="text-center">${j.job_title}</td>
                                        <td class="text-center">${j.min_salary}</td>
                                        <td class="text-center">${j.max_salary}</td>
                                         <td class="text-center">${j.department.department_name}</td>
                                        <td class="text-center">
                                            <a href="editJob?jid=${j.job_id}" class="btn btn-tbl-edit">
                                                <i class="fas fa-pen tbl-icon"></i>
                                            </a>
                                            <a class="btn btn-tbl-delete" onclick="onDelete()">
                                                <i class="fas fa-trash tbl-icon"></i>
                                            </a>
                                            <input type="hidden" id="jid" name="jid" value="${j.job_id}">
                                        </td>
                                    </tr>
                                <input type="hidden" value="<%=i++%>">
                            </c:forEach>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <th class="text-center">#</th>
                                    <th class="text-center"><a href="listAllJobs?page=${requestScope.page_index}&message=${requestScope.message}&field=job_title&sortIs=${requestScope.status}&column=${(requestScope.column eq "1") ? 1 :0}"> Job Title </a></th>
                                    <th class="text-center"><a href="listAllJobs?page=${requestScope.page_index}&message=${requestScope.message}&field=min_salary&sortIs=${requestScope.status}&column=${(requestScope.column eq "2") ? 2 :0}"> Min Salary </a></th>
                                    <th class="text-center"><a href="listAllJobs?page=${requestScope.page_index}&message=${requestScope.message}&field=max_salary&sortIs=${requestScope.status}&column=${(requestScope.column eq "3") ? 3 :0}"> Max Salary </a></th>
                                    <th class="text-center"><a href="listAllJobs?page=${requestScope.page_index}&message=${requestScope.message}&field=department_name&sortIs=${requestScope.status}&column=${(requestScope.column eq "4") ? 4 :0}"> Department </a></th>
                                    <th class="text-center"> Action </th>
                                </tr>
                            </tfoot>
                        </table>
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
                                                doPagging("block-footer", ${requestScope.page_index}, ${requestScope.total_pages}, "${requestScope.message}", "${requestScope.field}", "${requestScope.status}", "${requestScope.column}", 2, 'listAllJobs');
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
            var id = $(this).parent().find("#jid").val();
            var myHeading = "<p><strong>The job has jobid = " + id + "</strong></p>\n\
                                                                        <p>This action cannot be undone</p>\n\
                                                                        <input type=\"hidden\" id=\"jid\" value=\"" + id + "\" name=\"jid\"/>";
            $('.modal--content').html(myHeading);
            $('#confirmation .modal-confirm-btn').on('click', function () {
                $('.modal-form').attr('action', 'deleteJob');
            });
        });
    });
</script>