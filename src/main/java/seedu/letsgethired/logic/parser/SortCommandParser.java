package seedu.letsgethired.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.letsgethired.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_CYCLE;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_STATUS;

import seedu.letsgethired.logic.commands.SortCommand;
import seedu.letsgethired.logic.parser.exceptions.ParseException;
import seedu.letsgethired.model.application.InternApplicationComparator;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns an SortCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_COMPANY, PREFIX_ROLE, PREFIX_CYCLE, PREFIX_STATUS, PREFIX_DEADLINE);

        argMultimap.verifyOnlyOnePrefix();

        InternApplicationComparator comparator = null;

        if (argMultimap.getValue(PREFIX_COMPANY).isPresent()) {
            SortOrder order = ParserUtil.parseSortOrder(argMultimap.getValue(PREFIX_COMPANY).get());
            comparator = InternApplicationComparator.getComparator(PREFIX_COMPANY, order);
        }

        if (argMultimap.getValue(PREFIX_ROLE).isPresent()) {
            SortOrder order = ParserUtil.parseSortOrder(argMultimap.getValue(PREFIX_ROLE).get());
            comparator = InternApplicationComparator.getComparator(PREFIX_ROLE, order);
        }

        if (argMultimap.getValue(PREFIX_CYCLE).isPresent()) {
            SortOrder order = ParserUtil.parseSortOrder(argMultimap.getValue(PREFIX_CYCLE).get());
            comparator = InternApplicationComparator.getComparator(PREFIX_CYCLE, order);
        }

        if (argMultimap.getValue(PREFIX_STATUS).isPresent()) {
            SortOrder order = ParserUtil.parseSortOrder(argMultimap.getValue(PREFIX_STATUS).get());
            comparator = InternApplicationComparator.getComparator(PREFIX_STATUS, order);
        }

        if (argMultimap.getValue(PREFIX_DEADLINE).isPresent()) {
            SortOrder order = ParserUtil.parseSortOrder(argMultimap.getValue(PREFIX_DEADLINE).get());
            comparator = InternApplicationComparator.getComparator(PREFIX_DEADLINE, order);
        }

        if (argMultimap.getPreamble().equalsIgnoreCase("default")) {
            comparator = InternApplicationComparator.DEFAULT_SORT_COMPARATOR;
        }

        if (comparator == null) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        return new SortCommand(comparator);

    }
}
