package files;

/**
 * The Visit class represents a visit to an appointment. It is part of a linked list structure,
 * where each visit points to the next visit in the sequence.
 *
 * Each Visit object holds a reference to an Appointment and a reference to the next Visit,
 * allowing for the creation of a chain of visits.
 *
 * @author Ayaz Naeem
 */
public class Visit {

    private Appointment appointment;  // Reference to an appointment object
    private Visit next;               // Reference to the next visit in the linked list

    /**
     * Constructor to create a Visit object with a specific appointment.
     *
     * @param appointment The appointment associated with this visit.
     */
    public Visit(Appointment appointment) {
        this.appointment = appointment;
        this.next = null;  // Initially, the next visit is set to null
    }

    /**
     * Getter for the appointment associated with this visit.
     *
     * @return The appointment for this visit.
     */
    public Appointment getAppointment() {
        return appointment;
    }

    /**
     * Getter for the next visit in the list.
     *
     * @return The next visit, or null if this is the last visit.
     */
    public Visit getNext() {
        return next;
    }

    /**
     * Setter for the next visit in the list.
     *
     * @param next The next visit to link to this visit.
     */
    public void setNext(Visit next) {
        this.next = next;
    }

    /**
     * Converts the Visit object to a string representation.
     *
     * @return A string representation of the visit, which is the string representation of the associated appointment.
     */
    @Override
    public String toString() {
        return appointment.toString();
    }
}
