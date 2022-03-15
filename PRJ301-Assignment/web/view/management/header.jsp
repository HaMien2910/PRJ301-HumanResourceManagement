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
            <li class="header__navbar-item-link">
                ${sessionScope.account.username}
            </li>
        </ul>
    </div>
    <!-- #END Header Header -->
</div>
<!-- #END Header -->