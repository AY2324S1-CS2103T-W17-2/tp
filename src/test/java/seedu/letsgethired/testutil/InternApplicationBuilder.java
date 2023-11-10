package seedu.letsgethired.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import seedu.letsgethired.model.application.Company;
import seedu.letsgethired.model.application.Cycle;
import seedu.letsgethired.model.application.Deadline;
import seedu.letsgethired.model.application.Id;
import seedu.letsgethired.model.application.InternApplication;
import seedu.letsgethired.model.application.Note;
import seedu.letsgethired.model.application.Role;
import seedu.letsgethired.model.application.Status;

/**
 * A utility class to help with building InternApplication objects.
 */
public class InternApplicationBuilder {
    public static final String DEFAULT_COMPANY = "Jane Street";
    public static final String DEFAULT_ROLE = "Intern";
    public static final String DEFAULT_CYCLE = "Summer 2021";
    public static final String DEFAULT_STATUS = "Pending";
    public static final String DEFAULT_NOTE = "Jane Street is the leading market maker in the APAC region";
    public static final String DEFAULT_DEADLINE = "24 Oct 2023";

    private Id id;
    private Company company;
    private Role role;
    private Cycle cycle;
    private Status status;
    private List<Note> note;
    private Deadline deadline;

    /**
     * Creates a {@code InternApplicationBuilder} with the default details.
     */
    public InternApplicationBuilder() {
        id = Id.generateNewUseableId();
        company = new Company(DEFAULT_COMPANY);
        role = new Role(DEFAULT_ROLE);
        cycle = new Cycle(DEFAULT_CYCLE);
        status = new Status(DEFAULT_STATUS);
        note = Arrays.stream(new Note[]{new Note(DEFAULT_NOTE)})
                .collect(Collectors.toCollection(ArrayList<Note>::new));
        deadline = new Deadline(DEFAULT_DEADLINE);
    }

    /**
     * Initializes the InternApplicationBuilder with the data of {@code internApplicationToCopy}.
     */
    public InternApplicationBuilder(InternApplication internApplicationToCopy) {
        id = internApplicationToCopy.getId();
        company = internApplicationToCopy.getCompany();
        role = internApplicationToCopy.getRole();
        cycle = internApplicationToCopy.getCycle();
        status = internApplicationToCopy.getStatus();
        note = internApplicationToCopy.getNotes();
        deadline = internApplicationToCopy.getDeadline();
    }

    /**
     * Sets the {@code Company} of the {@code InternApplication} that we are building.
     */
    public InternApplicationBuilder withCompany(String company) {
        this.company = new Company(company);
        return this;
    }

    /**
     * Sets the {@code Status} of the {@code InternApplication} that we are building.
     */
    public InternApplicationBuilder withStatus(String status) {
        this.status = new Status(status);
        return this;
    }

    /**
     * Sets the {@code Note} of the {@code InternApplication} that we are building.
     */
    public InternApplicationBuilder withNotes(String ... notesString) {
        ArrayList<Note> notes = new ArrayList<>();
        for (String note: notesString) {
            notes.add(new Note(note));
        }
        this.note = notes;
        return this;
    }

    /**
     * Sets the {@code Role} of the {@code InternApplication} that we are building.
     */
    public InternApplicationBuilder withRole(String role) {
        this.role = new Role(role);
        return this;
    }

    /**
     * Sets the {@code Cycle} of the {@code InternApplication} that we are building.
     */
    public InternApplicationBuilder withCycle(String cycle) {
        this.cycle = new Cycle(cycle);
        return this;
    }

    /**
     * Sets the {@code Deadline} of the {@code InternApplication} that we are building.
     */
    public InternApplicationBuilder withDeadline(String deadline) {
        this.deadline = new Deadline(deadline);
        return this;
    }

    public InternApplication build() {
        return new InternApplication(id, company, role, cycle, note, status, deadline);
    }
}
