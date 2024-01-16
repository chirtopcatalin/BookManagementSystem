package Repositories;

import Entities.Borrow;
import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.PermitAll;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
@DeclareRoles({"administrator", "manager", "user"})
public class BorrowRepository {

    @PersistenceContext
    private EntityManager entityManager;


    @PermitAll
    public List<Borrow> getAllBorrows() {
        return entityManager.createQuery("SELECT b FROM Borrow b", Borrow.class)
                .getResultList();
    }

    @PermitAll
    public Borrow getBorrowById(int borrowId) {
        return entityManager.find(Borrow.class, borrowId);
    }

    @PermitAll
    public void addBorrow(Borrow borrow) {
        entityManager.persist(borrow);
    }

    @PermitAll
    public void updateBorrow(Borrow borrow) {
        entityManager.merge(borrow);
    }


    @PermitAll
    public void deleteBorrow(Borrow borrowId) {
        Borrow borrow = entityManager.find(Borrow.class, borrowId);
        if (borrow != null) {
            entityManager.remove(borrow);
        }
    }
    @PermitAll
    public void createBorrow(Borrow borrow) {
        //check if user already borrowed the book
        if (entityManager.createQuery("SELECT b FROM Borrow b WHERE b.userId = :userId AND b.bookId = :bookId", Borrow.class)
                .setParameter("userId", borrow.getUserId())
                .setParameter("bookId", borrow.getBookId())
                .getResultList().size() > 0) {
            return;
        }

        //check if book is available
        if (entityManager.createQuery("SELECT b FROM Book b WHERE b.id = :bookId AND b.copiesAvailable > 0", Borrow.class)
                .setParameter("bookId", borrow.getBookId())
                .getResultList().size() > 0) {
            entityManager.createQuery("UPDATE Book b SET b.copiesAvailable = b.copiesAvailable - 1 WHERE b.id = :bookId")
                    .setParameter("bookId", borrow.getBookId())
                    .executeUpdate();
            entityManager.persist(borrow);
        }
    }
}