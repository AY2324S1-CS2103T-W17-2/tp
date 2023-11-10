package seedu.letsgethired.model.application;

import static java.util.Objects.requireNonNull;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_CYCLE;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.letsgethired.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import seedu.letsgethired.logic.parser.Prefix;
import seedu.letsgethired.logic.parser.SortOrder;


/**
 * Compares two {@code InternApplication}s by specific fields.
 */
public class InternApplicationComparator implements Comparator<InternApplication> {
    /**
     * Comparators for sorting by specific fields.
     */
    public static final InternApplicationComparator COMPANY_COMPARATOR_DESCENDING =
            new InternApplicationComparator((a, b) -> b.getCompany().compareTo(a.getCompany()));
    public static final InternApplicationComparator COMPANY_COMPARATOR_ASCENDING =
            new InternApplicationComparator(Comparator.comparing(InternApplication::getCompany));
    public static final InternApplicationComparator ROLE_COMPARATOR_DESCENDING =
            new InternApplicationComparator((a, b) -> b.getRole().compareTo(a.getRole()));
    public static final InternApplicationComparator ROLE_COMPARATOR_ASCENDING =
            new InternApplicationComparator(Comparator.comparing(InternApplication::getRole));
    public static final InternApplicationComparator CYCLE_COMPARATOR_DESCENDING =
            new InternApplicationComparator((a, b) -> b.getCycle().compareTo(a.getCycle()));
    public static final InternApplicationComparator CYCLE_COMPARATOR_ASCENDING =
            new InternApplicationComparator(Comparator.comparing(InternApplication::getCycle));
    public static final InternApplicationComparator STATUS_COMPARATOR_DESCENDING =
            new InternApplicationComparator((a, b) -> b.getStatus().compareTo(a.getStatus()));
    public static final InternApplicationComparator STATUS_COMPARATOR_ASCENDING =
            new InternApplicationComparator(Comparator.comparing(InternApplication::getStatus));
    public static final InternApplicationComparator DEADLINE_COMPARATOR_DESCENDING =
            new InternApplicationComparator((a, b) -> b.getDeadline().compareTo(a.getDeadline()));
    public static final InternApplicationComparator DEADLINE_COMPARATOR_ASCENDING = new InternApplicationComparator(
            Comparator.comparing(InternApplication::getDeadline));
    public static final InternApplicationComparator DEFAULT_SORT_COMPARATOR = new InternApplicationComparator(
            Comparator.comparing(InternApplication::getId));

    private final List<Comparator<InternApplication>> comparators;

    private InternApplicationComparator(List<Comparator<InternApplication>> comparators) {
        this.comparators = comparators;
    }

    private InternApplicationComparator(Comparator<InternApplication> comparator) {
        this.comparators = List.of(comparator);
    }

    /**
     * Returns a {@code InternApplicationComparator} that compares by the given field in the given order.
     */
    public static InternApplicationComparator getComparator(Prefix prefix, SortOrder order) {
        requireNonNull(prefix);
        requireNonNull(order);

        if (prefix.equals(PREFIX_COMPANY)) {
            if (order.isAscending()) {
                return COMPANY_COMPARATOR_ASCENDING;
            } else {
                return COMPANY_COMPARATOR_DESCENDING;
            }
        }

        if (prefix.equals(PREFIX_ROLE)) {
            if (order.isAscending()) {
                return ROLE_COMPARATOR_ASCENDING;
            } else {
                return ROLE_COMPARATOR_DESCENDING;
            }
        }

        if (prefix.equals(PREFIX_CYCLE)) {
            if (order.isAscending()) {
                return CYCLE_COMPARATOR_ASCENDING;
            } else {
                return CYCLE_COMPARATOR_DESCENDING;
            }
        }

        if (prefix.equals(PREFIX_STATUS)) {
            if (order.isAscending()) {
                return STATUS_COMPARATOR_ASCENDING;
            } else {
                return STATUS_COMPARATOR_DESCENDING;
            }
        }

        if (prefix.equals(PREFIX_DEADLINE)) {
            if (order.isAscending()) {
                return DEADLINE_COMPARATOR_ASCENDING;
            } else {
                return DEADLINE_COMPARATOR_DESCENDING;
            }
        }

        return null;
    }


    /**
     * Creates a comparator that compares by the given comparators in order.
     * A List is used instead of a varargs parameter to avoid issues with type erasure.
     */
    public static InternApplicationComparator createCompositeComparator(List<InternApplicationComparator> comparators) {
        ArrayList<Comparator<InternApplication>> comparatorList = new ArrayList<>();

        for (InternApplicationComparator comparator : comparators) {
            comparatorList.addAll(comparator.comparators);
        }

        return new InternApplicationComparator(comparatorList);
    }


    @Override
    public int compare(InternApplication a, InternApplication b) {
        for (Comparator<InternApplication> comparator : comparators) {
            int result = comparator.compare(a, b);
            if (result != 0) {
                return result;
            }
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof InternApplicationComparator)) {
            return false;
        }

        InternApplicationComparator other = (InternApplicationComparator) o;
        // Order of comparators matters
        return comparators.equals(other.comparators);

    }
}
