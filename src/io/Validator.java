package io;

import model.*;

/**
 * Class for verifying commands
 */
public class Validator {
    private static final String ERROR_SEMICOLON = "The input must not contain a line break or semicolon.";

    /**
     * Checks the correctness of the input for the command
     *
     * @param command command as string
     * @return {@code true} if the check was successful, {@code false} otherwise
     */
    public static boolean validateCommand(String command) {
        if (command.split(" ").length > 2) {
            Terminal.printError("The command can contain only one space to separate the command from the user data !!");
            return false;
        } else if (command.endsWith(";")) {
            Terminal.printError("Invalid command, the entered data must not contain a line break or semicolon");
            return false;
        }
        return true;
    }

    /**
     * Checks the correctness of the input for the first name
     *
     * @param firstName first name user
     * @return {@code true} if the check was successful, {@code false} otherwise
     */
    private static boolean validateFirstName(String firstName) {
        if (firstName.length() == 0) {
            Terminal.printError("First Name must contain more than one character!!!");
            return false;
        }
        if (firstName.contains(" ")) {
            Terminal.printError("First Name must not contain a line break or semicolon!!!");
            return false;
        }
        return true;
    }

    /**
     * Checks the correctness of the input for the last name
     *
     * @param lastName last name user
     * @return {@code true} if the check was successful, {@code false} otherwise
     */
    private static boolean validateLastName(String lastName) {
        if (lastName.length() == 0) {
            Terminal.printError("Last Name must contain more than one character!!!");
            return false;
        }
        if (lastName.contains(" ")) {
            Terminal.printError("Last Name must not contain a line break or semicolon!!!");
            return false;
        }
        return true;
    }

    /**
     * Checks the correctness of the input for the user Name
     *
     * @param userName user Name
     * @return {@code true} if the check was successful, {@code false} otherwise
     */
    private static boolean validateUserName(String userName) {
        if (userName.length() < 4 || userName.length() > 9) {
            Terminal.printError("Username must be between 4 and 9 characters!!!");
            return false;
        }
        if (userName.contains(" ")) {
            Terminal.printError("Username must not contain a line break or semicolon!!!");
            return false;
        }
        return true;
    }

    /**
     * Checks the correctness of the input for the user password
     *
     * @param password user password
     * @return {@code true} if the check was successful, {@code false} otherwise
     */
    private static boolean validatePassword(String password) {
        if (password.length() < 4 || password.length() > 9) {
            Terminal.printError("Password must be between 4 and 9 characters!!!");
            return false;
        }
        if (password.contains(" ")) {
            Terminal.printError("Password must not contain a line break or semicolon!!!");
            return false;
        }
        return true;
    }

    /**
     * Checks the correctness of the input for the customer personal number
     *
     * @param personalNumber customer personal number
     * @return {@code true} if the check was successful, {@code false} otherwise
     */
    private static boolean validatePersonalNumberCustomer(String personalNumber) {
        if (personalNumber.length() != 9) {
            Terminal.printError("Personal number must contain 9 digits!!!");
            return false;
        }
        if (personalNumber.contains(" ")) {
            Terminal.printError("Personal number must not contain a line break or semicolon!!!");
            return false;
        }
        try {
            int i = Integer.parseInt(personalNumber);
            if (i < 0) {
                Terminal.printError("Personal number must be a 9-digit positive number!!!");
                return false;
            }
        } catch (NumberFormatException e) {
            System.out.println("Personal number must contain only numbers!!!");
            return false;
        }
        return true;
    }

    /**
     * Checks the correctness of the input for the employee personal number
     *
     * @param personalNumber - employee personal number
     * @return {@code true} if the check was successful, {@code false} otherwise
     */
    private static boolean validatePersonalNumberEmployee(String personalNumber) {
        try {
            int a = Integer.parseInt(personalNumber);
            if (a < 0) {
                Terminal.printError("Personal number must be positive number!!!");
                return false;
            }
        } catch (NumberFormatException e) {
            System.out.println("Personal number must be an integer number!!!");
            return false;
        }
        return true;
    }

    /**
     * checks the correctness of the input for the command add-customer
     *
     * @param command - command as string
     * @return {@code true} if the check was successful, {@code false} otherwise
     */
    public static boolean validateAddCustomer(Command command) {
        String[] data = command.getData();
        if (data.length != 5) {
            String comm = "add-customer FirstName;LastName;UserName;Password;PersonalNumber";
            Terminal.printError("The add customer command should look like this: "  + comm + ". "
                    + ERROR_SEMICOLON);
            return false;
        }
        return Validator.validateFirstName(data[0])
                && Validator.validateLastName(data[1])
                && Validator.validateUserName(data[2])
                && Validator.validatePassword(data[3])
                && Validator.validatePersonalNumberCustomer(data[4]);
    }

