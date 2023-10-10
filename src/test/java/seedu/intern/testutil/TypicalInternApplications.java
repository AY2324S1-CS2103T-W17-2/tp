package seedu.intern.testutil;

import static seedu.intern.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_ROLE_AMY;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_ROLE_BOB;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_STATUS_AMY;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_STATUS_BOB;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.intern.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.intern.model.InternTracker;
import seedu.intern.model.application.InternApplication;

/**
 * A utility class containing a list of {@code InternApplication} objects to be used in tests.
 */
public class TypicalInternApplications {

    public static final InternApplication ALICE = new InternApplicationBuilder().withName("Alice Pauline")
            .withStatus("Pending").withEmail("alice@example.com")
            .withRole("SWE Intern")
            .withTags("friends").build();
    public static final InternApplication BENSON = new InternApplicationBuilder().withName("Benson Meier")
            .withStatus("Pending")
            .withEmail("johnd@example.com").withRole("Data Engineering Intern")
            .withTags("owesMoney", "friends").build();
    public static final InternApplication CARL = new InternApplicationBuilder().withName("Carl Kurz")
            .withRole("Full Stack Intern")
            .withEmail("heinz@example.com").withStatus("Pending").build();
    public static final InternApplication DANIEL = new InternApplicationBuilder().withName("Daniel Meier")
            .withRole("Back End Intern")
            .withEmail("cornelia@example.com").withStatus("Pending").withTags("friends").build();
    public static final InternApplication ELLE = new InternApplicationBuilder().withName("Elle Meyer")
            .withRole("Front End Intern")
            .withEmail("werner@example.com").withStatus("Pending").build();
    public static final InternApplication FIONA = new InternApplicationBuilder().withName("Fiona Kunz")
            .withRole("Web Dev Intern")
            .withEmail("lydia@example.com").withStatus("Pending").build();
    public static final InternApplication GEORGE = new InternApplicationBuilder().withName("George Best")
            .withRole("DevOps Intern")
            .withEmail("anna@example.com").withStatus("Pending").build();

    // Manually added
    public static final InternApplication HOON = new InternApplicationBuilder().withName("Hoon Meier")
            .withRole("8482424")
            .withEmail("stefan@example.com").withStatus("little india").build();
    public static final InternApplication IDA = new InternApplicationBuilder().withName("Ida Mueller")
            .withRole("8482131")
            .withEmail("hans@example.com").withStatus("chicago ave").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final InternApplication AMY = new InternApplicationBuilder().withName(VALID_NAME_AMY)
            .withRole(VALID_ROLE_AMY)
            .withEmail(VALID_EMAIL_AMY).withStatus(VALID_STATUS_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final InternApplication BOB = new InternApplicationBuilder().withName(VALID_NAME_BOB)
            .withRole(VALID_ROLE_BOB)
            .withEmail(VALID_EMAIL_BOB).withStatus(VALID_STATUS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalInternApplications() {} // prevents instantiation

    /**
     * Returns an {@code InternTracker} with all the typical persons.
     */
    public static InternTracker getTypicalInternTracker() {
        InternTracker ab = new InternTracker();
        for (InternApplication internApplication : getTypicalInternApplications()) {
            ab.addApplication(internApplication);
        }
        return ab;
    }

    public static List<InternApplication> getTypicalInternApplications() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}