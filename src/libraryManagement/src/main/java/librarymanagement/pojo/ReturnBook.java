/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package librarymanagement.pojo;

import java.util.Date;

/**
 *
 * @author lantr
 */
public class ReturnBook {

    private int borrowId;        // Mã phiếu mượn
    private String readerName;   // Tên độc giả
    private String bookTitle;    // Tên sách
    private Date borrowDate;     // Ngày mượn
    private Date dueDate;        // Ngày phải trả
    private int fineAmount;      // Tiền phạt trả chậm

    // Constructor
    public ReturnBook(int borrowId, String readerName, String bookTitle, Date borrowDate, Date dueDate, int fineAmount) {
        this.borrowId = borrowId;
        this.readerName = readerName;
        this.bookTitle = bookTitle;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.fineAmount = fineAmount;
    }

    // Getters and Setters
    public int getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(int borrowId) {
        this.borrowId = borrowId;
    }

    public String getReaderName() {
        return readerName;
    }

    public void setReaderName(String readerName) {
        this.readerName = readerName;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public int getFineAmount() {
        return fineAmount;
    }

    public void setFineAmount(int fineAmount) {
        this.fineAmount = fineAmount;
    }

    @Override
    public String toString() {
        return "ReturnBook{"
                + "borrowId=" + borrowId
                + ", readerName='" + readerName + '\''
                + ", bookTitle='" + bookTitle + '\''
                + ", borrowDate=" + borrowDate
                + ", dueDate=" + dueDate
                + ", fineAmount=" + fineAmount
                + '}';
    }
}
