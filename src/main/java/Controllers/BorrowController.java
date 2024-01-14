package Controllers;



import DTO.BorrowDTO;
import Services.BorrowService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.List;

@Named
@RequestScoped
public class BorrowController {

    @Inject
    private BorrowService borrowService;

    private List<BorrowDTO> borrows;
    private BorrowDTO selectedBorrow;
    private String searchKeyword;

    private boolean isCreate;

    private boolean isDelete;

    private int borrowId;

    @PostConstruct
    public void init() {
        borrows = borrowService.getAllBorrows();
        selectedBorrow = new BorrowDTO();
    }

    public String addBorrow() {
        borrowService.addBorrow(selectedBorrow);
        borrows = borrowService.getAllBorrows();
        selectedBorrow = new BorrowDTO();
        return "/borrow_index.xhtml?faces-redirect=true";
    }

    public void updateBorrow() {
        borrowService.updateBorrow(selectedBorrow);
        borrows = borrowService.getAllBorrows();
        selectedBorrow = new BorrowDTO();
    }

    public void deleteBorrow() {
        borrowService.deleteBorrow(selectedBorrow);
        borrows = borrowService.getAllBorrows();
    }

    public void getBorrowById(String borrowIDparam) {
        int borrowID = Integer.parseInt(borrowIDparam);
        selectedBorrow = borrowService.getBorrowById(borrowID);
    }




    // Getteri și setteri
    public List<BorrowDTO> getBorrows() {
        return borrows;
    }

    public void setBorrows(List<BorrowDTO> borrows) {
        this.borrows = borrows;
    }

    public BorrowDTO getSelectedBorrow() {
        return selectedBorrow;
    }

    public void setSelectedBorrow(BorrowDTO selectedBorrow) {
        this.selectedBorrow = selectedBorrow;
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    public BorrowService getBorrowService() {
        return borrowService;
    }

    public void setBorrowService(BorrowService borrowService) {
        this.borrowService = borrowService;
    }
}
