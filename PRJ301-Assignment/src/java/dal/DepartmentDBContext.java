/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Department;
import model.Employee;

/**
 *
 * @author PhuongNH
 */
public class DepartmentDBContext extends DBContext {

    public ArrayList<Department> getAllDepartments() {
        ArrayList<Department> departments = new ArrayList<>();
        try {
            String sql = "SELECT a.[department_id]\n" //1
                    + "      ,a.[dapartment_name]\n" //2
                    + "      ,a.[department_phone]\n" //3
                    + "      ,a.[department_email]\n" //4
                    + "      ,a.[starting_date]\n" //5
                    + "      ,a.[description]\n" //6
                    + "      ,a.[manager_id]\n" //7
                    + "      ,b.[e_first_name]\n" //8
                    + "      ,b.[e_last_name]\n" //9
                    + "  FROM [Departments] AS a\n"
                    + "			LEFT JOIN \n"
                    + "	   [Employees] AS b ON a.manager_id = b.e_id";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            //Loop to add all information in list             
            while (rs.next()) {
                Department d = new Department();
                d.setDepartment_id(rs.getInt(1));
                d.setDepartment_name(rs.getString(2));
                d.setDepartment_phone(rs.getString(3));
                d.setDepartment_email(rs.getString(4));
                d.setDepartment_starting_date(rs.getDate(5));
                d.setDescription(rs.getString(6));
                Employee e = new Employee();
                e.setE_id(rs.getInt(7));
                e.setE_first_name(rs.getString(8));
                e.setE_last_name(rs.getString(9));
                d.setManager(e);

                EmployeeDBContext employeeDBContext = new EmployeeDBContext();
                //Get employee by department id
                ArrayList<Employee> employees = employeeDBContext.getAllEmployeesByDepartmentId(d.getDepartment_id());
                d.setEmployees(employees);
                departments.add(d);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DepartmentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return departments;
    }

    public void addDepartment(Department department) {
        try {
            String sql = "INSERT INTO [Departments]\n"
                    + "           ([dapartment_name]\n"
                    + "           ,[department_phone]\n"
                    + "           ,[department_email]\n"
                    + "           ,[starting_date]\n"
                    + "           ,[description]\n"
                    + "           ,[manager_id])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, department.getDepartment_name());
            stm.setString(2, department.getDepartment_phone());
            stm.setString(3, department.getDepartment_email());
            java.sql.Date currentDate = new java.sql.Date(new java.util.Date().getTime());
            stm.setDate(4, currentDate);
            stm.setString(5, department.getDescription());
            if (department.getManager().getE_id() >= 0) {
                stm.setInt(6, department.getManager().getE_id());
            } else {
                stm.setNull(6, Types.INTEGER);
            }
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DepartmentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Department getDepartmentById(int did) {
        Department d = new Department();
        try {
            String sql = "SELECT a.[department_id]\n" //1
                    + "      ,a.[dapartment_name]\n" //2
                    + "      ,a.[department_phone]\n" //3
                    + "      ,a.[department_email]\n" //4
                    + "      ,a.[starting_date]\n" //5
                    + "      ,a.[description]\n" //6
                    + "      ,a.[manager_id]\n" //7
                    + "      ,b.[e_first_name]\n" //8
                    + "      ,b.[e_last_name]\n" //9
                    + "  FROM [Departments] AS a\n"
                    + "			LEFT JOIN \n"
                    + "	   [Employees] AS b ON a.manager_id = b.e_id\n"
                    + "  WHERE a.[department_id] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, did);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                d.setDepartment_id(rs.getInt(1));
                d.setDepartment_name(rs.getString(2));
                d.setDepartment_phone(rs.getString(3));
                d.setDepartment_email(rs.getString(4));
                d.setDepartment_starting_date(rs.getDate(5));
                d.setDescription(rs.getString(6));
                Employee e = new Employee();
                e.setE_id(rs.getInt(7));
                e.setE_first_name(rs.getString(8));
                e.setE_last_name(rs.getString(9));

                EmployeeDBContext employeeDBContext = new EmployeeDBContext();
                //Get employee by department id
                ArrayList<Employee> employees = employeeDBContext.getAllEmployeesByDepartmentId(d.getDepartment_id());
                d.setEmployees(employees);
                d.setManager(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DepartmentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return d;
    }

    public void updateDepartmentById(Department department) {
        try {
            String sql = "UPDATE [Departments]\n"
                    + "   SET [dapartment_name] = ?\n"
                    + "      ,[manager_id] = ?\n"
                    + "      ,[department_phone] = ?\n"
                    + "      ,[department_email] = ?\n"
                    + "      ,[description] = ?\n"
                    + " WHERE [department_id] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, department.getDepartment_name());
            if (department.getManager().getE_id() >= 0) {
                stm.setInt(2, department.getManager().getE_id());
            } else {
                stm.setNull(2, Types.INTEGER);
            }
            stm.setString(3, department.getDepartment_phone());
            stm.setString(4, department.getDepartment_email());
            stm.setString(5, department.getDescription());
            stm.setInt(6, department.getDepartment_id());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DepartmentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteDepartmentById(int did) {
        try {
            String sql = "DELETE FROM [Departments]\n"
                    + "      WHERE [department_id] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, did);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DepartmentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
