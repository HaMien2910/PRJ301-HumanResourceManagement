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
public class DepartmentDBContext extends DBContext{
    public ArrayList<Department> getAllDepartments(){
        ArrayList<Department> departments = new ArrayList<>();
        try {
            String sql = "SELECT [DepartmentID], [DepartmentName], [ManagerID] FROM Departments";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Department d = new Department();
                d.setDep_id(rs.getInt(1));
                d.setDep_name(rs.getString(2));
                Employee e = new Employee();
                e.setId(rs.getInt(3));
                d.setManager(e);
                departments.add(d);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DepartmentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return departments;
    }
}
