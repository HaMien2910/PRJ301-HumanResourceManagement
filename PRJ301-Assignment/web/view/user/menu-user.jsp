<%-- 
    Document   : menu-user
    Created on : Mar 17, 2022, 12:16:28 PM
    Author     : PhuongNH
--%>

<div class="sidebar-header text-center">
    <h3 style="color: #181c32">PRJ301 Assignment</h3>
    <div>
        <image style="border-radius:12px; height: 70px; margin-top: 16px;box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.1);" src="${pageContext.request.contextPath}/assets/images/user/usrbig6.jpg">
    </div>
    <div>
        <h5 style="color: #181c32"><strong>${sessionScope.account.employee.e_last_name} ${sessionScope.account.employee.e_first_name}</strong></h5>
    </div>
</div>
