package seedu.letsgethired.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.letsgethired.storage.JsonAdaptedInternApplication.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.letsgethired.testutil.Assert.assertThrows;
import static seedu.letsgethired.testutil.TypicalInternApplications.OPTIVER;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.letsgethired.commons.exceptions.IllegalValueException;
import seedu.letsgethired.model.application.Company;
import seedu.letsgethired.model.application.Cycle;
import seedu.letsgethired.model.application.Deadline;
import seedu.letsgethired.model.application.Note;
import seedu.letsgethired.model.application.Role;
import seedu.letsgethired.model.application.Status;

public class JsonAdaptedInternApplicationTest {
    private static final String INVALID_COMPANY = "Jane/Street";
    private static final String INVALID_ROLE = " ";
    private static final String INVALID_STATUS = " ";
    private static final String INVALID_CYCLE = "example.com";
    private static final ArrayList<String> INVALID_NOTE = Arrays.stream(new String[]{" "})
            .collect(Collectors.toCollection(ArrayList<String>::new));
    private static final String INVALID_DEADLINE = "25 Oct";
    private static final String VALID_NAME = OPTIVER.getCompany().toString();
    private static final String VALID_ROLE = OPTIVER.getRole().toString();
    private static final String VALID_CYCLE = OPTIVER.getCycle().toString();
    private static final String VALID_STATUS = OPTIVER.getStatus().toString();
    private static final ArrayList<String> VALID_NOTE = OPTIVER.getNotes()
            .stream()
            .map(x -> x.value)
            .collect(Collectors.toCollection(ArrayList<String>::new));
    private static final String VALID_DEADLINE = OPTIVER.getDeadline().toString();

    @Test
    public void toModelType_validInternApplicationDetails_returnsInternApplication() throws Exception {
        JsonAdaptedInternApplication application = new JsonAdaptedInternApplication(OPTIVER);
        assertEquals(OPTIVER, application.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {

        JsonAdaptedInternApplication application =
                new JsonAdaptedInternApplication(
                        INVALID_COMPANY, VALID_ROLE, VALID_CYCLE, VALID_NOTE, VALID_STATUS, VALID_DEADLINE);
        String expectedMessage = Company.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedInternApplication application =
                new JsonAdaptedInternApplication(
                        null, VALID_ROLE, VALID_CYCLE, VALID_NOTE, VALID_STATUS, VALID_DEADLINE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Company.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_invalidRole_throwsIllegalValueException() {
        JsonAdaptedInternApplication application =
                new JsonAdaptedInternApplication(
                        VALID_NAME, INVALID_ROLE, VALID_CYCLE, VALID_NOTE, VALID_STATUS, VALID_DEADLINE);
        String expectedMessage = Role.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_nullRole_throwsIllegalValueException() {
        JsonAdaptedInternApplication application =
                new JsonAdaptedInternApplication(
                        VALID_NAME, null, VALID_CYCLE, VALID_NOTE, VALID_STATUS, VALID_DEADLINE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Role.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_invalidCycle_throwsIllegalValueException() {
        JsonAdaptedInternApplication application =
                new JsonAdaptedInternApplication(
                        VALID_NAME, VALID_ROLE, INVALID_CYCLE, VALID_NOTE, VALID_STATUS, VALID_DEADLINE);
        String expectedMessage = Cycle.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_nullCycle_throwsIllegalValueException() {
        JsonAdaptedInternApplication application =
                new JsonAdaptedInternApplication(
                        VALID_NAME, VALID_ROLE, null, VALID_NOTE, VALID_STATUS, VALID_DEADLINE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Cycle.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_invalidNote_throwsIllegalValueException() {
        JsonAdaptedInternApplication application =
                new JsonAdaptedInternApplication(
                        VALID_NAME, VALID_ROLE, VALID_CYCLE, INVALID_NOTE, VALID_STATUS, VALID_DEADLINE);
        String expectedMessage = Note.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_nullNote_throwsIllegalValueException() {
        JsonAdaptedInternApplication application =
                new JsonAdaptedInternApplication(
                        VALID_NAME, VALID_ROLE, VALID_CYCLE, null, VALID_STATUS, VALID_DEADLINE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Note.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_invalidStatus_throwsIllegalValueException() {
        JsonAdaptedInternApplication application =
                new JsonAdaptedInternApplication(
                        VALID_NAME, VALID_ROLE, VALID_CYCLE, VALID_NOTE, INVALID_STATUS, VALID_DEADLINE);
        String expectedMessage = Status.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_nullStatus_throwsIllegalValueException() {
        JsonAdaptedInternApplication application =
                new JsonAdaptedInternApplication(
                        VALID_NAME, VALID_ROLE, VALID_CYCLE, VALID_NOTE, null, VALID_DEADLINE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Status.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_invalidDeadline_throwsIllegalValueException() {
        JsonAdaptedInternApplication application =
                new JsonAdaptedInternApplication(
                        VALID_NAME, VALID_ROLE, VALID_CYCLE, VALID_NOTE, VALID_STATUS, INVALID_DEADLINE);
        String expectedMessage = Deadline.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_nullDeadline_throwsIllegalValueException() {
        JsonAdaptedInternApplication application =
                new JsonAdaptedInternApplication(
                        VALID_NAME, VALID_ROLE, VALID_CYCLE, VALID_NOTE, VALID_STATUS, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Deadline.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }
}
