package seedu.letsgethired.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_CYCLE;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import seedu.letsgethired.commons.core.index.Index;
import seedu.letsgethired.commons.util.CollectionUtil;
import seedu.letsgethired.commons.util.ToStringBuilder;
import seedu.letsgethired.logic.Messages;
import seedu.letsgethired.logic.commands.exceptions.CommandException;
import seedu.letsgethired.model.Model;
import seedu.letsgethired.model.application.Company;
import seedu.letsgethired.model.application.Cycle;
import seedu.letsgethired.model.application.Deadline;
import seedu.letsgethired.model.application.Id;
import seedu.letsgethired.model.application.InternApplication;
import seedu.letsgethired.model.application.Note;
import seedu.letsgethired.model.application.Role;
import seedu.letsgethired.model.application.Status;

/**
 * Edits the details of an existing intern application in the interntracker.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the details of the intern application "
            + "identified by the index number used in the displayed intern application list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_COMPANY + "COMPANY] "
            + "[" + PREFIX_ROLE + "ROLE] "
            + "[" + PREFIX_CYCLE + "CYCLE] "
            + "[" + PREFIX_DEADLINE + "DEADLINE] "
            + "[" + PREFIX_STATUS + "STATUS] "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_ROLE + "Software Engineering Intern "
            + PREFIX_CYCLE + "Summer 2024 "
            + PREFIX_STATUS + "Accepted ";

    public static final String MESSAGE_EDIT_INTERN_APPLICATION_SUCCESS = "Edited Intern Application";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_INTERN_APPLICATION =
            "This intern application already exists in the intern tracker.";

    private final Index index;
    private final EditInternApplicationDescriptor editInternApplicationDescriptor;

    /**
     * @param index of the intern application in the filtered intern application list to edit
     * @param editInternApplicationDescriptor details to edit the intern application with
     */
    public EditCommand(Index index, EditInternApplicationDescriptor editInternApplicationDescriptor) {
        requireNonNull(index);
        requireNonNull(editInternApplicationDescriptor);

        this.index = index;
        this.editInternApplicationDescriptor = new EditInternApplicationDescriptor(editInternApplicationDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<InternApplication> lastShownList = model.getFilteredInternApplicationList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INTERN_APPLICATION_DISPLAYED_INDEX);
        }

        InternApplication internApplicationToEdit = lastShownList.get(index.getZeroBased());
        InternApplication editedInternApplication =
                createEditedInternApplication(internApplicationToEdit, editInternApplicationDescriptor);

        if (!internApplicationToEdit.isSameApplication(editedInternApplication)
                && model.hasInternApplication(editedInternApplication)) {
            throw new CommandException(MESSAGE_DUPLICATE_INTERN_APPLICATION);
        }

        model.setInternApplication(internApplicationToEdit, editedInternApplication);
        return new CommandResult(MESSAGE_EDIT_INTERN_APPLICATION_SUCCESS, editedInternApplication);
    }

    /**
     * Creates and returns a {@code InternApplication} with the details of {@code internApplicationToEdit}
     * edited with {@code editInternApplicationDescriptor}.
     */
    private static InternApplication createEditedInternApplication(
            InternApplication internApplicationToEdit,
            EditInternApplicationDescriptor editInternApplicationDescriptor) {
        assert internApplicationToEdit != null;

        Id id = editInternApplicationDescriptor.getId()
                .orElse(internApplicationToEdit.getId());
        Company updatedCompany = editInternApplicationDescriptor.getCompany()
                .orElse(internApplicationToEdit.getCompany());
        Role updatedRole = editInternApplicationDescriptor.getRole()
                .orElse(internApplicationToEdit.getRole());
        Cycle updatedCycle = editInternApplicationDescriptor.getCycle()
                .orElse(internApplicationToEdit.getCycle());
        List<Note> updatedNote = editInternApplicationDescriptor.getNote()
                .orElse(internApplicationToEdit.getNotes());
        Status updatedStatus = editInternApplicationDescriptor.getStatus()
                .orElse(internApplicationToEdit.getStatus());
        Deadline updatedDeadline = editInternApplicationDescriptor.getDeadline()
                .orElse(internApplicationToEdit.getDeadline());

        return new InternApplication(
                id,
                updatedCompany,
                updatedRole,
                updatedCycle,
                updatedNote,
                updatedStatus,
                updatedDeadline);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        EditCommand otherEditCommand = (EditCommand) other;
        return index.equals(otherEditCommand.index)
                && editInternApplicationDescriptor.equals(otherEditCommand.editInternApplicationDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("editInternApplicationDescriptor", editInternApplicationDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the intern application with. Each non-empty field value will replace the
     * corresponding field value of the intern application.
     */
    public static class EditInternApplicationDescriptor {
        private Id id;
        private Company company;
        private Role role;
        private Cycle cycle;
        private List<Note> notes;
        private Status status;
        private Deadline deadline;

        public EditInternApplicationDescriptor() {}

        /**
         * Copy constructor.
         */
        public EditInternApplicationDescriptor(EditInternApplicationDescriptor toCopy) {
            setId(toCopy.id);
            setCompany(toCopy.company);
            setRole(toCopy.role);
            setCycle(toCopy.cycle);
            setNotes(toCopy.notes);
            setStatus(toCopy.status);
            setDeadline(toCopy.deadline);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(id, company, role, cycle, status, deadline);
        }

        public void setId(Id id) {
            this.id = id;
        }

        public Optional<Id> getId() {
            return Optional.ofNullable(id);
        }

        public void setCompany(Company company) {
            this.company = company;
        }

        public Optional<Company> getCompany() {
            return Optional.ofNullable(company);
        }

        public void setRole(Role role) {
            this.role = role;
        }

        public Optional<Role> getRole() {
            return Optional.ofNullable(role);
        }

        public void setCycle(Cycle cycle) {
            this.cycle = cycle;
        }

        public Optional<Cycle> getCycle() {
            return Optional.ofNullable(cycle);
        }

        public void setNotes(List<Note> notes) {
            this.notes = notes;
        }

        public Optional<List<Note>> getNote() {
            return Optional.ofNullable(notes);
        }

        public void setStatus(Status status) {
            this.status = status;
        }

        public Optional<Status> getStatus() {
            return Optional.ofNullable(status);
        }

        public void setDeadline(Deadline deadline) {
            this.deadline = deadline;
        }

        public Optional<Deadline> getDeadline() {
            return Optional.ofNullable(deadline);
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditInternApplicationDescriptor)) {
                return false;
            }

            EditInternApplicationDescriptor otherEditInternApplicationDescriptor =
                    (EditInternApplicationDescriptor) other;
            return Objects.equals(company, otherEditInternApplicationDescriptor.company)
                    && Objects.equals(role, otherEditInternApplicationDescriptor.role)
                    && Objects.equals(cycle, otherEditInternApplicationDescriptor.cycle)
                    && Objects.equals(status, otherEditInternApplicationDescriptor.status);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("company", company)
                    .add("role", role)
                    .add("cycle", cycle)
                    .add("status", status)
                    .add("deadline", deadline)
                    .toString();
        }
    }
}
