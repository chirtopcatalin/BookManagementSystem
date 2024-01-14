package Repositories;


import Entities.Borrow;
import jakarta.annotation.security.DeclareRoles;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@DeclareRoles({"administrator", "manager", "user"})
public class BorrowRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Borrow> getAllBorrows() {
        return entityManager.createQuery("SELECT b FROM Borrow b", Borrow.class)
                .getResultList();
    }

    public Borrow getBorrowById(int borrowId) {
        return entityManager.find(Borrow.class, borrowId);
    }

    public void addBorrow(Borrow borrow) {
        entityManager.persist(borrow);
    }

    public void updateBorrow(Borrow borrow) {
        entityManager.merge(borrow);
    }



    public void deleteBorrow(Borrow borrow) {
        entityManager.remove(borrow);
    }
}