/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package librarymanagement.pojo;

/**
 *
 * @author minhp
 */
public class Employee {

    public Employee(int id, String name, String role, String phoneNumber, String email, String password) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.isDelete= false;
    }
    public Employee( String name, String role, String phoneNumber, String email, String password) {
        this.name = name;
        this.role = role;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.isDelete= false;
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", name=" + name + ", role=" + role + ", phoneNumber=" + phoneNumber + ", email=" + email + ", password=" + password + '}';
    }
    

    
    private int id;
    private String name;
    private String role;
    private String phoneNumber;
    private String email;
    private String password;
    private Boolean isDelete;
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * @return the phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber the phoneNumber to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the isDelete
     */
    public Boolean getIsDelete() {
        return isDelete;
    }

    /**
     * @param isDelete the isDelete to set
     */
    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }
    
}
