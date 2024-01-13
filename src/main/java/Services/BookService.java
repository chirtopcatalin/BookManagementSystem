package Services;

import DTO.BookDTO;
import Entities.Book;
import Repositories.BookRepository;
import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.PermitAll;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@DeclareRoles({"administrator", "manager", "user"})
public class BookService {
    @Inject
    private BookRepository bookRepository;
    @PermitAll
    public List<BookDTO> getAllBooks() {
        return bookRepository.getAllBooks()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    @PermitAll
    public BookDTO getBookById(int bookId) {
        Book book = bookRepository.getProductById(bookId);
        return convertToDTO(book);
    }

    public void addBook(BookDTO bookDTO) {
        Book book = convertToEntity(bookDTO);
        bookRepository.addProduct(book);
    }


    public void updateBook(BookDTO bookDTO) {
        Book book = convertToEntity(bookDTO);
        bookRepository.updateBook(book);
    }

    @PermitAll
    public List<BookDTO> searchKeyword(String keyword) {
        List<Book> books = bookRepository.searchKeyword(keyword);
        return convertToDTO(books);
    }

    public void deleteBook(BookDTO bookDTO) {
        Book bookEntity = convertToEntity(bookDTO);
        bookRepository.deleteBook(bookEntity);
    }

    @PermitAll
    private BookDTO convertToDTO(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setCopiesAvailable(book.getCopiesAvailable());
        return bookDTO;
    }

    @PermitAll
    private List<BookDTO> convertToDTO(List<Book> books) {
        List<BookDTO> bookDTOs = new ArrayList<>();
        for (Book book : books) {
            bookDTOs.add(convertToDTO(book));
        }
        return bookDTOs;
    }

    @PermitAll
    private Book convertToEntity(BookDTO bookDTO) {
        Book book = new Book();
        book.setId(bookDTO.getId());
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setCopiesAvailable(bookDTO.getCopiesAvailable());
        return book;
    }

    @PermitAll
    private List<Book> convertToEntity(List<BookDTO> bookDTOs) {
        List<Book> books = new ArrayList<>();
        for (BookDTO bookDTO : bookDTOs) {
            books.add(convertToEntity(bookDTO));
        }
        return books;
    }
}
