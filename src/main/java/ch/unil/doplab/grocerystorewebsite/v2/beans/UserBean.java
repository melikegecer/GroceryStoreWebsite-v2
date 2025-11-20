package ch.unil.doplab.grocerystorewebsite.v2.beans;

import ch.unil.doplab.grocerystorewebsite.v2.exceptions.AlreadyExistsException;
import ch.unil.doplab.grocerystorewebsite.v2.exceptions.DoesNotExistException;
import ch.unil.doplab.grocerystorewebsite.v2.models.User;
import ch.unil.doplab.grocerystorewebsite.v2.database.MockDatabase;
import ch.unil.doplab.grocerystorewebsite.v2.exceptions.InsufficientBalanceException;
import java.io.Serializable;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

/**
 * Software Architectures | DOPLab | UniL
 *
 * @author Melike Geçer
 */
@Named(value = "userBean")
@SessionScoped
public class UserBean implements Serializable {

    private String email = "";
    private String username = "";
    private String firstName = "";
    private String lastName = "";
    private String password = "";
    private double amount = 0.0;

    public void createAUser() {
        try {
            if (!emailExists() && !usernameExists()) {
                MockDatabase.getInstance().addAUser(new User(username, firstName, lastName, email, password));
            }
        } catch (AlreadyExistsException | DoesNotExistException ex) {
            System.out.println(ex.getMessage());
        }
        // empty values
        this.email = "";
        this.username = "";
        this.firstName = "";
        this.lastName = "";
        this.password = "";
    }

    public void increaseBalance() {
        LoginBean.getUserLoggedIn().increaseBalance(amount);
        // empty value
        this.amount = 0.0;
    }

    public void completeShopping() {
        try {
            LoginBean.getUserLoggedIn().completeShopping();
        } catch (InsufficientBalanceException ex) {
            System.out.println(ex.getMessage());
        }
    }

    protected static User findByUsername(String username) throws DoesNotExistException {
        for (User user : MockDatabase.getInstance().getUsers()) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        throw new DoesNotExistException("The user " + username + " does not exist.");
    }

    private boolean emailExists() throws AlreadyExistsException {
        for (User user : MockDatabase.getInstance().getUsers()) {
            if (user.getEmail().equals(email)) {
                throw new AlreadyExistsException("The email " + email + " already in use.");
            }
        }
        return false;
    }

    private boolean usernameExists() throws DoesNotExistException {
        for (User user : MockDatabase.getInstance().getUsers()) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public double getAmount() {
        return amount;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

}
