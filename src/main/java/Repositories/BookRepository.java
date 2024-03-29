package Repositories;

import DTO.BookDTO;
import Entities.Book;
import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.PermitAll;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

import java.util.List;
@Stateless
@DeclareRoles({"administrator", "manager", "user"})
public class BookRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Book> getAllBooks() {
        return entityManager.createQuery("SELECT p FROM Book p", Book.class)
                .getResultList();
    }

    public Book getProductById(int productId) {
        return entityManager.find(Book.class, productId);
    }

    public void addProduct(Book book) {
        entityManager.persist(book);
    }

    public void updateBook(Book book) {
        entityManager.merge(book);
    }

    @PermitAll
    public List<Book> searchKeyword(String keyword) {
        if (keyword != "" && keyword != null) {
            String jpql = "SELECT p FROM Book p WHERE p.title LIKE :keyword";
            return entityManager.createQuery(jpql, Book.class)
                    .setParameter("keyword", "%" + keyword + "%")
                    .getResultList();
        } else {
            return getAllBooks();
        }
    }

    @PermitAll
    public void deleteBook(int bookId) {

        Book book = entityManager.find(Book.class, bookId);
        if (book != null) {

            entityManager.remove(book);
        }
    }

    public List<Book> getBorrowedBooks(int userId) {
        return entityManager.createQuery("SELECT b FROM Book b WHERE b.id IN (SELECT b.bookId FROM Borrow b WHERE b.userId = :userId)", Book.class)
                .setParameter("userId", userId)
                .getResultList();
    }
    @PermitAll
    public String findBookTitleById(int bookId) {
        try {
            return entityManager.createQuery(
                            "SELECT b.title FROM Book b WHERE b.id = :bookId", String.class)
                    .setParameter("bookId", bookId)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null; // sau gestionează cum consideri necesar
        }
    }
}
