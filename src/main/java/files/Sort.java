package files;



/**
 * A utility class that provides sorting functionality for appointments and providers using the selection sort algorithm.
 * It allows sorting based on various keys such as patient profile, date, provider profile, and provider location.
 *
 * @author Amani Islam
 */
public class Sort {

    /**
     * Performs a selection sort on an array of elements using a custom comparison function.
     *
     * @param <T> The type of elements to be sorted.
     * @param array The array of elements to be sorted.
     * @param compareFunction A functional interface used to compare two elements of type T.
     */
    public static <T> void selectionSort(T[] array, SortFunction<T> compareFunction) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                // Use the custom comparison function
                if (compareFunction.compare(array[j], array[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            // Swap the found minimum element with the first element
            T temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
    }

    /**
     * A functional interface that defines the comparison logic for sorting elements.
     *
     * @param <T> The type of elements to be compared.
     */
    public interface SortFunction<T> {

        /**
         * Compares two elements of type T.
         *
         * @param a The first element to compare.
         * @param b The second element to compare.
         * @return A negative integer, zero, or a positive integer as the first element is less than, equal to, or greater than the second.
         */
        int compare(T a, T b);
    }

    /**
     * Sorts a list of appointments based on various keys such as patient profile, date, provider profile, or provider location.
     *
     * @param appointments The list of appointments to be sorted.
     * @param providers The list of providers to be used for sorting by provider location.
     * @param key The key by which the appointments should be sorted:
     *            'p' - by patient profile,
     *            'd' - by date,
     *            'o' - by provider profile,
     *            'l' - by provider location.
     */
    public static void appointment(List<Appointment> appointments, List<Provider> providers, char key) {
        Appointment[] appointmentArray = new Appointment[appointments.size()];

        // Iterate over the list to fill the array
        for (int i = 0; i < appointments.size(); i++) {
            appointmentArray[i] = (Appointment) appointments.get(i);  // Assuming List has a get() method
        }

        switch (key) {
            case 'p': // Sort by patient
                selectionSort(appointmentArray, (a, b) -> a.getPatient().getProfile().compareTo(b.getPatient().getProfile()));
                break;
            case 'd': // Sort by date
                selectionSort(appointmentArray, (a, b) -> a.getDate().compareTo(b.getDate()));
                break;
            case 'o': // Sort by provider profile
                selectionSort(appointmentArray, (a, b) -> a.getProvider().getProfile().compareTo(b.getProvider().getProfile()));
                break;
            case 'l': // Sort by provider location
                selectionSort(appointmentArray, (a, b) -> {
                    String countyA = null;
                    String countyB = null;

                    for (Provider provider : providers) {
                        if (provider.getProfile().equals(a.getProvider().getProfile())) {
                            countyA = provider.getLocation().getCounty();
                            break;
                        }
                    }

                    for (Provider provider : providers) {
                        if (provider.getProfile().equals(b.getProvider().getProfile())) {
                            countyB = provider.getLocation().getCounty();
                            break;
                        }
                    }

                    return countyA.compareTo(countyB);
                });
                break;
            default:
                return;
        }
    }

    /**
     * Sorts a list of providers by their location (county).
     *
     * @param list The list of providers to be sorted.
     */
    public static void provider(List<Provider> list) {
        Provider[] providerArray = new Provider[list.size()];  // Convert List to array

        // Iterate over the list to copy elements to the array
        for (int i = 0; i < list.size(); i++) {
            providerArray[i] = list.get(i);  // Assuming List has a get() method
        }

        // Sort providers based on their location (county)
        selectionSort(providerArray, (a, b) -> a.getLocation().getCounty().compareTo(b.getLocation().getCounty()));
    }
}
