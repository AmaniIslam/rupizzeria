package files;

/**
 * The Imaging class extends the Appointment class and includes a Radiology element.
 * It represents an imaging appointment, with an associated radiology room.
 *
 * Author: Amani Islam
 */
public class Imaging extends Appointment {

    private Radiology room; // The radiology room assigned for the imaging appointment

    /**
     * Constructs an Imaging object with the specified radiology room, date, timeslot, patient, and provider.
     *
     * @param room The radiology room assigned for the imaging appointment.
     * @param date The date of the imaging appointment.
     * @param timeslot The timeslot of the imaging appointment.
     * @param patient The patient attending the imaging appointment.
     * @param provider The provider assigned to the imaging appointment.
     */
    public Imaging(Radiology room, Date date, Timeslot timeslot, Person patient, Person provider) {
        super(date, timeslot, patient, provider);
        this.room = room;
    }

    /**
     * Gets the radiology room assigned for the imaging appointment.
     *
     * @return The radiology room.
     */
    public Radiology getRoom() {
        return room;
    }
}
