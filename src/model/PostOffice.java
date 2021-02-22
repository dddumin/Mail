package model;

import io.Terminal;
import io.Validator;

import java.util.HashMap;

/**
 * This class represents a Post Office.
 */
public class PostOffice {
    /**
     * A dictionary that contains mail available for sending and prices
     */
    public static HashMap<String, Double> price;
    private User user;
    private final UserList users;

    /**
     * Constructs a new post office instance.
     */
    public PostOffice() {
        this.users = new UserList();
        price = new HashMap<>();
        //names??????
        price.put("Letter", 0.70);
        price.put("Registered", 1.20);
        price.put("Parcel", 2.00);
        price.put("PaketS", 5.00);
        price.put("PaketM", 6.00);
        price.put("PaketL", 7.00);
    }

    /**
     * Adding a new customer to the system
     *
     * @param command string representation of the command
     */
    private void addCustomer(Command command) {
        if (this.user != null) {
            Terminal.printError("There must be no authorized users to execute this command!!!");
            return;
        }
        if (!Validator.validateAddCustomer(command)) {
            return;
        }
        String[] data = command.getData();
        if (!this.users.isPersonalNumber(data[4]) && this.users.getUserByName(data[2]) == null) {
            User customer = new Customer(data[0], data[1], data[2], data[3], data[4]);
            this.users.addUser(customer);
            Terminal.printLine("OK");
        } else
            Terminal.printError("A customer with the specified personal number or username is already registered!!!");
    }

    /**
     * Adding a new employee to the system
     *
     * @param command string representation of the command
     */
    private void addEmployee(Command command) {
        if (this.user != null) {
            Terminal.printError("There must be no authorized users to execute this command!!!");
            return;
        }
        if (!Validator.validateAddEmployee(command))
            return;
        String[] data = command.getData();
        if (this.users.getUserByName(data[2]) == null) {
            User employee = null;
            if (command.getCommand().equals("add-mailman")) {
                employee = new MailMan(data[0], data[1], data[2], data[3]);
            } else if (command.getCommand().equals("add-agent")) {
                employee = new Agent(data[0], data[1], data[2], data[3]);
            }
            this.users.addUser(employee);
            Terminal.printLine("OK");
        } else
            Terminal.printError("A user with the specified personal number is already registered!!!");
    }

    /**
     * User authorization
     *
     * @param command string representation of the command
     */
    private void authenticate(Command command) {
        if (this.user != null) {
            Terminal.printError("There must be no authorized users to execute this command!!!");
            return;
        }
        if (!Validator.validateAuthenticate(command))
            return;
        String[] data = command.getData();
        this.user = this.users.checkLogPass(data[0], data[1]);
        if (this.user != null)
            Terminal.printLine("OK");
    }

    /**
     * Sign Out
     *
     * @param command string representation of the command
     */
    private void logout(Command command) {
        if (this.user == null) {
            Terminal.printError("No authorized users!!!");
            return;
        }
        if (!Validator.validateLogout(command))
            return;
        this.user = null;
        Terminal.printLine("OK");
    }

    /**
     * Sending a mail
     *
     * @param command string representation of the command
     */
    private void sendMail(Command command) {
        if (this.user == null) {
            Terminal.printError("To execute this command, you need to log in to the system!!!");
            return;
        }
        Class<? extends User> userClass = this.user.getClass();
        if (!userClass.equals(MailMan.class) && !userClass.equals(Customer.class)) {
            Terminal.printError("The send-mail command can only be performed by the customer or mailman");
            return;
        }
        if (!Validator.validateSendMail(command, userClass))
            return;
        String[] data = command.getData();
        User recipient = this.users.getUserByName(data[1]);
        if (recipient == null || !recipient.getClass().equals(Customer.class)) {
            Terminal.printError("User named customer \"" + data[1] + "\" is not registered in the system!!!");
        } else if (!price.containsKey(data[0])) {
            Terminal.printError("Mail must be of type: Letter, Registered, Parcel, PaketS, PaketM, PaketL.");
        } else {
            User sender = null;
            if (userClass.equals(Customer.class)) {
                sender = this.user;
            } else if (user instanceof MailMan) {
                sender = this.users.getUserByName(data[2]);
            }
            if (sender != null && sender.getClass().equals(Customer.class)) {
                ((Customer) sender).sendMail(data[0], recipient);
                Terminal.printLine("OK");
            } else
                Terminal.printError("User named customer \"" + data[2] + "\" is not registered in the system!!!");
        }

    }

