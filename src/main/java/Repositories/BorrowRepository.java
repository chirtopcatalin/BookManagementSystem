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
        entityManager.persist(borrow);
    }
}