package model;

import io.Terminal;

import java.util.ArrayList;

/**
 * This class represents a user list.
 */
public class UserList {
    private ArrayList<User> users;

    /**
     * Constructs a new user list instance.
     */
    public UserList() {
        this.users = new ArrayList<>();
    }

    /**
     * Add new user in list
     *
     * @param user new user
     */
    public void addUser(User user) {
        this.users.add(user);
    }

    public boolean isPersonalNumber(String personalNumber) {
        for (User user : this.users) {
            if (user.getClass().equals(Customer.class)) {
                Customer customer = (Customer) user;
                if (customer.getPersonalNumber().equals(personalNumber))
                    return true;
            }
        }
        return false;
    }

    /**
     * Search for a user in the list
     *
     * @param userName user Name
     * @return user object if a match is found, otherwise null
     */
    public User getUserByName(String userName) {
        for (User user : this.users) {
            if (user.getUserName().equals(userName))
                return user;
        }
        return null;
    }


    /**
     * Checks if the username and password match
     *
     * @param userName user Name
     * @param password user password
     * @return user object if a match is found, otherwise null
     */
    public User checkLogPass(String userName, String password) {
        User user = this.getUserByName(userName);
        if (user == null) {
            Terminal.printError("User named " + userName + " is not registered in the system!!!");
            return null;
        } else if (!user.getPassword().equals(password)) {
            Terminal.printError("Invalid Password!!!");
            return null;
        }
        return user;
    }

    @Override
    public String toString() {
        return "UserList{"
                + "users=" + users
                + '}';
    }
}
