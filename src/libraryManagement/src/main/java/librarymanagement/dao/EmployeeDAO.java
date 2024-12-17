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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import librarymanagement.pojo.Employee;

/**
 *
 * @author minhp
 */

public class EmployeeDAO {

    List<Employee> employeeList = new ArrayList<>();
    ConfigUtils util = new ConfigUtils();

    public List<String> getRoleName() {
        List<String> result = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    util.dbConnect, util.username, util.password);
            String sql = "select roleName from role";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                result.add(rs.getString(1));
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
     public Boolean checkEmail(String email) {
        Boolean isSuccess = false;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    util.dbConnect, util.username, util.password);
            String sql = "select employeeId from employee where email = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                if (!rs.getString(1).isEmpty()) {
                    isSuccess = true;
                }
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return isSuccess;
    }

    public Employee getEmployee(String email, String passWord) {
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

    public List<Employee> getAllEmployee() {
        employeeList = new ArrayList<>();
        Employee employee;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    util.dbConnect, util.username, util.password);
            String sql = "select * from employee where isDeleted=0";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                employee = new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
                employee.setIsDelete(rs.getInt(7));
                employeeList.add(employee);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return employeeList;
    }

    public Boolean addEmployee(Employee employee) {
        Boolean isSuccess = false;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    util.dbConnect, util.username, util.password);
            String sql = "insert into employee(name,role,phoneNumber,email,password) values(?,?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, employee.getName());
            stmt.setString(2, employee.getRole());
            stmt.setString(3, employee.getPhoneNumber());
            stmt.setString(4, employee.getEmail());
            stmt.setString(5, employee.getPassword());
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                isSuccess = true;
            }

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return isSuccess;
    }

    //edit employee
    public Employee getEmployeeById(int id) {
        Employee employee = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    util.dbConnect, util.username, util.password);
            String sql = "select * from employee where employeeId = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
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

    public Boolean editEmployee(Employee employee) {
        Boolean isSuccess = false;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    util.dbConnect, util.username, util.password);
            String sql = " update employee set name=?,role=?,phoneNumber=?,email=?,password=? where employeeId = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, employee.getName());
            stmt.setString(2, employee.getRole());
            stmt.setString(3, employee.getPhoneNumber());
            stmt.setString(4, employee.getEmail());
            stmt.setString(5, employee.getPassword());
            stmt.setInt(6, employee.getId());
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                isSuccess = true;
            }

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return isSuccess;
    }
//delete employee

    public Boolean deleteEmployee(int id) {
        Boolean isSuccess = false;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    util.dbConnect, util.username, util.password);
            String sql = "update employee set isDeleted = 1 where employeeId = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            int row = stmt.executeUpdate();
            if (row > 0) {
                isSuccess = true;
            }

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return isSuccess;
    }

//search
    public List<Employee> SearchById(int id) {
        employeeList=new ArrayList<>();
        Employee employee;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    util.dbConnect, util.username, util.password);
            String sql = "select * from employee where employeeId like '%?%'";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                employee = new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
                employee.setIsDelete(rs.getInt(7));
                employeeList.add(employee);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return employeeList;
    }
    

//add data to table
    public void addDataFromDB(DefaultTableModel model, JTable jtable1) {
        employeeList = this.getAllEmployee();
        model = (DefaultTableModel) jtable1.getModel();
        model.setRowCount(0);
        Object columns[] = new Object[6];
        for (int i = 0; i < employeeList.size(); i++) {
                columns[0] = employeeList.get(i).getId();
                columns[1] = employeeList.get(i).getName();
                columns[2] = employeeList.get(i).getRole();
                columns[3] = employeeList.get(i).getPhoneNumber();
                columns[4] = employeeList.get(i).getEmail();
                columns[5] = employeeList.get(i).getPassword();
                model.addRow(columns);
        }
    }
    public void addDataFromDBSearch(List<Employee> employees,DefaultTableModel model, JTable jtable1) {
        model = (DefaultTableModel) jtable1.getModel();
        model.setRowCount(0);
        Object columns[] = new Object[6];
        for (int i = 0; i < employees.size(); i++) {
                columns[0] = employees.get(i).getId();
                columns[1] = employees.get(i).getName();
                columns[2] = employees.get(i).getRole();
                columns[3] = employees.get(i).getPhoneNumber();
                columns[4] = employees.get(i).getEmail();
                columns[5] = employees.get(i).getPassword();
                model.addRow(columns);
        }
    }
}
