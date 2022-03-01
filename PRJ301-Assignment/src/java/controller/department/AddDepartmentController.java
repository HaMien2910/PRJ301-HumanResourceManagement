/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.department;

import dal.DepartmentDBContext;
import dal.EmployeeDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Department;
import model.Employee;

/**
 *
 * @author PhuongNH
 */
public class AddDepartmentController extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EmployeeDBContext employeeDBContext = new EmployeeDBContext();

        ArrayList<Employee> employees = employeeDBContext.getAllEmployees();

        request.setAttribute("employees", employees);
        request.getRequestDispatcher("../view/management/department/add-department.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Department department = new Department();
        department.setDepartment_name(request.getParameter("department_name"));
        department.setDepartment_phone(request.getParameter("department_phone"));
        department.setDepartment_email(request.getParameter("department_email"));
        department.setDescription(request.getParameter("description"));

        Employee employee = new Employee();
        String e_id = request.getParameter("manager_id");
        if (e_id.trim().length()>0) {
            System.out.println("======m1:" + e_id);
            employee.setE_id(Integer.parseInt(e_id));
        } else {
            System.out.println("======m2:" + e_id);
            employee.setE_id(-1);
        }
        department.setManager(employee);

        DepartmentDBContext departmentDBContext = new DepartmentDBContext();
        departmentDBContext.addDepartment(department);

        response.sendRedirect("listAllDepartments");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
