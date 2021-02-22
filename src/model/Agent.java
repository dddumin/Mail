package model;

/**
 * This class represents a agent.
 */
public class Agent extends User {
    /**
     * Constructs a new agent instance.
     *
     * @param firstName the first name customer
     * @param lastName the last name customer
     * @param userName the username customer
     * @param password the password customer
     */
    public Agent(String firstName, String lastName, String userName, String password) {
        super(firstName, lastName, userName, password);
    }
}
