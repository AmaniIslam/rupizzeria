package files;

/**
 * The Doctor class extends the Provider class and implements the rate method.
 * It represents a doctor with a profile, location, specialty, and unique National Provider Identification (NPI).
 * The rate method returns the charge per visit based on the doctor's specialty.
 *
 * Author: Amani Islam
 */
public class Doctor extends Provider {

    private Specialty specialty; // Encapsulates the rate per visit based on specialty
    private String npi; // National Provider Identification unique to the doctor

    /**
     * Constructs a Doctor object with the specified profile, location, specialty, and NPI.
     *
     * @param profile The profile of the doctor.
     * @param location The location of the doctor.
     * @param specialty The specialty of the doctor which determines the rate per visit.
     * @param npi The National Provider Identification unique to the doctor.
     */
    public Doctor(Profile profile, Location location, Specialty specialty, String npi) {
        super(profile, location);
        this.specialty = specialty;
        this.npi = npi;
    }

    /**
     * Gets the specialty of the doctor.
     *
     * @return The doctor's specialty.
     */
    public Specialty getSpecialty() {
        return specialty;
    }

    /**
     * Gets the National Provider Identification (NPI) of the doctor.
     *
     * @return The NPI of the doctor.
     */
    public String getNPI() {
        return npi;
    }

    /**
     * Returns the rate per visit based on the doctor's specialty.
     *
     * @return The rate charged by the doctor for a visit.
     */
    public int rate() {
        return specialty.getCharge();
    }
}
