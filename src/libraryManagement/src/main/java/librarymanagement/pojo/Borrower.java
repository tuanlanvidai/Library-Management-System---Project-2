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
public class Borrower {
    private int readerId;
    private String readerName;
    private Date registerDay;
    private int totalBooksBorrowed;

    public Borrower(int readerId, String readerName, Date registerDay, int totalBooksBorrowed) {
        this.readerId = readerId;
        this.readerName = readerName;
        this.registerDay = registerDay;
        this.totalBooksBorrowed = totalBooksBorrowed;
    }

    // Getters and Setters
    public int getReaderId() {
        return readerId;
    }

    public void setReaderId(int readerId) {
        this.readerId = readerId;
    }

    public String getReaderName() {
        return readerName;
    }

    public void setReaderName(String readerName) {
        this.readerName = readerName;
    }

    public Date getRegisterDay() {
        return registerDay;
    }

    public void setRegisterDay(Date registerDay) {
        this.registerDay = registerDay;
    }

    public int getTotalBooksBorrowed() {
        return totalBooksBorrowed;
    }

    public void setTotalBooksBorrowed(int totalBooksBorrowed) {
        this.totalBooksBorrowed = totalBooksBorrowed;
    }
}
