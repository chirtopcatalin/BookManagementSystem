package Entities;


import jakarta.persistence.*;



@Table(name = "borrow")
@Entity
public class Borrow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "borrow_id")
    private int borrowId;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "book_id")
    private int bookId;


    public Borrow() {
        // JPA
    }

    public Borrow(int userId, int bookId) {
        this.userId = userId;
        this.bookId = bookId;
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


}