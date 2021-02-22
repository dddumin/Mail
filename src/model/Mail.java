package model;

import java.util.Objects;

/**
 * This class represents a mail.
 */
public class Mail implements Comparable<Mail> {
    private String type;

    /**
     * Constructs a new mail instance.
     *
     * @param type mail type
     */
    public Mail(String type) {
        this.type = type;
    }

    /**
     * Returns mail type
     *
     * @return string representation of mail type
     */
    public String getType() {
        return type;
    }

    @Override
    public int compareTo(Mail o) {
        for (int i = 0; i < Math.min(this.type.length(), o.type.length()); i++) {
            if (this.type.charAt(i) != o.type.charAt(i))
                return Character.compare(this.type.charAt(i), o.type.charAt(i));
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mail mail = (Mail) o;
        return Objects.equals(type, mail.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }

    @Override
    public String toString() {
        return "Mail{"
                + "type='" + type + '\''
                + '}';
    }
}
