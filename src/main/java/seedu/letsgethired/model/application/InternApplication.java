package seedu.letsgethired.model.application;

import static seedu.letsgethired.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import seedu.letsgethired.commons.util.ToStringBuilder;

/**
 * Represents an Intern Application in the intern tracker.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class InternApplication {

    // Identity fields
    private final Id id;
    private final Company company;
    private final Role role;
    private final Cycle cycle;
    // Data fields
    private final List<Note> notes;
    private final Status status;
    private final Deadline deadline;

    /**
     * Every field must be present and not null.
     */
    public InternApplication(Company company, Role role, Cycle cycle, Status status, Deadline deadline) {
        requireAllNonNull(company, role, cycle, status, deadline);
        this.id = Id.generateNewUseableId();
        this.company = company;
        this.role = role;
        this.cycle = cycle;
        this.notes = Collections.unmodifiableList(new ArrayList<>());
        this.status = status;
        this.deadline = deadline;
    }

    /**
     * A second constructor to instantiate another InternApplication with an existing list of Notes
     * @param id representing the id key of the intern application in the database
     * @param company Company object representing the company
     * @param role Role object representing the role of the job
     * @param cycle Cycle object representing the cycle of the internship period
     * @param notes List of notes
     * @param status Status object representing the status of the application
     * @param deadline Deadline object representing deadline of the application
     */
    public InternApplication(Id id, Company company, Role role, Cycle cycle,
                             List<Note> notes, Status status, Deadline deadline) {
        requireAllNonNull(id, company, role, cycle, notes, status, deadline);
        this.id = id;
        this.company = company;
        this.role = role;
        this.cycle = cycle;
        this.notes = Collections.unmodifiableList(notes);
        this.status = status;
        this.deadline = deadline;
    }

    public Id getId() {
        return id;
    }

    public Company getCompany() {
        return company;
    }

    public Role getRole() {
        return role;
    }

    public Cycle getCycle() {
        return cycle;
    }

    public Status getStatus() {
        return status;
    }

    public Deadline getDeadline() {
        return deadline;
    }

    public List<Note> getNotes() {
        return notes;
    }

    /**
     * Formats the List of Notes to a readable string
     * @return String that is formatted into a numbered list
     */
    public String getNumberedListOfNotes() {
        Note[] noteArray = notes.toArray(new Note[0]);
        StringBuilder compiledNotes = new StringBuilder();
        for (int i = 0; i < noteArray.length; i++) {
            compiledNotes.append(i + 1)
                    .append(". ")
                    .append(noteArray[i].value)
                    .append("\n");
        }
        return compiledNotes.toString();
    }

    /**
     * Adds a Note object to the list of Notes in the InternApplication
     * @param note The Note object to be added
     * @return An InternApplication object with the added Note in its list
     */
    public InternApplication addNote(Note note) {
        List<Note> mutableList = new ArrayList<>(this.notes);
        mutableList.add(note);
        return new InternApplication(id, company, role, cycle, mutableList, status, deadline);
    }

    /**
     * Removes a Note object from the list of Notes in the InternApplication
     * @param index The index of the Note object to be deleted
     * @return An InternApplication object with the deleted Note in its list
     */
    public InternApplication deleteNote(int index) {
        List<Note> mutableList = new ArrayList<>(this.notes);
        mutableList.remove(index - 1);
        return new InternApplication(id, company, role, cycle, mutableList, status, deadline);
    }

    /**
     * Returns true if both applications have the same name.
     * This defines a weaker notion of equality between two applications.
     */
    public boolean isSameApplication(InternApplication otherInternApplication) {
        if (otherInternApplication == this) {
            return true;
        }

        return otherInternApplication != null
                && otherInternApplication.getCompany().equals(getCompany())
                && otherInternApplication.getRole().equals(getRole())
                && otherInternApplication.getCycle().equals(getCycle());
    }

    /**
     * Returns true if both applications have the same identity and data fields.
     * This defines a stronger notion of equality between two applications.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof InternApplication)) {
            return false;
        }

        // No comparing notes because notes is not a strict differentiating factor
        InternApplication otherInternApplication = (InternApplication) other;
        return company.equals(otherInternApplication.company)
                && role.equals(otherInternApplication.role)
                && cycle.equals(otherInternApplication.cycle);
    }

    @Override
    public int hashCode() {
        // No comparing notes because notes is not a strict differentiating factor
        return Objects.hash(company, role, cycle);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("company", company)
                .add("role", role)
                .add("cycle", cycle)
                .add("note", notes)
                .add("status", status)
                .add("deadline", deadline)
                .toString();
    }

    /**
     * Creates a copy of the current InternApplication object.
     *
     * @return A new InternApplication object with the same company, role, cycle, note, and status.
     */
    public InternApplication clone() {
        return new InternApplication(
                this.id,
                this.company,
                this.role,
                this.cycle,
                this.notes,
                this.status,
                this.deadline);
    }
}
