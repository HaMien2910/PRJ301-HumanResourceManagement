/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author PhuongNH
 */
public class Department {

    private int department_id;
    private String department_name;
    private Employee manager;
    private String department_phone;
    private String department_email;
    private Date department_starting_date;
    private String description;
    private ArrayList<Employee> employees = new ArrayList<>();

    public Date getDepartment_starting_date() {
        return department_starting_date;
    }

    public void setDepartment_starting_date(Date department_starting_date) {
        this.department_starting_date = department_starting_date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    

    public String getDepartment_phone() {
        return department_phone;
    }

    public void setDepartment_phone(String department_phone) {
        this.department_phone = department_phone;
    }

    public String getDepartment_email() {
        return department_email;
    }

    public void setDepartment_email(String department_email) {
        this.department_email = department_email;
    }
    
    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    
}
