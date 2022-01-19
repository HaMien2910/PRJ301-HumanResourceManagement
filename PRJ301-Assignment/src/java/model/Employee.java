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
public class Employee {

    private int e_id;
    private String e_first_name;
    private String e_last_name;
    private boolean e_gender;
    private Date e_dob;
    private String e_email;
    private String e_phone;
    private Date e_join_date;
    private double e_salary;
    private Department department;
    private Employee manager;
    private ArrayList<Employee> employees = new ArrayList<>();
    private Job job;
    private Location location;

    public int getE_id() {
        return e_id;
    }

    public void setE_id(int e_id) {
        this.e_id = e_id;
    }

    public String getE_first_name() {
        return e_first_name;
    }

    public void setE_first_name(String e_first_name) {
        this.e_first_name = e_first_name;
    }

    public String getE_last_name() {
        return e_last_name;
    }

    public void setE_last_name(String e_last_name) {
        this.e_last_name = e_last_name;
    }

    public boolean isE_gender() {
        return e_gender;
    }

    public void setE_gender(boolean e_gender) {
        this.e_gender = e_gender;
    }

    public Date getE_dob() {
        return e_dob;
    }

    public void setE_dob(Date e_dob) {
        this.e_dob = e_dob;
    }

    public String getE_email() {
        return e_email;
    }

    public void setE_email(String e_email) {
        this.e_email = e_email;
    }

    public String getE_phone() {
        return e_phone;
    }

    public void setE_phone(String e_phone) {
        this.e_phone = e_phone;
    }

    public Date getE_join_date() {
        return e_join_date;
    }

    public void setE_join_date(Date e_join_date) {
        this.e_join_date = e_join_date;
    }

    public double getE_salary() {
        return e_salary;
    }

    public void setE_salary(double e_salary) {
        this.e_salary = e_salary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
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

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
