package files;

/**
 * The Person class represents a person with a profile and includes methods for comparing
 * and printing the person's information.
 * It implements the `Comparable` interface to compare persons based on their profile.
 *
 * Author: Amani Islam
 */
public class Person implements Comparable<Person> {

    protected Profile profile;  // The profile associated with the person

    /**
     * Constructs a Person object with a given profile.
     *
     * @param profile The profile of the person.
     */
    public Person(Profile profile) {
        this.profile = profile;
    }

    /**
     * Returns the profile associated with the person.
     *
     * @return The person's profile.
     */
    public Profile getProfile() {
        return profile;
    }

    /**
     * Compares this person to another person based on their profile.
     *
     * @param other The other person to compare to.
     * @return A negative integer, zero, or a positive integer as this person is less than,
     *         equal to, or greater than the specified person.
     */
    @Override
    public int compareTo(Person other) {
        return this.profile.compareTo(other.getProfile());
    }

    /**
     * Checks whether this person is equal to another object.
     * The comparison is based on the person's profile.
     *
     * @param obj The object to compare this person to.
     * @return true if the object is a Person and has the same profile, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        Person temp = (Person) obj;
        return temp.getProfile().equals(profile);
    }

    /**
     * Returns a string representation of the Person object, including the person's profile.
     *
     * @return A string representation of the person.
     */
    @Override
    public String toString() {
        return profile.toString();
    }
}
