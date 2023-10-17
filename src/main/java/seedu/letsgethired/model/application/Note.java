package seedu.letsgethired.model.application;

import static java.util.Objects.requireNonNull;

/**
 * Represents an InternApplication's note in the InternTracker
 * Guarantees: immutable; is always valid
 */
public class Note {
    public final String value;

    public static final String MESSAGE_CONSTRAINTS =
            "Note should only contain characters and spaces, and it should not be blank";

    /*
     * The first character of the status must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public Note(String note) {
        requireNonNull(note);
        value = note;
    }

    /**
     * Returns true if a given string is a valid note.
     */
    public static boolean isValidNote(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Note // instanceof handles nulls
                && value.equals(((Note) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}