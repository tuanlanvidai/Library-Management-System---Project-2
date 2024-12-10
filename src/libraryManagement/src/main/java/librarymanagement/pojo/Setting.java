/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package librarymanagement.pojo;

/**
 *
 * @author quoct
 */
public class Setting {
    private int maxBorrowDays;
    private int lateFeePerDay;
    private int maxBooksBorrowed;

    // Constructor
    public Setting(int maxBorrowDays, int lateFeePerDay, int maxBooksBorrowed) {
        this.maxBorrowDays = maxBorrowDays;
        this.lateFeePerDay = lateFeePerDay;
        this.maxBooksBorrowed = maxBooksBorrowed;
    }

    // Getters and Setters
    public int getMaxBorrowDays() {
        return maxBorrowDays;
    }

    public void setMaxBorrowDays(int maxBorrowDays) {
        this.maxBorrowDays = maxBorrowDays;
    }

    public int getLateFeePerDay() {
        return lateFeePerDay;
    }

    public void setLateFeePerDay(int lateFeePerDay) {
        this.lateFeePerDay = lateFeePerDay;
    }

    public int getMaxBooksBorrowed() {
        return maxBooksBorrowed;
    }

    public void setMaxBooksBorrowed(int maxBooksBorrowed) {
        this.maxBooksBorrowed = maxBooksBorrowed;
    }
}
