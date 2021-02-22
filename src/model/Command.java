package model;

import java.util.Arrays;

/**
 * This class represents a command.
 */
public class Command {
    private String command;
    private String[] data;

    /**
     * Constructs a new command instance.
     *
     * @param command - command as String
     * @throws IndexOutOfBoundsException
     */
    public Command(String command) throws IndexOutOfBoundsException {
        this.command = command.split(" ")[0];
        if (command.split(" ").length > 1) {
            this.data = command.split(" ")[1].split(";");
        } else
            this.data = new String[0];
    }

    /**
     * Returns the command to be executed
     *
     * @return command to be executed as String
     */
    public String getCommand() {
        return command;
    }

    /**
     * Returns input
     *
     * @return input
     */
    public String[] getData() {
        return Arrays.copyOf(this.data, this.data.length);
    }
}
