package files;

/**
 * Enum class that represents various locations, each consisting of a county and zip code.
 * The enum provides getter methods for accessing the county and zip code of each location.
 * It also includes a method to choose a location based on a string input.
 *
 * Author: Ayaz Naeem
 */
public enum Location {
    BRIDGEWATER("Somerset", "08807"),
    EDISON("Middlesex", "08817"),
    PISCATAWAY("Middlesex", "08854"),
    PRINCETON("Mercer", "08542"),
    MORRISTOWN("Morris", "07960"),
    CLARK("Union", "07066");

    private final String county; // The county of the location
    private final String zip;    // The zip code of the location

    /**
     * Constructor for the Location enum, which initializes the county and zip code.
     *
     * @param county The county of the location.
     * @param zip The zip code of the location.
     */
    Location(String county, String zip) {
        this.county = county;
        this.zip = zip;
    }

    /**
     * Gets the county of the location.
     *
     * @return The county of the location.
     */
    public String getCounty() {
        return county;
    }

    /**
     * Gets the zip code of the location.
     *
     * @return The zip code of the location.
     */
    public String getZip() {
        return zip;
    }

    /**
     * Chooses a location based on a string input.
     *
     * @param loc The name of the location to choose.
     * @return The corresponding Location enum value, or null if not found.
     */
    public Location choose(String loc) {
        if (loc == "BRIDGEWATER") {
            return BRIDGEWATER;
        } else if (loc == "EDISON") {
            return EDISON;
        } else if (loc == "PISCATAWAY") {
            return PISCATAWAY;
        } else if (loc == "PRINCETON") {
            return PRINCETON;
        } else if (loc == "MORRISTOWN") {
            return MORRISTOWN;
        } else if (loc == "CLARK") {
            return CLARK;
        } else {
            return null;
        }
    }

    /**
     * Returns a string representation of the location, including its name, county, and zip code.
     *
     * @return A string representing the location.
     */
    @Override
    public String toString() {
        return this.name() + ", " + county + " " + zip;
    }
}
