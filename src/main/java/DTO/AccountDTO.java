package DTO;

public class AccountDTO {
    private int id;
    private String username;
    private String password;
    private String email;
    private int borrowedBooks;
    private String type;

    public AccountDTO() {
    }

    public AccountDTO(String username, String email, int borrowedBooks, String type, String password){
        this.password = password;
        this.id = id;
        this.username = username;
        this.email = email;
        this.borrowedBooks = borrowedBooks;
        this.type = type;
    }

    public AccountDTO(String username, String password){
        this.username = username;
        this.password = password;
    }

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

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
