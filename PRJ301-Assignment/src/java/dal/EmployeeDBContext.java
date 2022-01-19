/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Department;
import model.Employee;
import model.Job;
import model.Location;

/**
 *
 * @author PhuongNH
 */
public class EmployeeDBContext extends DBContext {

    public ArrayList<Employee> getAllEmployees() {
        ArrayList<Employee> employees = new ArrayList<>();
        try {
            String sql = "select \n"
                    + "	a.[EmployeeID], \n"      
                    + "	a.[FirstName], \n"
                    + "	a.[LastName], \n"
                    + "	a.[Gender], \n"
                    + "	a.[DOB] ,\n"
                    + "	a.[Phone], \n"
                    + "	a.[Email], \n"
                    + "	a.[HireDate], \n"
                    + "	a.salary, \n"
                    + "	b.[DepartmentID], \n"
                    + "	b.[DepartmentName],\n"
                    + "	b.[ManagerID], \n"
                    + "	c.[JobID], \n"
                    + "	c.[JobTitle], \n"
                    + "	c.min_salary, \n"
                    + "	c.max_salary, \n"
                    + "	d.LocationID, \n"
                    + "	d.Province_id, \n"
                    + "	d.District_id, \n"
                    + "	d.ward_id, \n"
                    + "	d.streetAddress,\n"
                    + "	a.[ManagerID]\n"
                    + "from Employees as a JOIN Departments as b ON a.[DepartmentID] = b.[DepartmentID]\n"
                    + "                    JOIN Jobs as c ON a.[JobID] = c.[JobID]\n"
                    + "			   JOIN Locations as d on a.[LocationId] = d.[LocationId]";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Employee e = new Employee();
                Department d = new Department();
                Job j = new Job();
                Location l = new Location();
                e.setE_id(rs.getInt(1));
                e.setE_first_name(rs.getString(2));
                e.setE_last_name(rs.getString(3));
                e.setE_gender(rs.getBoolean(4));
                e.setE_dob(rs.getDate(5));
                e.setE_phone(rs.getString(6));
                e.setE_email(rs.getString(7));
                e.setE_join_date(rs.getDate(8));
                e.setE_salary(rs.getDouble(9));
//                d.setDep_id(rs.getInt(10));
//                d.setDep_name(rs.getString(11));
                Employee manager = new Employee();
                manager.setE_id(rs.getInt(12));
                d.setManager(manager);
                e.setDepartment(d);
                j.setJob_id(rs.getInt(13));
                j.setJob_title(rs.getString(14));
                j.setMin_salary(rs.getDouble(15));
                j.setMax_salary(rs.getDouble(16));
                e.setJob(j);
//                l.setId(rs.getInt(17));
//                l.setProvince_id(rs.getString(18));
//                l.setDistrict_id(rs.getString(19));
                l.setWard_id(rs.getString(20));
                l.setStreet(rs.getString(21));
                e.setLocation(l);
                e.setManager(manager);
                employees.add(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return employees;
    }
}