    /**
     * checks the correctness of the input for the command add-mailman or add-agent
     *
     * @param command command as string
     * @return {@code true} if the check was successful, {@code false} otherwise
     */
    public static boolean validateAddEmployee(Command command) {
        String[] data = command.getData();
        if (data.length != 4) {
            String comm = "add-mailman/agent FirstName;LastName;PersonalNumber;Password;";
            Terminal.printError("The add mailMan/agent command should look like this: " + comm + ". "
                    + ERROR_SEMICOLON);
            return false;
        }
        return Validator.validateFirstName(data[0])
                && Validator.validateLastName(data[1])
                && Validator.validatePersonalNumberEmployee(data[2])
                && Validator.validatePassword(data[3]);
    }

    /**
     * checks the correctness of the input for the command authenticate
     *
     * @param command command as string
     * @return {@code true} if the check was successful, {@code false} otherwise
     */
    public static boolean validateAuthenticate(Command command) {
        if (command.getData().length != 2) {
            Terminal.printError("The authenticate command should look like this: \"authenticate UserName;Password\". ");
            return false;
        }
        return true;
    }

    /**
     * checks the correctness of the input for the command logout
     *
     * @param command command as string
     * @return {@code true} if the check was successful, {@code false} otherwise
     */
    public static boolean validateLogout(Command command) {
        if (command.getData().length != 0) {
            Terminal.printError("The logout command should look like this: \"logout\".");
            return false;
        }
        return true;
    }

    /**
     * checks the correctness of the input for the command send-mail
     *
     * @param command   command as string
     * @param userClass the class whose object is executing the command
     * @return {@code true} if the check was successful, {@code false} otherwise
     */
    public static boolean validateSendMail(Command command, Class<? extends User> userClass) {
        if (userClass.equals(Customer.class)) {
            if (command.getData().length != 2) {
                String comm = "send-mail PostalService;Recipient";
                Terminal.printError("The send mail command should look like this: " + comm + ". ");
                return false;
            }
        } else if (userClass.equals(MailMan.class)) {
            if (command.getData().length != 3) {
                String comm = "send-mail PostalService;Recipient;Sender";
                Terminal.printError("The send mail command should look like this: " + comm +    ". ");
                return false;
            }
        }
        return true;
    }

    /**
     * checks the correctness of the input for the command get-mail
     *
     * @param command   command as string
     * @param userClass the class whose object is executing the command
     * @return {@code true} if the check was successful, {@code false} otherwise
     */
    public static boolean validateGetMail(Command command, Class<? extends User> userClass) {
        if (userClass.equals(Customer.class)) {
            if (command.getData().length != 0) {
                Terminal.printError("The get mail command should look like this: \"get-mail\". ");
                return false;
            }
        } else if (userClass.equals(MailMan.class)) {
            if (command.getData().length != 1) {
                Terminal.printError("The get mail command should look like this: \"get-mail Recipient\". ");
                return false;
            }
        }
        return true;
    }

    /**
     * checks the correctness of the input for the command list-mail and list-price
     *
     * @param command   command as string
     * @param userClass the class whose object is executing the command
     * @return {@code true} if the check was successful, {@code false} otherwise
     */
    public static boolean validateListCommand(Command command, Class<? extends User> userClass) {
        String str = "";
        if (command.getCommand().equals("list-mail"))
            str = "list-mail";
        else if (command.getCommand().equals("list-price"))
            str = "list-price";
        if (userClass.equals(Customer.class)) {
            if (command.getData().length != 0) {
                Terminal.printError("The " + str + " command should look like this: \"" + str + "\". ");
                return false;
            }
        } else if (userClass.equals(MailMan.class) || userClass.equals(Agent.class)) {
            if (command.getData().length != 1) {
                Terminal.printError("The " + str + " command should look like this: \"" + str + " UserName\". ");
                return false;
            }
        }
        return true;
    }

    /**
     * checks the correctness of the input for the command reset-pin
     *
     * @param command command as string
     * @return {@code true} if the check was successful, {@code false} otherwise
     */
    public static boolean validateResetPin(Command command) {
        String[] data = command.getData();
        if (data.length != 2) {
            Terminal.printError("The reset pin command should look like this: \"reset-pin UserName;Password\". ");
            return false;
        }
        return Validator.validatePassword(data[1]);
    }
}
