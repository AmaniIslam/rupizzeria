package files;

/**
 * Technician class extends the Provider class and implements the rate method
 * to return the rate per visit specific to a technician.
 *
 * @author Amani Islam
 */
public class Technician extends Provider {

    private int ratePerVisit;

    /**
     * Constructor to initialize the Technician object with profile, location, and rate per visit.
     *
     * @param profile The profile of the technician, including personal information.
     * @param location The location where the technician works.
     * @param ratePerVisit The rate charged by the technician per visit.
     */
    public Technician(Profile profile, Location location, int ratePerVisit) {
        super(profile, location);
        this.ratePerVisit = ratePerVisit;
    }

    /**
     * Returns the rate per visit for the technician.
     *
     * @return The rate charged by the technician for a visit.
     */
    public int rate() {
        return ratePerVisit;
    }
}
