package model;

import io.Terminal;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * This class represents a list-mail.
 */
public class MailList {
    private ArrayList<Mail> mails;

    /**
     * Constructs a new mail-list instance.
     */
    public MailList() {
        this.mails = new ArrayList<>();
    }

    /**
     * Adds a new mail to the list
     *
     * @param mail added mail
     */
    public void addMail(Mail mail) {
        this.mails.add(mail);
    }

    /**
     * Get list size
     *
     * @return list size
     */
    public int size() {
        return this.mails.size();
    }


    /**
     * Returns a map with a message type as a key, the number of mails of each type as a value
     *
     * @return map with a message type as a key, the number of mails of each type as a value
     */
    public LinkedHashMap<String, Integer> getMapMail() {
        this.mails.sort(null);
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
        for (Mail mail : this.mails) {
            if (!map.containsKey(mail.getType()))
                map.put(mail.getType(), 0);
            map.put(mail.getType(), map.get(mail.getType()) + 1);
        }
        return map;
    }

    /**
     * Print the list of incoming mails
     *
     * @param map - map with a message type as a key, the number of mails of each type as a value
     */
    public void printListMail(LinkedHashMap<String, Integer> map) {
        String result = "";
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            result += entry.getKey() + ";" + entry.getValue() + "\r\n";
        }
        Terminal.printLine(result);
    }

    /**
     * Print the list of incoming mails
     *
     * @param map - map with a message type as a key, the number of mails of each type as a value
     */
    public void printListPrice(LinkedHashMap<String, Integer> map) {
        String result = "";
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            double price = entry.getValue() * PostOffice.price.get(entry.getKey());
            result += entry.getKey() + ";" + entry.getValue() + ";" + price + "\r\n";
        }
        Terminal.printLine(result);
    }

    /**
     * Clear the list
     *
     * @return {@code true} is successful, {@code false} if the list is empty.
     */
    public boolean clear() {
        if (this.mails.size() == 0)
            return false;
        this.mails.clear();
        return true;
    }

    @Override
    public String toString() {
        return "MailList{"
                + "mails=" + mails
                + '}';
    }
}
