package Controllers;

import DTO.BookDTO;
import Services.BookService;
import jakarta.annotation.ManagedBean;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.List;

@Named
@RequestScoped
public class BookController {
    @Inject
    private BookService bookService;

    private List<BookDTO> books;
    private BookDTO selectedBook;
    private String searchKeyword;

    private boolean isCreate;

    private boolean isDelete;

    private int bookId;

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

    public void updateBook() {
        bookService.updateBook(selectedBook);
        books = bookService.getAllBooks();
        selectedBook = null;
    }


    public void deleteBook() {
        bookService.deleteBook(selectedBook);
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
}
