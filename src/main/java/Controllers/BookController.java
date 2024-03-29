package Controllers;

import DTO.BookDTO;
import Services.BookService;
import jakarta.annotation.ManagedBean;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.security.DeclareRoles;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.List;

@Named
@RequestScoped
@DeclareRoles({"administrator", "manager", "user"})
public class BookController {
    @Inject
    private BookService bookService;

    private List<BookDTO> books;
    private BookDTO selectedBook;
    private String searchKeyword;

    private boolean isCreate;

    private boolean isDelete;

    private int bookId;
    private List<BookDTO> borrowedBooks;

    @PostConstruct
    public void init() {
        books = bookService.getAllBooks();
        selectedBook = new BookDTO();
    }

    public String addBook() {
        bookService.addBook(selectedBook);
        books = bookService.getAllBooks();
        selectedBook = null;
        return "/index.xhtml?faces-redirect=true";
    }

    public String updateBook() {
        bookService.updateBook(selectedBook);
        books = bookService.getAllBooks();
        selectedBook = null;

        return "/index.xhtml";
    }
    public String selectBookEdit(String bookIDparam) {
        int bookID = Integer.parseInt(bookIDparam);
        selectedBook = bookService.getBookById(bookID);
        return "edit.xhtml";
    }


    public void deleteBook(int bookId) {
        bookService.deleteBook(bookId);
        books = bookService.getAllBooks();
    }

    public void getBookById(String bookIDparam) {
        int bookID = Integer.parseInt(bookIDparam);
        selectedBook = bookService.getBookById(bookID);
    }

    public String startCreate() {
        bookId = 0;
        // isEdit = false;
        isCreate = true;
        selectedBook = new BookDTO();
        return "createBook.xhtml";
    }

    public String getUserBorrowedBooks() {
        int userID = (int) jakarta.faces.context.FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userid");
        borrowedBooks = bookService.getBorrowedBooks(userID);
        return "myBorrowedBooks.xhtml";
    }

    public void searchBooks() {
        books = bookService.searchKeyword(searchKeyword);
    }

    public List<BookDTO> getBooks() {
        return books;
    }

    public void setBooks(List<BookDTO> books) {
        this.books = books;
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    public BookService getBookService() {
        return bookService;
    }

    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    public BookDTO getSelectedBook() {
        return selectedBook;
    }

    public void setSelectedBook(BookDTO selectedBook) {
        this.selectedBook = selectedBook;
    }

    public void setBorrowedBooks(List<BookDTO> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    public List<BookDTO> getBorrowedBooks() {
        return borrowedBooks;
    }
}
