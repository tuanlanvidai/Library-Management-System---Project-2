/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package librarymanagement.pojo;

/**
 *
 * @author CuongVu
 */
public class ReturnFine {
    private int returnFineId;
    private int returnId;
    private int lateDays;
    private String bookStatus;
    private int fineMoney;

    public int getReturnFineId() {
        return returnFineId;
    }

    public void setReturnFineId(int returnFineId) {
        this.returnFineId = returnFineId;
    }

    public int getReturnId() {
        return returnId;
    }

    public void setReturnId(int returnId) {
        this.returnId = returnId;
    }

    public int getLateDays() {
        return lateDays;
    }

    public void setLateDays(int lateDays) {
        this.lateDays = lateDays;
    }

    public String getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(String bookStatus) {
        this.bookStatus = bookStatus;
    }

    public int getFineMoney() {
        return fineMoney;
    }

    public void setFineMoney(int fineMoney) {
        this.fineMoney = fineMoney;
    }
}

