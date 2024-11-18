package files;

/**
 * Represents an appointment with a date, timeslot, patient, and provider.
 * Provides methods to compare, check availability, and generate string representations of an appointment.
 *
 * @author Amani Islam
 */
public class Appointment implements Comparable<Appointment> {

    private Date date;
    private Timeslot timeslot;
    private Person patient;
    private Person provider;

    /**
     * Constructs an Appointment with the specified date, timeslot, patient, and provider.
     *
     * @param date The date of the appointment.
     * @param timeslot The timeslot of the appointment.
     * @param patient The patient for this appointment.
     * @param provider The provider for this appointment.
     */
    public Appointment(Date date, Timeslot timeslot, Person patient, Person provider) {
        this.date = date;
        this.timeslot = timeslot;
        this.patient = patient;
        this.provider = provider;
    }

    /**
     * Gets the date of the appointment.
     *
     * @return The date of the appointment.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Gets the timeslot of the appointment.
     *
     * @return The timeslot of the appointment.
     */
    public Timeslot getTimeslot() {
        return timeslot;
    }

    /**
     * Gets the patient associated with the appointment.
     *
     * @return The patient for this appointment.
     */
    public Person getPatient() {
        return patient;
    }

    /**
     * Gets the provider associated with the appointment.
     *
     * @return The provider for this appointment.
     */
    public Person getProvider() {
        return provider;
    }

    /**
     * Compares this appointment to another appointment for equality.
     *
     * @param obj The object to compare with.
     * @return true if the appointments have the same date, timeslot, and patient; false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        Appointment temp = (Appointment) obj;
        return date.equals(temp.getDate())
                && timeslot.equals(temp.getTimeslot())
                && patient.equals(temp.getPatient());
    }

    /**
     * Checks if this appointment is available at the same time and provider as another appointment.
     *
     * @param obj The object to compare with.
     * @return true if the appointments share the same date, timeslot, and provider; false otherwise.
     */
    public boolean availablity(Object obj) {
        if (obj instanceof Appointment temp) {
            return date.equals(temp.date)
                    && timeslot.equals(temp.timeslot)
                    && provider.equals(temp.provider);
        }
        return false;
    }

    /**
     * Compares this appointment with another for sorting purposes.
     *
     * @param other The other appointment to compare with.
     * @return A negative integer, zero, or a positive integer as this appointment is less than, equal to, or greater than the other.
     */
    @Override
    public int compareTo(Appointment other) {
        int d = date.compareTo(other.date);
        if (d != 0) {
            return d;
        }
        int t = timeslot.compareTo(other.timeslot);
        if (t != 0) {
            return t;
        }
        int pa = patient.compareTo(other.patient);
        if (pa != 0) {
            return pa;
        }
        int pr = provider.compareTo(other.provider);
        if (pr != 0) {
            return pr;
        }
        return 0;
    }

    /**
     * Returns a string representation of the appointment.
     *
     * @return A string in the format: "date timeslot patient [provider]".
     */
    @Override
    public String toString() {
        return date.toString() + " " + timeslot.toString() + " " + patient.toString() + " " + "[" + provider.toString()
                + "]";
    }
}
