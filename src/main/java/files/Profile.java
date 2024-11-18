package files;

/**
 * The Profile class represents an individual's profile, including their first name,
 * last name, and date of birth. It implements the `Comparable` interface to allow
 * comparison between profiles based on their first name, last name, and date of birth.
 *
 * Author: Amani Islam
 */
public class Profile implements Comparable<Profile> {

    private String fname;  // First name of the person
    private String lname;  // Last name of the person
    private Date dob;      // Date of birth of the person

    /**
     * Constructs a Profile object with the given first name, last name, and date of birth.
     *
     * @param fname The first name of the person.
     * @param lname The last name of the person.
     * @param dob   The date of birth of the person.
     */
    public Profile(String fname, String lname, Date dob) {
        this.dob = dob;
        this.fname = fname;
        this.lname = lname;
    }

    /**
     * Returns the first name of the person.
     *
     * @return The first name of the person.
     */
    public String getFname() {
        return fname;
    }

    /**
     * Returns the last name of the person.
     *
     * @return The last name of the person.
     */
    public String getLname() {
        return lname;
    }

    /**
     * Returns the date of birth of the person.
     *
     * @return The date of birth of the person.
     */
    public Date getDob() {
        return dob;
    }

    /**
     * Checks whether this profile is equal to another object.
     * The comparison is based on the first name, last name, and date of birth.
     *
     * @param obj The object to compare this profile to.
     * @return true if the object is a Profile and has the same first name, last name,
     * and date of birth, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Profile) {
            Profile temp = (Profile) obj;
            return this.dob.equals(temp.dob)
                    && this.fname.equalsIgnoreCase(temp.fname)
                    && this.lname.equalsIgnoreCase(temp.lname);
        }
        return false;
    }

    /**
     * Compares this profile to another profile.
     * The comparison is done first by last name, then by first name, and finally by date of birth.
     *
     * @param other The other profile to compare this profile to.
     * @return A negative integer, zero, or a positive integer as this profile is less than,
     * equal to, or greater than the specified profile.
     */
    @Override
    public int compareTo(Profile other) {
        // Compare by last name
        int l = lname.compareTo(other.lname);
        if (l > 0) {
            return 1;
        } else if (l < 0) {
            return -1;
        }

        // Compare by first name
        int f = fname.compareTo(other.fname);
        if (f > 0) {
            return 1;
        } else if (f < 0) {
            return -1;
        }

        // Compare by date of birth
        int d = dob.compareTo(other.dob);
        if (d > 0) {
            return 1;
        } else if (d < 0) {
            return -1;
        }

        return 0;
    }

    /**
     * Returns a string representation of the profile, including the first name,
     * last name, and date of birth.
     *
     * @return A string representation of the profile.
     */
    @Override
    public String toString() {
        return fname + " " + lname + " " + dob;
    }
}