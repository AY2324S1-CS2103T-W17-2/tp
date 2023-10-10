package seedu.intern.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.intern.model.InternTracker;
import seedu.intern.model.Model;

/**
 * Clears the intern book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Status book has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setInternBook(new InternTracker());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}