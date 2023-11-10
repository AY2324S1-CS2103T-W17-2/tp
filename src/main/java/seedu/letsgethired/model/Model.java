package seedu.letsgethired.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.letsgethired.commons.core.GuiSettings;
import seedu.letsgethired.model.application.InternApplication;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<InternApplication> PREDICATE_SHOW_ALL_APPLICATIONS = unused -> true;

    /**
     * {@code Comparator} to reflect the original order of the list
     */
    Comparator<InternApplication> COMPARATOR_SHOW_ALL_APPLICATIONS = null;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' intern tracker file path.
     */
    Path getInternTrackerFilePath();

    /**
     * Sets the user prefs' intern tracker file path.
     */
    void setInternTrackerFilePath(Path dataFilePath);

    /**
     * Replaces intern tracker data with the data in {@code InternTracker}.
     */
    void setInternTracker(ReadOnlyInternTracker internTracker);

    /** Returns the InternTracker */
    ReadOnlyInternTracker getInternTracker();

    /**
     * Returns true if an intern application with the same identity
     * as {@code InternApplication} exists in the intern tracker.
     */
    boolean hasInternApplication(InternApplication internApplication);

    /**
     * Deletes the given intern application.
     * The intern application must exist in the intern tracker.
     */
    void deleteInternApplication(InternApplication target);

    /**
     * Adds the given intern application.
     * {@code InternApplication} must not already exist in the intern tracker.
     */
    void addInternApplication(InternApplication internApplication);

    /**
     * Replaces the given intern application {@code target} with {@code editedInternApplication}.
     * {@code target} must exist in the intern tracker.
     * The intern application {@code editedInternApplication} must not be the same
     * as another existing intern application in the intern tracker.
     */
    void setInternApplication(InternApplication target, InternApplication editedInternApplication);

    /** Returns an unmodifiable view of the filtered intern application list */
    ObservableList<InternApplication> getFilteredInternApplicationList();

    /**
     * Resets the intern application list to its default order
     */
    void resetSortFilterProperty();

    /**
     * Updates the filter of the filtered intern application list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredInternApplicationList(Predicate<InternApplication> predicate);

    /**
     * Updates the comparator of the filtered sorted intern application list to sort by the given {@code comparator}.
     *
     * @throws NullPointerException if {@code comparator} is null.
     */
    void updateFilteredSortedInternApplicationList(Comparator<InternApplication> comparator);

    /**
     * Shows all the applications in their original order.
     */
    void showAllInternApplications();

    /**
     * Sets the selected application to {@code target}.
     * {@code target} must exist in the intern tracker or be Null.
     */
    void setCurrentInternApplication(InternApplication target);

    /**
     * Retrieves the current internApplication being tracked
     */
    InternApplication getCurrentInternApplication();

    /**
     * Undoes the previous CRUD (Create, Read, Update, Delete) operation if available.
     * @return {@code true} if an action was undone successfully; {@code false} if there are no actions to undo.
     */
    boolean undoAction();

    /**
     * Deletes all existing internship applications
     */
    void clearInternshipApplications();
}
