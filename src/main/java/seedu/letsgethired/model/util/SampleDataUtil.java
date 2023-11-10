package seedu.letsgethired.model.util;

import java.util.ArrayList;
import java.util.List;

import seedu.letsgethired.model.InternTracker;
import seedu.letsgethired.model.ReadOnlyInternTracker;
import seedu.letsgethired.model.application.Company;
import seedu.letsgethired.model.application.Cycle;
import seedu.letsgethired.model.application.Deadline;
import seedu.letsgethired.model.application.Id;
import seedu.letsgethired.model.application.InternApplication;
import seedu.letsgethired.model.application.Note;
import seedu.letsgethired.model.application.Role;
import seedu.letsgethired.model.application.Status;

/**
 * Contains utility methods for populating {@code InternTracker} with sample data.
 */
public class SampleDataUtil {

    public static InternApplication[] getSampleInternApplications() {
        return new InternApplication[] {
            new InternApplication(
                    Id.generateNewUseableId(),
                    new Company("Jane Street"),
                    new Role("Economist"),
                    new Cycle("Summer 2022"),
                    getListOfNotes(new String[]{"Review economic models.", "Prepare for behavioral questions."}),
                    new Status("Interview"),
                    new Deadline("24 Oct 2023")),
            new InternApplication(
                    Id.generateNewUseableId(),
                    new Company("Netflix"),
                    new Role("Backend Developer"),
                    new Cycle("Winter 2024"),
                    getListOfNotes(
                            new String[]{"Research the company's tech stack.", "Team meeting scheduled next month."}),
                    new Status("Accepted"),
                    new Deadline("15 Nov 2023")),
            new InternApplication(
                    Id.generateNewUseableId(),
                    new Company("Apple"),
                    new Role("DevOps"),
                    new Cycle("Summer 2024"),
                    getListOfNotes(
                            new String[]{"Review Data Structure and Algorithm", "Interview upcoming next week."}),
                    new Status("Pending"),
                    new Deadline("24 Oct 2023")),
            new InternApplication(
                    Id.generateNewUseableId(),
                    new Company("Bytedance"),
                    new Role("Systems Integrator"),
                    new Cycle("Summer 2023"),
                    getListOfNotes(
                            new String[]{"Review Data Structure and Algorithm", "Interview upcoming next week."}),
                    new Status("Rejected"),
                    new Deadline("24 Oct 2023")),
            new InternApplication(
                    Id.generateNewUseableId(),
                    new Company("Tesla"),
                    new Role("Embedded Systems Engineer"),
                    new Cycle("Summer 2024"),
                    getListOfNotes(
                            new String[]{"Received the compensation breakdown.", "Need to discuss joining bonus."}),
                    new Status("Offered"),
                    new Deadline("01 Dec 2023")),
            new InternApplication(
                    Id.generateNewUseableId(),
                    new Company("Grab"),
                    new Role("Data intern"),
                    new Cycle("Summer 2023"),
                    getListOfNotes(new String[]{"Prepare for data processing questions."}),
                    new Status("Pending"),
                    new Deadline("24 Oct 2023")),
            new InternApplication(
                    Id.generateNewUseableId(),
                    new Company("Shoppee"),
                    new Role("Full Stack Developer"),
                    new Cycle("Winter 2024"),
                    getListOfNotes(new String[]{"Prepare for data processing questions."}),
                    new Status("Rejected"),
                    new Deadline("24 Oct 2023")),
            new InternApplication(
                    Id.generateNewUseableId(),
                    new Company("Stripe"),
                    new Role("Cyber security Intern"),
                    new Cycle("Winter 2024"),
                    getListOfNotes(new String[]{"Research on latest cybersecurity trends."}),
                    new Status("Pending"),
                    new Deadline("24 Oct 2023")),
            new InternApplication(
                    Id.generateNewUseableId(),
                    new Company("Spotify"),
                    new Role("Data Scientist"),
                    new Cycle("Winter 2024"),
                    getListOfNotes(new String[]{"Prepare for machine learning questions.", "Review Python libraries."}),
                    new Status("Pending"),
                    new Deadline("15 Dec 2023")),
            new InternApplication(
                    Id.generateNewUseableId(),
                    new Company("Amazon"),
                    new Role("Frontend Developer"),
                    new Cycle("Summer 2023"),
                    getListOfNotes(new String[]{"Prepare for machine learning questions.", "Review Python libraries."}),
                    new Status("Rejected"),
                    new Deadline("10 Jan 2024")),
            new InternApplication(
                    Id.generateNewUseableId(),
                    new Company("Facebook"),
                    new Role("UI UX Designer"),
                    new Cycle("Winter 2024"),
                    getListOfNotes(
                            new String[]{"Create a design portfolio.", "Look into company's design principles."}),
                    new Status("Pending"),
                    new Deadline("15 Jan 2024")),
            new InternApplication(
                    Id.generateNewUseableId(),
                    new Company("Airbnb"),
                    new Role("Mobile App Developer"),
                    new Cycle("Summer 2024"),
                    getListOfNotes(new String[]{"Prepare for Swift and Kotlin questions."}),
                    new Status("Interview"),
                    new Deadline("24 Jan 2024")),
            new InternApplication(
                    Id.generateNewUseableId(),
                    new Company("Oracle"),
                    new Role("Database Administrator"),
                    new Cycle("Winter 2024"),
                    getListOfNotes(new String[]{"Review SQL performance optimization."}),
                    new Status("Pending"),
                    new Deadline("01 Feb 2024")),
            new InternApplication(
                    Id.generateNewUseableId(),
                    new Company("Twitter"),
                    new Role("Social Media Manager"),
                    new Cycle("Summer 2023"),
                    getListOfNotes(new String[]{"Negotiate for a better package.", "Consider relocation options."}),
                    new Status("Offered"),
                    new Deadline("15 Feb 2024")),
            new InternApplication(
                    Id.generateNewUseableId(),
                    new Company("Slack"),
                    new Role("Product Manager"),
                    new Cycle("Winter 2024"),
                    getListOfNotes(new String[]{"Review agile methodologies.", "Look into company's product lineup."}),
                    new Status("Pending"),
                    new Deadline("24 Feb 2024")),
            new InternApplication(
                    Id.generateNewUseableId(),
                    new Company("Zoom"),
                    new Role("Network Engineer"),
                    new Cycle("Summer 2024"),
                    getListOfNotes(new String[]{"Brush up on OSI model.", "Research company's infrastructure."}),
                    new Status("Interview"),
                    new Deadline("10 Mar 2024")),
            new InternApplication(
                    Id.generateNewUseableId(),
                    new Company("Lyft"),
                    new Role("Transport Analyst"),
                    new Cycle("Winter 2024"),
                    getListOfNotes(
                            new String[]{"Review urban transportation dynamics.", "Prepare for case study round."}),
                    new Status("Accepted"),
                    new Deadline("20 Mar 2024")),
            new InternApplication(
                    Id.generateNewUseableId(),
                    new Company("NVIDIA"),
                    new Role("Graphics Researcher"),
                    new Cycle("Summer 2023"),
                    getListOfNotes(new String[]{"Review latest in ray tracing.", "Prepare for coding challenge."}),
                    new Status("Pending"),
                    new Deadline("30 Mar 2024")),
            new InternApplication(
                    Id.generateNewUseableId(),
                    new Company("IBM"),
                    new Role("Cloud Consultant"),
                    new Cycle("Winter 2024"),
                    getListOfNotes(
                            new String[]{"Understand the nuances of hybrid cloud.", "Prepare for group discussion."}),
                    new Status("Interview"),
                    new Deadline("01 Apr 2024")),
            new InternApplication(
                    Id.generateNewUseableId(),
                    new Company("SpaceX"),
                    new Role("Aerospace Engineer"),
                    new Cycle("Summer 2023"),
                    getListOfNotes(new String[]{"Revise propulsion systems.", "Look into company's recent missions."}),
                    new Status("Pending"),
                    new Deadline("10 Apr 2024"))
        };
    }

    private static List<Note> getListOfNotes(String[] notes) {
        List<Note> noteList = new ArrayList<>();
        for (String note : notes) {
            noteList.add(new Note(note));
        }
        return noteList;
    }

    public static ReadOnlyInternTracker getSampleInternTracker() {
        InternTracker sampleAb = new InternTracker();
        for (InternApplication sampleInternApplication : getSampleInternApplications()) {
            sampleAb.addApplication(sampleInternApplication);
        }
        return sampleAb;
    }
}
