package files;

/**
 * Enum representing different types of radiology imaging services, such as CATSCAN, ULTRASOUND, and XRAY.
 * The enum also includes a custom `toString()` method that returns the name of the service in lowercase.
 *
 * Author: Ayaz Naeem
 */
public enum Radiology {
    CATSCAN,      // CT scan
    ULTRASOUND,   // Ultrasound scan
    XRAY;         // X-ray scan

    /**
     * Returns the name of the radiology service in lowercase.
     * This method overrides the default `toString()` method to provide a more user-friendly output.
     *
     * @return The name of the radiology service in lowercase.
     */
    @Override
    public String toString() {
        return name().toLowerCase();  // Returns the name of the enum constant in lowercase
    }

    /**
     * Main method for demonstrating the usage of the Radiology enum.
     * It prints the selected radiology service and displays all available services.
     *
     * @param args Command line arguments (not used in this example).
     */
    public static void main(String[] args) {
        // Example of using the Radiology enum
        Radiology service = Radiology.CATSCAN;

        // Print the type of imaging service selected
        System.out.println("The imaging service selected is: " + service);

        // Display each available type of radiology service
        for (Radiology r : Radiology.values()) {
            System.out.println("Available service: " + r);
        }
    }
}
