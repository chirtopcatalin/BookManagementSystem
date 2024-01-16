package Repositories;

import Entities.Account;
import Entities.Book;
import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.PermitAll;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.List;
@DeclareRoles({"administrator", "manager", "user"})
public class AccountRepository {

    @PersistenceContext
    private EntityManager entityManager;
    @PermitAll
    public Account loginUser(String username, String password) {
        if (password == null) {
            return new Account();
        }
        String hashedPassword = DigestUtils.sha256Hex(password);
        String jpql = "SELECT a FROM Account a WHERE a.username = :username AND a.password = :password";
        List<Account> accounts = entityManager.createQuery(jpql, Account.class)
                .setParameter("username", username)
                .setParameter("password", hashedPassword)
                .getResultList();

        if (accounts.size() > 0) {
            return accounts.get(0);
        } else {
            return new Account();
        }
    }
    @PermitAll
        public void addAccount(Account account) {
        account.setPassword(DigestUtils.sha256Hex(account.getPassword()));
        entityManager.persist(account);
        String sql = "INSERT INTO grup (username, groupname) VALUES ('"+account.getUsername()+"', 'group3')";
        entityManager.createNativeQuery(sql).executeUpdate();
        }
    @PermitAll
    public String findUserNameById(int userId) {
        try {
            return entityManager.createQuery(
                            "SELECT u.username FROM Account u WHERE u.id = :userId", String.class)
                    .setParameter("userId", userId)
                     .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    @PermitAll
    public List<Account> getAllUsers(){
        List<Account> accounts = entityManager.createQuery("SELECT a FROM Account a", Account.class).getResultList();
        return accounts;
    }
    }
