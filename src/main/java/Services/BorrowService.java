package Services;


import DTO.BorrowDTO;
import Entities.Borrow;
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

     @PermitAll
     public List<BorrowDTO> getAllBorrows() {
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

public void addBorrow(BorrowDTO borrowDTO) {
    Borrow borrow = convertToEntity(borrowDTO);
    borrowRepository.addBorrow(borrow);
}

public void updateBorrow(BorrowDTO borrowDTO) {
    Borrow borrow = convertToEntity(borrowDTO);
    borrowRepository.updateBorrow(borrow);
}

public void deleteBorrow(BorrowDTO borrowDTO) {
    Borrow borrow = convertToEntity(borrowDTO);
    borrowRepository.deleteBorrow(borrow);
}

private BorrowDTO convertToDTO(Borrow borrow) {
    BorrowDTO borrowDTO = new BorrowDTO();
    // Presupunem că BorrowDTO conține userId, bookId și alte câmpuri necesare
    borrowDTO.setBorrowId(borrow.getBorrowId());
    borrowDTO.setUserId(borrow.getUserId());
    borrowDTO.setBookId(borrow.getBookId());
    return borrowDTO;
}

private Borrow convertToEntity(BorrowDTO borrowDTO) {
    Borrow borrow = new Borrow();
    borrow.setBorrowId(borrowDTO.getBorrowId());
    borrow.setUserId(borrowDTO.getUserId());
    borrow.setBookId(borrowDTO.getBookId());
    return borrow;
}
}