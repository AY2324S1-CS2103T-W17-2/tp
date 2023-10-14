package seedu.letsgethired.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_CYCLE;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.letsgethired.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.letsgethired.commons.core.index.Index;
import seedu.letsgethired.logic.commands.exceptions.CommandException;
import seedu.letsgethired.model.InternTracker;
import seedu.letsgethired.model.Model;
import seedu.letsgethired.model.application.CompanyContainsKeywordsPredicate;
import seedu.letsgethired.model.application.InternApplication;
import seedu.letsgethired.testutil.EditInternApplicationDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_COMPANY_A = "Citadel";
    public static final String VALID_COMPANY_B = "Accenture";
    public static final String VALID_ROLE_A = "Intern A";
    public static final String VALID_ROLE_B = "Intern B";
    public static final String VALID_CYCLE_A = "Summer 2024";
    public static final String VALID_CYCLE_B = "Winter 2023";
    public static final String VALID_STATUS_A = "Pending";
    public static final String VALID_STATUS_B = "Rejected";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";
    public static final String COMPANY_DESC_A = " " + PREFIX_COMPANY + VALID_COMPANY_A;
    public static final String COMPANY_DESC_B = " " + PREFIX_COMPANY + VALID_COMPANY_B;
    public static final String ROLE_DESC_A = " " + PREFIX_ROLE + VALID_ROLE_A;
    public static final String ROLE_DESC_B = " " + PREFIX_ROLE + VALID_ROLE_B;
    public static final String CYCLE_DESC_A = " " + PREFIX_CYCLE + VALID_CYCLE_A;
    public static final String CYCLE_DESC_B = " " + PREFIX_CYCLE + VALID_CYCLE_B;
    public static final String STATUS_DESC_A = " " + PREFIX_STATUS + VALID_STATUS_A;
    public static final String STATUS_DESC_B = " " + PREFIX_STATUS + VALID_STATUS_B;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String INVALID_COMPANY_DESC = " " + PREFIX_COMPANY + "Jane Street&"; // '&' not allowed in company names
    public static final String INVALID_ROLE_DESC = " " + PREFIX_ROLE + " "; // empty string is not allowed in roles
    public static final String INVALID_CYCLE_DESC = " " + PREFIX_CYCLE + "summer!2023"; // '!' not allowed in cycles
    public static final String INVALID_STATUS_DESC = " " + PREFIX_STATUS; // empty string not allowed in status
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditInternApplicationDescriptor DESC_A;
    public static final EditCommand.EditInternApplicationDescriptor DESC_B;

    static {
        DESC_A = new EditInternApplicationDescriptorBuilder().withCompany(VALID_COMPANY_A).withRole(VALID_ROLE_A)
                .withCycle(VALID_CYCLE_A).withStatus(VALID_STATUS_A).withTags(VALID_TAG_FRIEND).build();
        DESC_B = new EditInternApplicationDescriptorBuilder().withCompany(VALID_COMPANY_B).withRole(VALID_ROLE_B)
                .withCycle(VALID_CYCLE_B).withStatus(VALID_STATUS_B).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
                .build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
                                            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
                                            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the intern tracker, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        InternTracker expectedInternTracker = new InternTracker(actualModel.getInternTracker());
        List<InternApplication> expectedFilteredList = new ArrayList<>(actualModel.getFilteredInternApplicationList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedInternTracker, actualModel.getInternTracker());
        assertEquals(expectedFilteredList, actualModel.getFilteredInternApplicationList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s intern tracker.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredInternApplicationList().size());

        InternApplication internApplication = model.getFilteredInternApplicationList().get(targetIndex.getZeroBased());
        final String[] splitName = internApplication.getCompany().value.split("\\s+");
        model.updateFilteredInternApplicationList(new CompanyContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredInternApplicationList().size());
    }

}
