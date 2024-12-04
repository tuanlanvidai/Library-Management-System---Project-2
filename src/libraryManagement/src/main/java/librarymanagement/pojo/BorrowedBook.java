/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package librarymanagement.pojo;

/**
 *
 * @author CuongVu
 */
public class BorrowedBook {
    private int readerId;
    private int bookId;
    private int borrowId;
    private String bookStatus;
    private int lateDays;
    private int fineMoney;

    public BorrowedBook(int readerId, int bookId, int borrowId, String bookStatus, int lateDays, int fineMoney) {
        this.readerId = readerId;
        this.bookId = bookId;
        this.borrowId = borrowId;
        this.bookStatus = bookStatus;
        this.lateDays = lateDays;
        this.fineMoney = fineMoney;
    }

    // Getters and Setters
    public int getReaderId() {
        return readerId;
    }

    public void setReaderId(int readerId) {
        this.readerId = readerId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(int borrowId) {
        this.borrowId = borrowId;
    }

    public String getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(String bookStatus) {
        this.bookStatus = bookStatus;
    }

    public int getLateDays() {
        return lateDays;
    }

    public void setLateDays(int lateDays) {
        this.lateDays = lateDays;
    }

    public int getFineMoney() {
        return fineMoney;
    }

    public void setFineMoney(int fineMoney) {
        this.fineMoney = fineMoney;
    }
}

