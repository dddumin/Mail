package model;

import java.util.Objects;

/**
 * This class represents a customer.
 */
public class Customer extends User {
    private String personalNumber;
    private MailList sentMail;
    private MailList incomingMail;

    /**
     * Constructs a new customer instance.
     *
     * @param firstName      the first name customer
     * @param lastName       the last name customer
     * @param userName       the username customer
     * @param password       the password customer
     * @param personalNumber the personal number customer
     */
    public Customer(String firstName, String lastName, String userName, String password, String personalNumber) {
        super(firstName, lastName, userName, password);
        this.personalNumber = personalNumber;
        this.sentMail = new MailList();
        this.incomingMail = new MailList();
    }

    /**
     * Getting a personal number
     *
     * @return string representation of personal number
     */
    public String getPersonalNumber() {
        return personalNumber;
    }

    /**
     * Send mail to user
     *
     * @param postalService - type mail
     * @param recipient     - recipient mail
     */
    public void sendMail(String postalService, User recipient) {
        Mail mail = new Mail(postalService);
        this.addSentMail(mail);
        ((Customer) recipient).addIncomingMail(mail);
    }

    /**
     * Adds an incoming mail
     *
     * @param mail - incoming mail
     */
    private void addIncomingMail(Mail mail) {
        this.incomingMail.addMail(mail);
    }

    /**
     * Adds an sent mail
     *
     * @param mail - sent mail
     */
    private void addSentMail(Mail mail) {
        this.sentMail.addMail(mail);
    }

    /**
     * Issue mail
     *
     * @return {@code true} if successful, {@code false} if there are no incoming letters
     */
    public boolean getIncomingMail() {
        return this.incomingMail.clear();
    }

    /**
     * Checks if there is any incoming mail
     *
     * @return @return {@code true} if there is an incoming mail {@code false} otherwise
     */
    public boolean haveIncomingMail() {
        return this.incomingMail.size() != 0;
    }

    /**
     * Checks if there is any sent mail
     *
     * @return @return {@code true} if there is an sent mail {@code false} otherwise
     */
    public boolean haveSentMail() {
        return this.sentMail.size() != 0;
    }

    /**
     * List incoming mail
     */
    public void getListMail() {
        this.incomingMail.printListMail(this.incomingMail.getMapMail());
    }

    /**
     * List sent mail
     */
    public void getListPrice() {
        this.sentMail.printListPrice(this.sentMail.getMapMail());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(personalNumber, customer.personalNumber)
                && Objects.equals(sentMail, customer.sentMail)
                && Objects.equals(incomingMail, customer.incomingMail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), personalNumber, sentMail, incomingMail);
    }

    @Override
    public String toString() {
        return "Customer{"
                + "personalNumber='" + personalNumber + '\''
                + ", sentMail=" + sentMail
                + ", incomingMail=" + incomingMail
                + '}';
    }
}
