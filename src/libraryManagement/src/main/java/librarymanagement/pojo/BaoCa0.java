/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package librarymanagement.pojo;

/**
 *
 * @author CuongVu
 */
public class BaoCa0 {
    private String name;    
    private String bookName;  
    private String status;    
    private int exDates;    
    private int values;     

    public BaoCa0(String name, String bookName, String status, int exDates, int values) {
        this.name = name;
        this.bookName = bookName;
        this.status = status;
        this.exDates = exDates;
        this.values = values;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getExDates() {
        return exDates;
    }

    public void setExDates(int exDates) {
        this.exDates = exDates;
    }

    public int getValues() {
        return values;
    }

    public void setValues(int values) {
        this.values = values;
    }
}