    /**
     * Receiving a mail
     *
     * @param command string representation of the command
     */
    private void getMail(Command command) {
        if (this.user == null) {
            Terminal.printError("To execute this command, you need to log in to the system!!!");
            return;
        }
        Class<? extends User> userClass = this.user.getClass();
        if (!userClass.equals(MailMan.class) && !userClass.equals(Customer.class)) {
            Terminal.printError("The get-mail command can only be performed by the customer or mailman");
            return;
        }
        if (!Validator.validateGetMail(command, userClass))
            return;
        String[] data = command.getData();
        User recipient = null;
        if (userClass.equals(Customer.class))
            recipient = this.user;
        else if (user instanceof MailMan) {
            recipient = this.users.getUserByName(data[0]);
        }
        if (recipient == null || !recipient.getClass().equals(Customer.class))
            Terminal.printError("User named customer" + data[0] + " is not registered in the system!!!");
        else if (!((Customer) recipient).getIncomingMail())
            Terminal.printError("No incoming mail!!!");
        else
            Terminal.printLine("OK");
    }

    /**
     * Getting a list of mail available to receive
     *
     * @param command string representation of the command
     */
    private void listMail(Command command) {
        if (this.user == null) {
            Terminal.printError("To execute this command, you need to log in to the system!!!");
            return;
        }
        Class<? extends User> userClass = this.user.getClass();
        if (!Validator.validateListCommand(command, userClass))
            return;
        String[] data = command.getData();
        User user;
        if (userClass.equals(Customer.class)) {
            user = this.user;
        } else  {
            user = this.users.getUserByName(data[0]);
            if (user == null || !user.getClass().equals(Customer.class)) {
                Terminal.printError("User named customer" + data[0] + " is not registered in the system!!!");
                return;
            }
        }
        if (!((Customer) user).haveIncomingMail()) { //!!!
            Terminal.printLine("OK");
            return;
        }
        ((Customer) user).getListMail();
    }

    /**
     * Getting a list of mail that was sent
     *
     * @param command string representation of the command
     */
    private void listPrice(Command command) {
        if (this.user == null) {
            Terminal.printError("To execute this command, you need to log in to the system!!!");
            return;
        }
        Class<? extends User> userClass = this.user.getClass();
        if (!Validator.validateListCommand(command, userClass))
            return;
        String[] data = command.getData();
        User user;
        if (userClass.equals(Customer.class)) {
            user = this.user;
        } else {
            user = this.users.getUserByName(data[0]);
            if (user == null || !user.getClass().equals(Customer.class)) {
                Terminal.printError("User named customer" + data[0] + " is not registered in the system!!!");
                return;
            }
        }
        if (!((Customer) user).haveSentMail()) {
            Terminal.printLine("OK");
            return;
        }
        ((Customer) user).getListPrice();
    }

    /**
     * Change user password
     *
     * @param command string representation of the command
     */
    private void resetPin(Command command) {
        if (this.user == null) {
            Terminal.printError("To execute this command, you need to log in to the system!!!");
            return;
        }
        if (!this.user.getClass().equals(Agent.class)) {
            Terminal.printError("The reset-pin command can only be performed by the agent");
            return;
        }
        if (!Validator.validateResetPin(command))
            return;
        String[] data = command.getData();
        User user = this.users.getUserByName(data[0]);
        if (user != null && user.getClass().equals(Customer.class)) {
            user.setPassword(data[1]);
            Terminal.printLine("OK");
        } else if (user == null)
            Terminal.printError("User named customer" + data[0] + " is not registered in the system!!!");
        else if (!(user instanceof MailMan))
            Terminal.printError("The password can only be changed at the customer's");
    }

    /**
     * Post office system launch
     */
    public void open() {
        while (true) {
            String commandLine = Terminal.readLine();
            if (!Validator.validateCommand(commandLine)) {
                continue;
            }
            Command command = new Command(commandLine);
            switch (command.getCommand()) {
                case "add-customer":
                    this.addCustomer(command);
                    break;
                case "add-mailman":
                case "add-agent":
                    this.addEmployee(command);
                    break;
                case "authenticate":
                    this.authenticate(command);
                    break;
                case "logout":
                    this.logout(command);
                    break;
                case "send-mail":
                    this.sendMail(command);
                    break;
                case "get-mail":
                    this.getMail(command);
                    break;
                case "list-mail":
                    this.listMail(command);
                    break;
                case "list-price":
                    this.listPrice(command);
                    break;
                case "reset-pin":
                    this.resetPin(command);
                    break;
                case "quit":
                    return;
                default:
                    Terminal.printError("Invalid command!!!");
                    break;
            }
        }
    }
}
