package files;

/**
 * The Timeslot class represents a time slot for appointments. It holds the hour and minute for each time slot.
 * The class also provides methods for comparing, converting to string, and selecting a specific time slot.
 * It maintains a static array of all available time slots.
 *
 * @author Ayaz Naeem
 */
public class Timeslot implements Comparable<Timeslot> {

    private int hour;
    private int minute;
    public static final int SLOT_COUNT = 12;  // Total number of available slots
    private static Timeslot[] allTimeslots = new Timeslot[12];  // Array to hold all available timeslots

    /**
     * Constructor to create a Timeslot object with specific hour and minute.
     *
     * @param var1 The hour of the timeslot.
     * @param var2 The minute of the timeslot.
     */
    public Timeslot(int var1, int var2) {
        this.hour = var1;
        this.minute = var2;
    }

    /**
     * Gets the hour of the timeslot.
     *
     * @return The hour of the timeslot.
     */
    public int getHour() {
        return this.hour;
    }

    /**
     * Gets the minute of the timeslot.
     *
     * @return The minute of the timeslot.
     */
    public int getMinute() {
        return this.minute;
    }

    /**
     * Chooses a Timeslot based on the slot number (1-based index).
     *
     * @param slot A string representing the slot number (1-based).
     * @return The Timeslot corresponding to the provided slot number.
     */
    public static Timeslot choose(String slot) {
        populateTimeslots();
        return Timeslot.getAllTimeslots()[Integer.parseInt(slot) - 1];
    }

    /**
     * Populates the static array of available timeslots.
     * The slots are predefined with specific times for appointments.
     */
    public static void populateTimeslots() {
        int[] var0 = new int[]{9, 9, 10, 10, 11, 11};  // Hours for AM slots
        int[] var1 = new int[]{0, 30, 0, 30, 0, 30};  // Minutes for AM slots
        int[] var2 = new int[]{14, 14, 15, 15, 16, 16};  // Hours for PM slots
        int[] var3 = new int[]{0, 30, 0, 30, 0, 30};  // Minutes for PM slots
        int var4 = 0;

        // Populate the allTimeslots array with predefined slots
        for (int var5 = 0; var5 < 6; ++var5) {
            allTimeslots[var4] = new Timeslot(var0[var5], var1[var5]);
            ++var4;
        }

        for (int var5 = 0; var5 < 6; ++var5) {
            allTimeslots[var4] = new Timeslot(var2[var5], var3[var5]);
            ++var4;
        }
    }

    /**
     * Gets the array of all available timeslots.
     *
     * @return The array of all timeslots.
     */
    public static Timeslot[] getAllTimeslots() {
        return allTimeslots;
    }

    /**
     * Checks whether this Timeslot is equal to another object.
     *
     * @param var1 The object to compare to.
     * @return True if the object is equal to this Timeslot, false otherwise.
     */
    @Override
    public boolean equals(Object var1) {
        if (this == var1) {
            return true;
        } else if (var1 != null && this.getClass() == var1.getClass()) {
            Timeslot var2 = (Timeslot) var1;
            return this.hour == var2.hour && this.minute == var2.minute;
        } else {
            return false;
        }
    }

    /**
     * Converts the Timeslot object to a string representation.
     *
     * @return A string representing the Timeslot in a "HH:mm AM/PM" format.
     */
    @Override
    public String toString() {
        String var1 = "AM";
        int var2 = this.hour;
        if (this.hour >= 12) {
            var1 = "PM";
            if (this.hour > 12) {
                var2 = this.hour - 12;  // Convert to 12-hour format
            }
        }

        return this.minute == 0 ? "" + var2 + ":00 " + var1 : "" + var2 + ":" + (this.minute < 10 ? "0" + this.minute : this.minute) + " " + var1;
    }

    /**
     * Compares this Timeslot with another Timeslot for ordering.
     *
     * @param var1 The Timeslot to compare to.
     * @return A negative integer, zero, or a positive integer as this Timeslot is less than, equal to, or greater than the specified Timeslot.
     */
    @Override
    public int compareTo(Timeslot var1) {
        return this.hour != var1.hour ? this.hour - var1.hour : this.minute - var1.minute;
    }

}
