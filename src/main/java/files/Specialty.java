package files;

/**
 * Enum class to represent different medical specialties, each with a specific charge.
 * Provides a method to retrieve the charge associated with each specialty.
 *
 * @author Ayaz Naeem
 */
public enum Specialty {
    FAMILY(250),
    PEDIATRICIAN(300),
    ALLERGIST(350);

    private final int charge;

    /**
     * Constructor to initialize the charge for each specialty.
     *
     * @param charge The charge for the specialty.
     */
    Specialty(int charge) {
        this.charge = charge;
    }

    /**
     * Returns the charge associated with the specialty.
     *
     * @return The charge for this specialty.
     */
    public int getCharge() {
        return charge;
    }

    /**
     * Returns the name of the specialty as a string.
     *
     * @return The name of the specialty.
     */
    @Override
    public String toString() {
        return this.name();
    }
}
