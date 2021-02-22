package model;

import java.util.Objects;

/**
 * This class represents a user.
 */
public class User {
    private final String firstName;
    private final String lastName;
    private final String userName;
    private String password;

    /**
     * Constructs a new user instance.
     *
     * @param firstName first name user
     * @param lastName  last name user
     * @param userName  username user
     * @param password  password user
     */
    public User(String firstName, String lastName, String userName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
    }

    /**
     * Get username
     *
     * @return a string representation of the user's username
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Get user password
     *
     * @return a string representation of the user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set user password
     *
     * @param password new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /*public boolean isCustomer() {
        if (this.getClass().equals(Customer.class))
            return true;
        return false;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(firstName, user.firstName)
                && Objects.equals(lastName, user.lastName)
                && Objects.equals(userName, user.userName)
                && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, userName, password);
    }

    @Override
    public String toString() {
        return "User{"
                + "firstName='" + firstName + '\''
                + ", lastName='" + lastName + '\''
                + ", userName='" + userName + '\''
                + ", password='" + password + '\''
                + '}';
    }
}
