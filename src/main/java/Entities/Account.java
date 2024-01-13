package Entities;

import jakarta.persistence.*;

@Table(name = "account")
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "borrowed_books")
    private int borrowedBooks;

    @Column(name = "type")
    private String type;

    public Account() {
    }

    public Account(String username, String email, String password, int borrowedBooks, String type) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.borrowedBooks = borrowedBooks;
        this.type = type;
    }

    public Account(String username, String password, String type){
        this.username = username;
        this.password = password;
        this.type = type;
    }

//    public Account(String email, String username, String password,int borrowedBooks, String type){
//        this.email = email;
//        this.username = username;
//        this.password = password;
//        this.borrowedBooks = borrowedBooks;
//        this.type = type;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(int borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}