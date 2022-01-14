<%-- 
    Document   : all-employees
    Created on : Jan 9, 2022, 10:21:28 PM
    Author     : PhuongNH
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <title>Collapse sidebar in Bootstrap 4</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    </head>

    <body>


        <div class="wrapper">

            <nav id="sidebar">

                <div class="sidebar-header">
                    <h3>Bootstrap Slider</h3>
                </div>
                <ul class="lisst-unstyled components">
                    <p>The Providers</p>
                    <li class="active">
                        <a href="#homeSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">Employees</a>
                        <ul class="collapse list-unstyled" id="homeSubmenu">
                            <li>
                                <a href="#">All Employees</a>
                            </li>
                            <li>
                                <a href="#">Add Employee</a>
                            </li>
                            <li>
                                <a href="#">Edit Employee</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="#">About</a>
                    </li>
                    <li>
                        <a href="#pageSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">Pages</a>
                        <ul class="collapse list-unstyled" id="pageSubmenu">
                            <li>
                                <a href="#">Page 1</a>
                            </li>
                            <li>
                                <a href="#">Page 2</a>
                            </li>


                        </ul>
                    </li>

                    <li>
                        <a href="#">Policy</a>
                    </li>

                    <li>
                        <a href="#">Contact Us</a>
                    </li>

                </ul>
            </nav>


            <div id="content">

                <nav class="navbar navbar-expand-lg navbar-light bg-light">
                    <div class="container-fluid">
                        <button type="button" id="sidebarCollapse" class="btn  btn-info">
                            <i class="fas fa-align-left"></i>
                            <span>Toggle Sidebar</span>

                        </button>
                    </div>
                </nav>

                <div class="block-header">
                    <div class="row">
                        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                            <ul class="breadcrumb breadcrumb-style ">
                                <li class="breadcrumb-item">
                                    <h4 class="page-title">All Employees</h4>
                                </li>
                                <li class="breadcrumb-item bcrumb-1">
                                    <a href="../../index.html">
                                        <i class="fas fa-home"></i> Home</a>
                                </li>
                                <li class="breadcrumb-item bcrumb-2">
                                    <a href="#" onClick="return false;">Employee</a>
                                </li>
                                <li class="breadcrumb-item active">All Employees</li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12 col-md-12 col-sm-12">
                        <div class="table-responsive">
                            <table class="table table-hover js-basic-example contact_list">
                                <thead>
                                    <tr>
                                        <th class="center">#</th>
                                        <th class="center"> Name </th>
                                        <th class="center"> Designation </th>
                                        <th class="center"> Mobile </th>
                                        <th class="center"> Email </th>
                                        <th class="center"> Address </th>
                                        <th class="center">Joining Date</th>
                                        <th class="center"> Action </th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr class="odd gradeX">
                                        <td></td>
                                        <td class="center">Rajesh</td>
                                        <td class="center">Programmer</td>
                                        <td class="center">+ 167-894-2378</td>
                                        <td class="center"><a href="https://www.radixtouch.com/cdn-cgi/l/email-protection" class="__cf_email__" data-cfemail="4f2a372e223f232a0f2a222e2623612c2022">[email&#160;protected]</a></td>
                                        <td class="center">22,tilak appt. surat</td>
                                        <td class="center">22 Feb 2000</td>
                                        <td class="center">
                                            <a href="edit-employee.html" class="btn btn-tbl-edit">
                                                <i class="material-icons">create</i>
                                            </a>
                                            <a href="#" class="btn btn-tbl-delete">
                                                <i class="material-icons">delete_forever</i>
                                            </a>
                                        </td>
                                    </tr>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <th class="center">#</th>
                                        <th class="center"> Name </th>
                                        <th class="center"> Designation </th>
                                        <th class="center"> Mobile </th>
                                        <th class="center"> Email </th>
                                        <th class="center"> Address </th>
                                        <th class="center">Joining Date</th>
                                        <th class="center"> Action </th>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                    </div>
                </div>
            </div>

        </div>












        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

        <script>
                                        $(document).ready(function () {
                                            $('#sidebarCollapse').on('click', function () {
                                                $('#sidebar').toggleClass('active');
                                            });
                                        });
        </script>


    </body>

</html>