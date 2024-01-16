package Repositories;

import Entities.Book;
import jakarta.annotation.security.DeclareRoles;
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

    public void deleteBook(int bookId) {

        Book book = entityManager.find(Book.class, bookId);
        if (book != null) {

            entityManager.remove(book);
        }
    }

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
