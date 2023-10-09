package seedu.intern.logic.parser;

import static seedu.intern.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.intern.logic.commands.CommandTestUtil.STATUS_DESC_AMY;
import static seedu.intern.logic.commands.CommandTestUtil.STATUS_DESC_BOB;
import static seedu.intern.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.intern.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.intern.logic.commands.CommandTestUtil.INVALID_STATUS_DESC;
import static seedu.intern.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.intern.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.intern.logic.commands.CommandTestUtil.INVALID_ROLE_DESC;
import static seedu.intern.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.intern.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.intern.logic.commands.CommandTestUtil.ROLE_DESC_AMY;
import static seedu.intern.logic.commands.CommandTestUtil.ROLE_DESC_BOB;
import static seedu.intern.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.intern.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_STATUS_AMY;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_ROLE_AMY;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_ROLE_BOB;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.intern.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.intern.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.intern.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.intern.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.intern.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import org.junit.jupiter.api.Test;

import seedu.intern.commons.core.index.Index;
import seedu.intern.logic.Messages;
import seedu.intern.logic.commands.EditCommand;
import seedu.intern.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.intern.model.application.Status;
import seedu.intern.model.application.Email;
import seedu.intern.model.application.Name;
import seedu.intern.model.application.Role;
import seedu.intern.model.tag.Tag;
import seedu.intern.testutil.EditPersonDescriptorBuilder;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_ROLE_DESC, Role.MESSAGE_CONSTRAINTS); // invalid role
        assertParseFailure(parser, "1" + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, "1" + INVALID_STATUS_DESC, Status.MESSAGE_CONSTRAINTS); // invalid status
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid role followed by valid email
        assertParseFailure(parser, "1" + INVALID_ROLE_DESC + EMAIL_DESC_AMY, Role.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Person} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_DESC_HUSBAND + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_EMPTY + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_FRIEND + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_EMAIL_DESC + VALID_STATUS_AMY + VALID_ROLE_AMY,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + ROLE_DESC_BOB + TAG_DESC_HUSBAND
                + EMAIL_DESC_AMY + STATUS_DESC_AMY + NAME_DESC_AMY + TAG_DESC_FRIEND;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withRole(VALID_ROLE_BOB).withEmail(VALID_EMAIL_AMY).withStatus(VALID_STATUS_AMY)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + ROLE_DESC_BOB + EMAIL_DESC_AMY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withRole(VALID_ROLE_BOB)
                .withEmail(VALID_EMAIL_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY;
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // role
        userInput = targetIndex.getOneBased() + ROLE_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withRole(VALID_ROLE_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + EMAIL_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // status
        userInput = targetIndex.getOneBased() + STATUS_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withStatus(VALID_STATUS_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_FRIEND;
        descriptor = new EditPersonDescriptorBuilder().withTags(VALID_TAG_FRIEND).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_failure() {
        // More extensive testing of duplicate parameter detections is done in
        // AddCommandParserTest#parse_repeatedNonTagValue_failure()

        // valid followed by invalid
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + INVALID_ROLE_DESC + ROLE_DESC_BOB;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ROLE));

        // invalid followed by valid
        userInput = targetIndex.getOneBased() + ROLE_DESC_BOB + INVALID_ROLE_DESC;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ROLE));

        // mulltiple valid fields repeated
        userInput = targetIndex.getOneBased() + ROLE_DESC_AMY + STATUS_DESC_AMY + EMAIL_DESC_AMY
                + TAG_DESC_FRIEND + ROLE_DESC_AMY + STATUS_DESC_AMY + EMAIL_DESC_AMY + TAG_DESC_FRIEND
                + ROLE_DESC_BOB + STATUS_DESC_BOB + EMAIL_DESC_BOB + TAG_DESC_HUSBAND;

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ROLE, PREFIX_EMAIL, PREFIX_STATUS));

        // multiple invalid values
        userInput = targetIndex.getOneBased() + INVALID_ROLE_DESC + INVALID_STATUS_DESC + INVALID_EMAIL_DESC
                + INVALID_ROLE_DESC + INVALID_STATUS_DESC + INVALID_EMAIL_DESC;

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ROLE, PREFIX_EMAIL, PREFIX_STATUS));
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withTags().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
