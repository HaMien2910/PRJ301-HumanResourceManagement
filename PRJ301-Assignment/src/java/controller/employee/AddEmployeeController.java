/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.employee;

import com.google.gson.Gson;
import dal.DepartmentDBContext;
import dal.AddressDBContext;
import dal.EmployeeDBContext;
import dal.JobDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Department;
import model.Employee;
import model.Job;
import model.Location;
import model.Province;
import model.Ward;

/**
 *
 * @author PhuongNH
 */
public class AddEmployeeController extends HttpServlet {

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
        DepartmentDBContext departmentDBContext = new DepartmentDBContext();
        AddressDBContext provinceDBContext = new AddressDBContext();

        ArrayList<Department> departments = departmentDBContext.getAllDepartments(); // Get
        ArrayList<Province> provinces = provinceDBContext.getAllProvinces();

        request.setAttribute("departments", departments);
        request.setAttribute("provinces", provinces);

        request.getRequestDispatcher("../view/management/employee/add-employee.jsp").forward(request, response);
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
        Employee employee = new Employee();
        employee.setE_first_name(request.getParameter("first_name"));
        employee.setE_last_name(request.getParameter("last_name"));

        Department department = new Department();
        department.setDepartment_id(Integer.parseInt(request.getParameter("department_id")));
        // Get the manager_id of the department
        int manager_id = getManagerOfDepartment(department);
        Employee e = new Employee();
        if (manager_id > 0) {
            e.setE_id(manager_id);
        }
        department.setManager(e);
        employee.setDepartment(department);

        Job job = new Job();
        job.setJob_id(Integer.parseInt(request.getParameter("job_id")));
        employee.setJob(job);

        employee.setE_salary(Double.parseDouble(request.getParameter("salary")));
        employee.setE_email(request.getParameter("email"));
        employee.setE_phone(request.getParameter("phone"));
        employee.setE_gender(request.getParameter("gender").equals("male"));
        employee.setE_dob(Date.valueOf(request.getParameter("dob")));

        Location location = new Location();
        location.setStreet(request.getParameter("street"));
        Ward ward = new Ward();
        ward.setWard_id(request.getParameter("ward"));
        location.setWard(ward);
        employee.setLocation(location);

        EmployeeDBContext employeeDBContext = new EmployeeDBContext();
        employeeDBContext.addEmployee(employee);
        response.sendRedirect("listAllEmployees");
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

    private int getManagerOfDepartment(Department department) {
        DepartmentDBContext departmentDBContext = new DepartmentDBContext();
        ArrayList<Department> departments = departmentDBContext.getAllDepartments();
        // Loop to access all information of the departments
        for (Department department1 : departments) {
            // check if department_id duplicate then return manager_id
            if (department1.getDepartment_id() == department.getDepartment_id()) {
                return department1.getManager().getE_id();
            }
        }
        return -1;
    }
}
