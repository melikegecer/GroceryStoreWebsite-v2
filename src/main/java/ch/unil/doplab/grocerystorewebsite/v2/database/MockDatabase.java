package ch.unil.doplab.grocerystorewebsite.v2.database;

import ch.unil.doplab.grocerystorewebsite.v2.models.Food;
import ch.unil.doplab.grocerystorewebsite.v2.models.User;
import java.util.ArrayList;

/**
 * Software Architectures | DOPLab | UniL
 *
 * @author Melike Geçer
 */
public class MockDatabase {

    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Food> foods = new ArrayList<>();
    private static MockDatabase instance = null;

    private MockDatabase() {
        users.add(new User("lisa", "lisa", "simpson", "lisa@simpson.com", "1234"));
        users.add(new User("homer", "homer", "simpson", "homer@simpson.com", "1234"));
        users.add(new User("marge", "marge", "simpson", "marge@simpson.com", "1234"));
        users.add(new User("bart", "bart", "simpson", "bart@simpson.com", "1234"));

        foods.add(new Food("Pasta", 15.0, new ArrayList<String>() {
            {
                add("spaghetti");
                add("pesto sauce");
                add("parmigiano");
            }
        }));
        foods.add(new Food("Chicken Curry", 18.0, new ArrayList<String>() {
            {
                add("chicken");
                add("curry sauce");
            }
        }));
        foods.add(new Food("Pizza", 12.0, new ArrayList<String>() {
            {
                add("dough");
                add("tomato sauce");
                add("mozarella");
            }
        }));

    }

    public static MockDatabase getInstance() {
        if (instance == null) {
            instance = new MockDatabase();
        }
        return instance;
    }

    public void addAUser(User user) {
        users.add(user);
    }

    public void addFood(Food food) {
        foods.add(food);
    }

    public void removeAUser(User user) {
        users.remove(user);
    }

    public void removeFood(Food food) {

    }

    public ArrayList<Food> getFoods() {
        return foods;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

}
