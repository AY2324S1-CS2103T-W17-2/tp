package seedu.letsgethired.model.application;

/*
This entire class in general should not exist.
It is a crude way to implement the identity columns in real database tables.
To be deleted once we change from a json based data storage to a more appropriate database like MySQL or SQLite.
 */

/**
 * Represents an InternApplication's ID in the InternTracker.
 */
public class Id implements Comparable<Id> {
    private static int largestId = 0; // Initialise as 0
    private int id;

    private Id() {
        largestId = largestId + 1;
        id = largestId;
    }
    /**
     * Factory method to generate a useable Id object.
     */
    // Create a Factory method to make it less ambiguous about the usage for other developers.
    public static Id generateNewUseableId() {
        return new Id();
    }

    @Override
    public int compareTo(Id o) {
        if (this.id > o.id) {
            return 1;
        } else if (this.id == o.id) {
            return 0;
        } else {
            return -1;
        }
    }
}
