package Services;


import DTO.BorrowDTO;
import Entities.Borrow;
import Repositories.AccountRepository;
import Repositories.BookRepository;
import Repositories.BorrowRepository;
import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.PermitAll;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@DeclareRoles({"administrator", "manager", "user"})
public class BorrowService {
    @Inject
    private BorrowRepository borrowRepository;
    @Inject
    private AccountRepository userRepository;
    @Inject
    private BookRepository bookRepository;

     @PermitAll
     public List<BorrowDTO> getAllBorrows() {

        // List<Borrow> borrows = borrowRepository.getAllBorrows();
        // return borrows.stream().map(borrow -> {

            // int userId = borrow.getUserId();
            // int bookId = borrow.getBookId();

            // String userName = userRepository.findUserNameById(userId);
            // String bookTitle = bookRepository.findBookTitleById(bookId);
            // return new BorrowDTO(borrow.getBorrowId(), userId, bookId, userName, bookTitle);}).collect(Collectors.toList());
       return borrowRepository.getAllBorrows()
          .stream()
        .map(this::convertToDTO)
       .collect(Collectors.toList());
}

@PermitAll
public BorrowDTO getBorrowById(int borrowId) {
    Borrow borrow = borrowRepository.getBorrowById(borrowId);
    return convertToDTO(borrow);
}



public void deleteBorrow(BorrowDTO borrowDTO) {
    Borrow borrow = convertToEntity(borrowDTO);
    borrowRepository.deleteBorrow(borrow);
}

private BorrowDTO convertToDTO(Borrow borrow) {
    BorrowDTO borrowDTO = new BorrowDTO();

    borrowDTO.setBorrowId(borrow.getBorrowId());
    borrowDTO.setUserId(borrow.getUserId());
    borrowDTO.setBookId(borrow.getBookId());
    return borrowDTO;
}

    public void borrowBook(int userId, int bookId) {
        Borrow borrow = new Borrow();
        borrow.setUserId(userId);
        borrow.setBookId(bookId);

        borrowRepository.createBorrow(borrow);
    }

private Borrow convertToEntity(BorrowDTO borrowDTO) {
    Borrow borrow = new Borrow();
    borrow.setBorrowId(borrowDTO.getBorrowId());
    borrow.setUserId(borrowDTO.getUserId());
    borrow.setBookId(borrowDTO.getBookId());
    return borrow;
   }
}