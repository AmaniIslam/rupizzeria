package files;

/**
 * The Patient class represents a patient with a profile and a linked list of visits.
 * It includes a method to compute the total charges for the patient based on the visits.
 * The class also overrides `compareTo`, `equals`, and `toString` methods for comparison
 * and string representation of the patient object.
 *
 * Author: Ayaz Naeem
 */
public class Patient implements Comparable<Patient> {

    private Profile profile;  // The profile of the patient
    private Visit visits;     // Linked list of the patient's visits

    /**
     * Constructs a Patient object with a given profile and a list of visits.
     *
     * @param profile The profile of the patient.
     * @param visits The linked list of visits associated with the patient.
     */
    public Patient(Profile profile, Visit visits) {
        this.profile = profile;
        this.visits = visits;
    }

    /**
     * Computes the total charge for the patient by traversing through the list of visits.
     *
     * @return The total charge for the patient, or 0 if no visits are recorded.
     */
    public int charge() {
        if (this.visits == null) {
            return 0;  // No visits, so total charge is 0
        }

        int charges = 0;
        Visit current = this.visits;

        while (current != null) {
            Appointment appointment = current.getAppointment();
            // Patient provider = appointment.getProvider();
            // charges += provider.getSpecialty().getCharge();  // Add charge depending on provider's specialty
            current = current.getNext();  // Move to the next visit in the linked list
        }

        return charges;
    }

    /**
     * Compares this patient to another patient based on their profile.
     *
     * @param other The other patient to compare to.
     * @return A negative integer, zero, or a positive integer as this patient is less than,
     *         equal to, or greater than the specified patient.
     */
    @Override
    public int compareTo(Patient other) {
        return this.profile.compareTo(other.profile);
    }

    /**
     * Checks whether this patient is equal to another object.
     * The comparison is based on the patient's profile.
     *
     * @param obj The object to compare this patient to.
     * @return true if the object is a Patient and has the same profile, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Patient other = (Patient) obj;
        return this.profile.equals(other.profile);
    }

    /**
     * Returns a string representation of the Patient object, including the patient's profile
     * and the total charges for the patient.
     *
     * @return A string representation of the patient.
     */
    @Override
    public String toString() {
        return "Patient Profile: " + profile.toString() + ", Total Charge: $" + charge();
    }
}
