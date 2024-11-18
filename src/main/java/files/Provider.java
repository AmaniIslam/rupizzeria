package files;

/**
 * Abstract class representing a provider, such as a doctor or technician, with a profile,
 * location, and specialty. This class extends `Person` and serves as the base class for specific
 * provider types that define the `rate` method for determining the charge based on their specialty.
 *
 * Author: Ayaz Naeem
 */
public abstract class Provider extends Person {

    private Location location;  // Location of the provider

    /**
     * Constructs a Provider object with the specified profile and location.
     * This constructor calls the constructor of the superclass `Person` to initialize the profile.
     *
     * @param profile The profile of the provider (e.g., first name, last name, date of birth).
     * @param location The location where the provider works.
     */
    public Provider(Profile profile, Location location) {
        super(profile);  // Initialize profile using the constructor from the Person class
        this.location = location;
    }

    /**
     * Returns the location of the provider.
     *
     * @return The location of the provider.
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Abstract method that must be implemented by subclasses to calculate the rate charged
     * by the provider based on their specialty or role.
     *
     * @return The rate charged by the provider.
     */
    public abstract int rate();

    /**
     * Returns a string representation of the provider, including their profile and location.
     *
     * @return A string representation of the provider.
     */
    @Override
    public String toString() {
        return "[" + profile.toString() + "," + location.toString() + "]";
    }
}
