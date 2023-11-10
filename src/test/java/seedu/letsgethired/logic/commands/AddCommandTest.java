package seedu.letsgethired.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.letsgethired.testutil.Assert.assertThrows;
import static seedu.letsgethired.testutil.TypicalInternApplications.JANE_STREET;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.letsgethired.commons.core.GuiSettings;
import seedu.letsgethired.logic.commands.exceptions.CommandException;
import seedu.letsgethired.model.InternTracker;
import seedu.letsgethired.model.Model;
import seedu.letsgethired.model.ReadOnlyInternTracker;
import seedu.letsgethired.model.ReadOnlyUserPrefs;
import seedu.letsgethired.model.application.InternApplication;
import seedu.letsgethired.testutil.InternApplicationBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullInternApplication_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_internApplicationAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingInternApplicationAdded modelStub = new ModelStubAcceptingInternApplicationAdded();
        InternApplication validInternApplication = new InternApplicationBuilder().build();

        CommandResult commandResult = new AddCommand(validInternApplication).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validInternApplication),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validInternApplication), modelStub.internApplicationAdded);
    }

    @Test
    public void execute_duplicateInternApplication_throwsCommandException() {
        InternApplication validInternApplication = new InternApplicationBuilder().build();
        AddCommand addCommand = new AddCommand(validInternApplication);
        ModelStub modelStub = new ModelStubWithInternApplication(validInternApplication);

        assertThrows(CommandException.class,
                AddCommand.MESSAGE_DUPLICATE_INTERN_APPLICATION, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        InternApplication janeStreet = new InternApplicationBuilder().withCompany("Jane Street").build();
        InternApplication byteDance = new InternApplicationBuilder().withCompany("ByteDance").build();
        AddCommand addACommand = new AddCommand(janeStreet);
        AddCommand addBCommand = new AddCommand(byteDance);

        // same object -> returns true
        assertTrue(addACommand.equals(addACommand));

        // same values -> returns true
        AddCommand addACommandCopy = new AddCommand(janeStreet);
        assertTrue(addACommand.equals(addACommandCopy));

        // different types -> returns false
        assertFalse(addACommand.equals(1));

        // null -> returns false
        assertFalse(addACommand.equals(null));

        // different intern application -> returns false
        assertFalse(addACommand.equals(addBCommand));
    }

    @Test
    public void toStringMethod() {
        AddCommand addCommand = new AddCommand(JANE_STREET);
        String expected = AddCommand.class.getCanonicalName() + "{toAdd=" + JANE_STREET + "}";
        assertEquals(expected, addCommand.toString());
    }

    /**
     * A default model stub that have all the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getInternTrackerFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setInternTrackerFilePath(Path internBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addInternApplication(InternApplication internApplication) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setInternTracker(ReadOnlyInternTracker newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyInternTracker getInternTracker() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasInternApplication(InternApplication internApplication) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteInternApplication(InternApplication target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setInternApplication(InternApplication target, InternApplication editedInternApplication) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<InternApplication> getFilteredInternApplicationList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void getUnfilteredInternApplicationList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredInternApplicationList(Predicate<InternApplication> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredSortedInternApplicationList(Comparator<InternApplication> comparator) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void showAllInternApplications() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setCurrentInternApplication(InternApplication target) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public InternApplication getCurrentInternApplication() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean undoAction() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void clearInternshipApplications() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single intern application.
     */
    private class ModelStubWithInternApplication extends ModelStub {
        private final InternApplication internApplication;

        ModelStubWithInternApplication(InternApplication internApplication) {
            requireNonNull(internApplication);
            this.internApplication = internApplication;
        }

        @Override
        public boolean hasInternApplication(InternApplication internApplication) {
            requireNonNull(internApplication);
            return this.internApplication.isSameApplication(internApplication);
        }
    }

    /**
     * A Model stub that always accept the intern application being added.
     */
    private class ModelStubAcceptingInternApplicationAdded extends ModelStub {
        final ArrayList<InternApplication> internApplicationAdded = new ArrayList<>();

        @Override
        public boolean hasInternApplication(InternApplication internApplication) {
            requireNonNull(internApplication);
            return internApplicationAdded.stream().anyMatch(internApplication::isSameApplication);
        }

        @Override
        public void addInternApplication(InternApplication internApplication) {
            requireNonNull(internApplication);
            internApplicationAdded.add(internApplication);
        }

        @Override
        public ReadOnlyInternTracker getInternTracker() {
            return new InternTracker();
        }
    }

}
