package DTO;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;


public class BorrowDTO {

    private int borrowId;
    private int userId;
    private int bookId;

    private String userName; // Numele utilizatorului
    private String bookTitle; // Titlul cărții


    public BorrowDTO() {

    }

    public BorrowDTO(int userId, int bookId) {
        this.userId = userId;
        this.bookId = bookId;
    }

    public BorrowDTO(int borrowId, int userId, int bookId, String userName, String bookTitle) {
    }


    public int getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(int borrowId) {
        this.borrowId = borrowId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }
}