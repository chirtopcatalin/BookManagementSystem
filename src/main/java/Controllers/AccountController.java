package Controllers;
import DTO.AccountDTO;
import DTO.BookDTO;
import Services.AccountService;
import Services.BookService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.PermitAll;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class AccountController implements Serializable {

    @Inject
    private AccountService accountService;
    private List<AccountDTO> accounts = new ArrayList<>();
    private static final long serialVersionUID = 1L;
    private String username;
    private String password;
    private String email;
    public String login() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        try {
            request.login(username, password);
        } catch(ServletException ex) {
            context.addMessage(null, new FacesMessage("Login Failed with "+ex.getMessage()));
            return "error.xhtml";
        }
        AccountDTO account = accountService.loginUser(username, password);
        externalContext.getSessionMap().put("userid", account.getId());
        externalContext.getSessionMap().put("username", account.getUsername());
        externalContext.getSessionMap().put("type", account.getType());
        String accountType = account.getType();
        if ("user".equals(accountType)) {
            return "indexLoggedUser.xhtml";
        } else if ("manager".equals(accountType) || "administrator".equals(accountType)) {
            return "indexLoggedAdmin.xhtml";
        } else {
            return "index.xhtml";
        }
    }

    @PostConstruct
    public void init(){
        getAllUsers();
    }
    @PermitAll
    public void getAllUsers(){
        accounts = accountService.getAllUsers();
    }
    @PermitAll
    public String register() throws IOException {
        AccountDTO account = new AccountDTO(username,email,0,"user", password);
        accountService.addAccount(account);
        return "index.xhtml";
    }
    @PermitAll
    public void logout() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.invalidateSession();
        externalContext.redirect(externalContext.getRequestContextPath() + "/index.xhtml");
        //return "/index.xhtml";
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){
        return email;
    }

    public List<AccountDTO> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountDTO> accounts) {
        this.accounts = accounts;
    }
}
