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
                    + "      ,b.[manager_id]\n" //7
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
                d.setDescription(rs.getString(7));
                Employee e = new Employee();
                e.setE_id(rs.getInt(7));
                e.setE_first_name(rs.getString(8));
                e.setE_last_name(rs.getString(9));
                d.setManager(e);
                departments.add(d);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DepartmentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return departments;
    }
}
