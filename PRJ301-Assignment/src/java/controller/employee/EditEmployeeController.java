/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.employee;

import controller.login_security.BaseAuthenticationController;
import dal.AddressDBContext;
import dal.DepartmentDBContext;
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
import model.District;
import model.Employee;
import model.Job;
import model.EmployeeContact;
import model.Province;
import model.Ward;

/**
 *
 * @author PhuongNH
 */
public class EditEmployeeController extends BaseAuthenticationController {

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
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("eid"));

        EmployeeDBContext employeeDBContext = new EmployeeDBContext();
        DepartmentDBContext departmentDBContext = new DepartmentDBContext();
        AddressDBContext provinceDBContext = new AddressDBContext();
        JobDBContext jobDBContext = new JobDBContext();
        AddressDBContext addressDBContext = new AddressDBContext();

        Employee employee = employeeDBContext.getEmployeeById(id); // Get Employee By Id From DB
        ArrayList<Department> departments = departmentDBContext.getAllDepartments(); // Get All Departments From DB
        ArrayList<Province> provinces = provinceDBContext.getAllProvinces(); // Get All Provinces From DB
        ArrayList<Job> jobs = jobDBContext.getAllJobs(); // Get All Jobs From DB
        // Get all wards by district id From DB
        ArrayList<Ward> wards = addressDBContext.getAllWardsByDistrictID(employee.getContact().getWard().getDistrict().getDistrict_id());
        // get all districts by provinces id From DB
        ArrayList<District> districts = addressDBContext.getAllDistrictsByProvinceID(employee.getContact().getWard().getDistrict().getProvince().getProvince_id());

        request.setAttribute("departments", departments);
        request.setAttribute("provinces", provinces);
        request.setAttribute("jobs", jobs);
        request.setAttribute("employee", employee);
        request.setAttribute("wards", wards);
        request.setAttribute("districts", districts);
        request.getRequestDispatcher("../view/management/employee/edit-employee.jsp").forward(request, response);
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
    protected void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Set all information for object employee
        Employee employee = new Employee();
        employee.setE_id(Integer.parseInt(request.getParameter("eid")));
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
        employee.setE_gender(request.getParameter("gender").equals("male"));
        employee.setE_dob(Date.valueOf(request.getParameter("dob")));

        EmployeeContact contact = new EmployeeContact();
        contact.setStreet(request.getParameter("street"));
        contact.setPhone(request.getParameter("phone"));
        Ward ward = new Ward();
        ward.setWard_id(request.getParameter("ward_id"));
        contact.setWard(ward);
        employee.setContact(contact);

        EmployeeDBContext employeeDBContext = new EmployeeDBContext();
        // Update employee by id has id duplicate with employee param into DB
        employeeDBContext.updateEmployeeById(employee);
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
