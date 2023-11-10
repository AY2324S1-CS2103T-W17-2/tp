package seedu.letsgethired.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.letsgethired.model.Model;

/**
 * Lists all intern applications in the intern tracker to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all intern applications";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.getFilteredInternApplicationList();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
