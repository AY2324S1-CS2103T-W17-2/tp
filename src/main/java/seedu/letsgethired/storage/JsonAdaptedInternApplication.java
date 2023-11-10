package seedu.letsgethired.storage;

import java.util.ArrayList;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.letsgethired.commons.exceptions.IllegalValueException;
import seedu.letsgethired.model.application.Company;
import seedu.letsgethired.model.application.Cycle;
import seedu.letsgethired.model.application.Deadline;
import seedu.letsgethired.model.application.Id;
import seedu.letsgethired.model.application.InternApplication;
import seedu.letsgethired.model.application.Note;
import seedu.letsgethired.model.application.Role;
import seedu.letsgethired.model.application.Status;


/**
 * Jackson-friendly version of {@link InternApplication}.
 */
class JsonAdaptedInternApplication {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Intern Application's %s field is missing!";
    private final String company;
    private final String role;
    private final String cycle;
    private final String status;
    private final ArrayList<String> notes;
    private final String deadline;

    /**
     * Constructs a {@code JsonAdaptedInternApplication} with the given intern application details.
     */
    @JsonCreator
    public JsonAdaptedInternApplication(@JsonProperty("company") String company,
                                        @JsonProperty("role") String role,
                                        @JsonProperty("cycle") String cycle,
                                        @JsonProperty("note") ArrayList<String> notes,
                                        @JsonProperty("status") String status,
                                        @JsonProperty("deadline") String deadline) {
        this.company = company;
        this.role = role;
        this.cycle = cycle;
        this.notes = notes;
        this.status = status;
        this.deadline = deadline;
    }

    /**
     * Converts a given {@code InternApplication} into this class for Jackson use.
     */
    public JsonAdaptedInternApplication(InternApplication source) {
        company = source.getCompany().value;
        role = source.getRole().value;
        cycle = source.getCycle().value;
        notes = source.getNotes().stream().map(x -> x.value).collect(Collectors.toCollection(ArrayList<String>::new));
        status = source.getStatus().value;
        deadline = source.getDeadline().value;
    }

    /**
     * Converts this Jackson-friendly adapted intern application object
     * into the model's {@code InternApplication} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted intern application.
     */
    public InternApplication toModelType() throws IllegalValueException {
        final Id modelId = Id.generateNewUseableId();
        if (company == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Company.class.getSimpleName()));
        }
        if (!Company.isValidCompany(company)) {
            throw new IllegalValueException(Company.MESSAGE_CONSTRAINTS);
        }
        final Company modelCompany = new Company(company);

        if (role == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Role.class.getSimpleName()));
        }
        if (!Role.isValidRole(role)) {
            throw new IllegalValueException(Role.MESSAGE_CONSTRAINTS);
        }
        final Role modelRole = new Role(role);

        if (cycle == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Cycle.class.getSimpleName()));
        }
        if (!Cycle.isValidCycle(cycle)) {
            throw new IllegalValueException(Cycle.MESSAGE_CONSTRAINTS);
        }
        final Cycle modelCycle = new Cycle(cycle);

        if (status == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Status.class.getSimpleName()));
        }
        if (!Status.isValidStatus(status)) {
            throw new IllegalValueException(Status.MESSAGE_CONSTRAINTS);
        }
        final Status modelStatus = new Status(status);

        if (notes == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Note.class.getSimpleName()));
        }

        if (notes.stream().anyMatch(x -> !Note.isValidNote(x))) {
            throw new IllegalValueException(Note.MESSAGE_CONSTRAINTS);
        }
        final ArrayList<Note> modelNote = notes
                .stream()
                .map(Note::new).collect(Collectors.toCollection(ArrayList<Note>::new));

        if (deadline == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Deadline.class.getSimpleName()));
        }
        if (!Deadline.isValidDeadline(deadline)) {
            throw new IllegalValueException(Deadline.MESSAGE_CONSTRAINTS);
        }
        final Deadline modelDeadline = new Deadline(deadline);
        return new InternApplication(modelId, modelCompany, modelRole, modelCycle, modelNote,
                modelStatus, modelDeadline);
    }
}
