package Controllers;



import DTO.BookDTO;
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


    @PostConstruct
    public void init() {
        borrows = borrowService.getAllBorrows();
    }

    public List<BorrowDTO> getBorrows() {
        return borrows;
    }

    public String navigateToBorrowsPage() {
        return "borrowsList.xhtml";
    }

    public String borrowBook(int bookId) {
        int userID = (int) jakarta.faces.context.FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userid");
        BorrowDTO borrow = new BorrowDTO();
        borrow.setBookId(bookId);
        borrow.setUserId(userID);
        borrowService.borrowBook(borrow);
        return "indexLoggedUser.xhtml?faces-redirect=true";
    }

}

