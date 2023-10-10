package seedu.intern.logic.parser;

import static seedu.intern.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import seedu.intern.logic.commands.AddCommand;
import seedu.intern.logic.parser.exceptions.ParseException;
import seedu.intern.model.application.Email;
import seedu.intern.model.application.InternApplication;
import seedu.intern.model.application.Name;
import seedu.intern.model.application.Role;
import seedu.intern.model.application.Status;
import seedu.intern.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_ROLE, PREFIX_EMAIL, PREFIX_STATUS, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_STATUS, PREFIX_ROLE, PREFIX_EMAIL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_ROLE, PREFIX_EMAIL, PREFIX_STATUS);
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Role role = ParserUtil.parseRole(argMultimap.getValue(PREFIX_ROLE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Status status = ParserUtil.parseStatus(argMultimap.getValue(PREFIX_STATUS).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        InternApplication internApplication = new InternApplication(name, role, email, status, tagList);

        return new AddCommand(internApplication);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}