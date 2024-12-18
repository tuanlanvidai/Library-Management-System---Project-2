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
    private int lostBookFee;
    private int bookDamageFee;

    // Constructor
    public Setting(int maxBorrowDays, int lateFeePerDay, int bookDamageFee, int lostBookFee, int maxBooksBorrowed) {
        this.maxBorrowDays = maxBorrowDays;
        this.lateFeePerDay = lateFeePerDay;
        this.lostBookFee = lostBookFee;
        this.bookDamageFee = bookDamageFee;
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

    public int getLostBookFee() {
        return lostBookFee;
    }

    public void setLostBookFee(int lostBookFee) {
        this.lostBookFee = lostBookFee;
    }

    public int getBookDamageFee() {
        return bookDamageFee;
    }

    public void setBookDamageFee(int bookDamageFee) {
        this.bookDamageFee = bookDamageFee;
    }
}
