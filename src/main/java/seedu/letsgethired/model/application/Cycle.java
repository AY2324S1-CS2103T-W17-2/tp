package seedu.letsgethired.model.application;

import static java.util.Objects.requireNonNull;
import static seedu.letsgethired.commons.util.AppUtil.checkArgument;

/**
 * Represents an InternApplication's cycle in the intern book.
 * Guarantees: immutable; is valid as declared in {@link #isValidCycle(String)}
 */
public class Cycle {

    public static final String MESSAGE_CONSTRAINTS =
            "Cycle should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the cycle must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum}- ]*";

    public final String value;

    /**
     * Constructs an {@code Cycle}.
     *
     * @param cycle A valid cycle.
     */
    public Cycle(String cycle) {
        requireNonNull(cycle);
        checkArgument(isValidCycle(cycle), MESSAGE_CONSTRAINTS);
        value = cycle;
    }

    /**
     * Returns if a given string is a valid cycle.
     */
    public static boolean isValidCycle(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Cycle)) {
            return false;
        }

        Cycle otherCycle = (Cycle) other;
        return value.equals(otherCycle.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
