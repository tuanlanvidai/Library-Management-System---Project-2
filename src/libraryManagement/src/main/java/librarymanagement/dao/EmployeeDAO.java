/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package librarymanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import librarymanagement.pojo.Employee;

/**
 *
 * @author minhp
 */
public class EmployeeDAO {
    List<Employee> employeeList = new ArrayList<>();
     ConfigUtils util = new ConfigUtils();
     public Employee getEmployee(String email,String passWord){
         Employee employee = null;
         try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    util.dbConnect, util.username, util.password);
            String sql = "select * from employee where email = ? and password = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, passWord);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                employee = new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
         return employee;
     }
     public List<Employee> getAllEmployee(){
        employeeList = new ArrayList<>();
        Employee employee;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    util.dbConnect, util.username, util.password);
            String sql = "select * from employee";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                employee = new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
                employeeList.add(employee);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return employeeList;
     }
     public Boolean addEmployee(Employee employee){
         Boolean isSuccess = false;
         return isSuccess;
     }
}
