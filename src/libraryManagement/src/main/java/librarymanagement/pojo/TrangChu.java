/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package librarymanagement.pojo;

/**
 *
 * @author CuongVu
 */
public class TrangChu {
    private String date;
    private int totalBooks;

    public TrangChu(String date, int totalBooks) {
        this.date = date;
        this.totalBooks = totalBooks;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTotalBooks() {
        return totalBooks;
    }

    public void setTotalBooks(int totalBooks) {
        this.totalBooks = totalBooks;
    }

    @Override
    public String toString() {
        return "Date: " + date + ", Total Books: " + totalBooks;
    }
}
