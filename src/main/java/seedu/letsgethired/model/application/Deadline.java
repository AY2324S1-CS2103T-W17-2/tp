package seedu.letsgethired.model.application;

import static java.util.Objects.requireNonNull;
import static seedu.letsgethired.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an Intern Application's deadline in the intern tracker.
 * Guarantees: immutable; is always valid
 */
public class Deadline {

    public static final String MESSAGE_CONSTRAINTS =
            "Deadline should be a valid date in this format: dd MMM yyyy, for example, 25 Sep 2023.";

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy");

    public final LocalDate value;

    /**
     * Constructs a {@code Deadline} object.
     *
     * @param date A valid date.
     */
    public Deadline(String date) {
        requireNonNull(date);
        checkArgument(isValidDeadline(date), MESSAGE_CONSTRAINTS);
        value = LocalDate.parse(date, DATE_FORMATTER);
    }

    /**
     * Returns if a given string is a valid deadline.
     */
    public static boolean isValidDeadline(String date) {
        try {
            LocalDate localDate = LocalDate.parse(date, DATE_FORMATTER);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return value.format(DATE_FORMATTER);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Deadline // instanceof handles nulls
                && value.equals(((Deadline) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}