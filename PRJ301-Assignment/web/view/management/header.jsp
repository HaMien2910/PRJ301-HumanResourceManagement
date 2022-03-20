<%-- 
    Document   : header
    Created on : Mar 9, 2022, 5:44:31 PM
    Author     : PhuongNH
--%>

<!-- Header -->
<div class="header">
    <!-- Left Header -->
    <div class="header-first-element">
        <button type="button" id="sidebarCollapse" class="btn  btn-info">
            <i class="fas fa-align-left"></i>
            <span>Memu</span>
        </button>
    </div >
    <!-- #END Left Header -->
    <!-- Right Header -->
    <div class="header-second-element">
        <ul>
            <li class="header__navbar-item header__navbar-item--has-notify">
                <a href="" class="header__navbar-item-link">
                    <i class="fas fa-bell"></i>
                </a>
            </li>
            <li class="header__navbar-item">
                <a href="" class="header__navbar-item-link">
                    <i class="far fa-question-circle"></i>
                </a>
            </li>
            <li class="header__navbar-item-link header-profile" style="display: flex; align-items: center;">
                ${sessionScope.account.employee.e_last_name} ${sessionScope.account.employee.e_first_name} 
                <div class="img-profile" style="margin-left: 12px;">
                    <image style="border-radius:50%;" src="${pageContext.request.contextPath}/assets/images/user/user6.jpg">
                    <div class="detail__profile">
                        <ul class="detail__profile-list">
                            <li class="detail__profile-item">
                                <a href="#" style="padding: 0;"> 
                                    <i class="fas fa-user"></i>
                                    Profile
                                </a>
                            </li>
                            <li class="detail__profile-item">
                                <a href="../authen/changePassword" style="padding: 0;">
                                    <i class="fas fa-unlock"></i>
                                    Change Password
                                </a>
                            </li>     
                            <li class="detail__profile-item">
                                <a href="#" style="padding: 0;">
                                    <i class="fas fa-comment-dots"></i>
                                    Feedback
                                </a>
                            </li>                        
                            <li class="detail__profile-item">
                                <a href="${sessionScope.account.portal ?  "../authen/logout" : "../authen/userLogout"}" style="padding: 0;">
                                    <i class="fas fa-sign-out-alt"></i>
                                    Logout
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </li>
        </ul>
    </div>
    <!-- #END Header Header -->
</div>
<!-- #END Header -->