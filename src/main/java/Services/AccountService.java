package Services;

import DTO.AccountDTO;
import Entities.Account;
import Repositories.AccountRepository;
import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.PermitAll;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@DeclareRoles({"administrator", "manager", "user"})
public class AccountService {

    @Inject
    private AccountRepository accountRepository;

    @PermitAll
    public AccountDTO loginUser(String username, String password) {
        Account account = accountRepository.loginUser(username, password);
        return convertToDTO(account);
    }


    @PermitAll
    public void addAccount(AccountDTO accountDTO) {
        Account account = convertToEntity(accountDTO);
        accountRepository.addAccount(account);
    }

    @PermitAll
    private AccountDTO convertToDTO(Account account) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(account.getId());
        accountDTO.setUsername(account.getUsername());
        accountDTO.setEmail(account.getEmail());
        accountDTO.setPassword(account.getPassword());
        accountDTO.setBorrowedBooks(account.getBorrowedBooks());
        accountDTO.setType(account.getType());
        return accountDTO;
    }

    @PermitAll
    private List<AccountDTO> convertToDTO(List<Account> accounts) {
        List<AccountDTO> accountDTOs = new ArrayList<>();
        for (Account account : accounts) {
            accountDTOs.add(convertToDTO(account));
        }
        return accountDTOs;
    }

    @PermitAll
    private Account convertToEntity(AccountDTO accountDTO) {
        Account account = new Account();
        account.setId(accountDTO.getId());
        account.setUsername(accountDTO.getUsername());
        account.setEmail(accountDTO.getEmail());
        account.setPassword(accountDTO.getPassword());
        account.setBorrowedBooks(accountDTO.getBorrowedBooks());
        account.setType(accountDTO.getType());
        return account;
    }

    @PermitAll
    private List<Account> convertToEntity(List<AccountDTO> accountDTOs) {
        List<Account> accounts = new ArrayList<>();
        for (AccountDTO accountDTO : accountDTOs) {
            accounts.add(convertToEntity(accountDTO));
        }
        return accounts;
    }
}