package seedu.letsgethired.model;

import static java.util.Objects.requireNonNull;
import static seedu.letsgethired.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import seedu.letsgethired.commons.core.GuiSettings;
import seedu.letsgethired.commons.core.LogsCenter;
import seedu.letsgethired.model.application.InternApplication;
import seedu.letsgethired.model.application.InternApplicationComparator;

/**
 * Represents the in-memory model of the intern tracker data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final VersionedInternTracker internTracker;
    private final UserPrefs userPrefs;
    private FilteredList<InternApplication> filteredInternApplications;

    private final SortedList<InternApplication> filteredSortedInternApplications;


    /**
     * Initializes a ModelManager with the given internTracker and userPrefs.
     */
    public ModelManager(ReadOnlyInternTracker internTracker, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(internTracker, userPrefs);

        logger.fine("Initializing with intern tracker: " + internTracker + " and user prefs " + userPrefs);

        this.internTracker = new VersionedInternTracker(internTracker);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredInternApplications = new FilteredList<>(this.internTracker.getApplicationList());
        filteredSortedInternApplications = new SortedList<>(filteredInternApplications);
    }

    public ModelManager() {
        this(new InternTracker(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getInternTrackerFilePath() {
        return userPrefs.getInternTrackerFilePath();
    }

    @Override
    public void setInternTrackerFilePath(Path dataFilePath) {
        requireNonNull(dataFilePath);
        userPrefs.setInternTrackerFilePath(dataFilePath);
    }

    //=========== InternTracker ================================================================================
    @Override
    public void setInternTracker(ReadOnlyInternTracker internTracker) {
        this.internTracker.resetData(internTracker);
    }

    @Override
    public ReadOnlyInternTracker getInternTracker() {
        return internTracker;
    }

    @Override
    public boolean hasInternApplication(InternApplication internApplication) {
        requireNonNull(internApplication);
        return internTracker.hasApplication(internApplication);
    }

    @Override
    public void deleteInternApplication(InternApplication target) {
        internTracker.commit();
        internTracker.removeApplication(target);
    }

    @Override
    public void addInternApplication(InternApplication internApplication) {
        internTracker.commit();
        internTracker.addApplication(internApplication);
        showAllInternApplications();
    }

    @Override
    public void setInternApplication(InternApplication target, InternApplication editedInternApplication) {
        requireAllNonNull(target, editedInternApplication);
        internTracker.commit();
        internTracker.setApplication(target, editedInternApplication);
    }

    @Override
    public void clearInternshipApplications() {
        internTracker.commit();
        internTracker.clear();
    }

    //=========== Filtered Intern Application List Accessors =========================================================

    /**
     * Returns an unmodifiable view of the list of {@code InternApplication} backed by the internal list of
     * {@code versionedInternTracker}
     */
    @Override
    public ObservableList<InternApplication> getFilteredInternApplicationList() {
        return filteredSortedInternApplications;
    }

    @Override
    public void resetSortFilterProperty() {
        filteredInternApplications.setPredicate(x -> true);
        filteredSortedInternApplications.setComparator(InternApplicationComparator.DEFAULT_SORT_COMPARATOR);
    }

    @Override
    public void updateFilteredInternApplicationList(Predicate<InternApplication> predicate) {
        requireNonNull(predicate);
        filteredInternApplications.setPredicate(predicate);
    }

    @Override
    public void updateFilteredSortedInternApplicationList(Comparator<InternApplication> comparator) {
        requireNonNull(comparator);
        filteredSortedInternApplications.setComparator(comparator);
    }

    /**
     * Updates the internTracker to track the current selected application {@code target}
     */
    @Override
    public void setCurrentInternApplication(InternApplication target) {
        internTracker.setSelectedApplication(target);
    }

    /**
     * Gets the current application the internTracker is tracking
     */
    @Override
    public InternApplication getCurrentInternApplication() {
        return internTracker.getSelectedApplication();
    }

    /**
     * Undoes the previous add, edit or delete operation if available.
     * @return {@code true} if an action was undone successfully; {@code false} if there are no actions to undo.
     */

    @Override
    public boolean undoAction() {
        boolean isRestored = internTracker.undo();
        filteredInternApplications.setPredicate(PREDICATE_SHOW_ALL_APPLICATIONS);
        return isRestored;
    }

    @Override
    public void showAllInternApplications() {
        filteredInternApplications.setPredicate(PREDICATE_SHOW_ALL_APPLICATIONS);
        filteredSortedInternApplications.setComparator(COMPARATOR_SHOW_ALL_APPLICATIONS);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModelManager)) {
            return false;
        }

        ModelManager otherModelManager = (ModelManager) other;
        return internTracker.equals(otherModelManager.internTracker)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredInternApplications.equals(otherModelManager.filteredInternApplications)
                && filteredSortedInternApplications.equals(otherModelManager.filteredSortedInternApplications);
    }
}
