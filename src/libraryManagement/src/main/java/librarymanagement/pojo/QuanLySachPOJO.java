package librarymanagement.pojo;

public class QuanLySachPOJO {
    private int bookId;
    private String title;
    private String author;
    private String category;
    private int publishYear;
    private int totalQuantity;
    private int availableQty;
    private boolean isDeleted;

    // Constructor sử dụng thông tin mà bạn có
    public QuanLySachPOJO(String title, String author, String category, int publishYear, int totalQuantity, int availableQty) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.publishYear = publishYear;
        this.totalQuantity = totalQuantity;
        this.availableQty = availableQty;
        this.isDeleted = false; // Mặc định là chưa bị xóa
    }

    // Constructor với đầy đủ tham số (nếu bạn cần sử dụng)
    public QuanLySachPOJO(int bookId, String title, String author, String category, int publishYear, int totalQuantity, int availableQty, boolean isDeleted) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.category = category;
        this.publishYear = publishYear;
        this.totalQuantity = totalQuantity;
        this.availableQty = availableQty;
        this.isDeleted = isDeleted;
    }

    // Getter và Setter cho các thuộc tính
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public int getAvailableQty() {
        return availableQty;
    }

    public void setAvailableQty(int availableQty) {
        this.availableQty = availableQty;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    // Override phương thức toString để hiển thị thông tin sách
    @Override
    public String toString() {
        return "QuanLySach{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", category='" + category + '\'' +
                ", publishYear=" + publishYear +
                ", totalQuantity=" + totalQuantity +
                ", availableQty=" + availableQty +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
