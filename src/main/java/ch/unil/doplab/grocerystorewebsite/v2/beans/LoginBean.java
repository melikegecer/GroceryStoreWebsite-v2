package ch.unil.doplab.grocerystorewebsite.v2.beans;

import ch.unil.doplab.grocerystorewebsite.v2.exceptions.DoesNotExistException;
import ch.unil.doplab.grocerystorewebsite.v2.models.User;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author admin
 */
@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    private static User currentUser;
    private String username = "";
    private String password = "";

    public String userLogsIn() {
        try {
            User user = UserBean.findByUsername(username);
            if (user != null && user.isPasswordCorrect(password)) {
                currentUser = user;
                return "/UserPage/UserMainPage.xhtml?faces-redirect=true";
            }
        } catch (DoesNotExistException ex) {
            System.out.println(ex.getMessage());
        }
        return "/MainPage/LoginPage.xhtml?faces-redirect=true";
    }

    public String userLogsout() {
        currentUser = null;
        return "/MainPage/MainPage.xhtml?faces-redirect=true";
    }

    public static User getUserLoggedIn() {
        return currentUser;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
