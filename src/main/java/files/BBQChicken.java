package files;

import java.util.ArrayList;

/**
 * Represents a BBQ Chicken pizza with predefined toppings and size options.
 * Extends the Pizza class to specify the crust and toppings for a BBQ Chicken pizza.
 *
 * @author Amani Islam
 * @author Aryan Rejo
 */
public class BBQChicken extends Pizza {

    // Price constants for different pizza sizes
    private final double SMALL = 14.99;
    private final double MEDIUM = 16.99;
    private final double LARGE = 19.99;

    /**
     * Constructs a BBQChicken pizza with the specified style and size.
     * The pizza is created with predefined toppings based on the BBQ Chicken style.
     *
     * @param style The style of the pizza (e.g., regular or Chicago).
     * @param size The size of the pizza (small, medium, or large).
     */
    public BBQChicken(PizzaStyle style, Size size) {
        super(style, size, getCrustBasedOnStyle(style), createToppings());
    }

    /**
     * Determines the crust type based on the pizza style.
     *
     * @param style The style of the pizza (e.g., Chicago or regular).
     * @return The type of crust (PAN for Chicago, THIN for others).
     */
    public static Crust getCrustBasedOnStyle(PizzaStyle style) {
        if (style == PizzaStyle.CHICAGO){
            return Crust.PAN;
        } else {
            return Crust.THIN;
        }
    }

    /**
     * Creates the list of toppings for the BBQ Chicken pizza.
     * This pizza includes BBQ chicken, green peppers, provolone cheese, and cheddar cheese.
     *
     * @return A list of toppings for the BBQ Chicken pizza.
     */
    private static ArrayList<Topping> createToppings() {
        ArrayList<Topping> toppings = new ArrayList<>();
        toppings.add(Topping.BBQ_CHICKEN);
        toppings.add(Topping.GREEN_PEPPER);
        toppings.add(Topping.PROVOLONE);
        toppings.add(Topping.CHEDDAR);
        return toppings;
    }

    /**
     * Calculates the price of the BBQChicken pizza based on the selected size.
     * The price is determined by the size of the pizza and does not include additional toppings
     * as the toppings are predefined.
     *
     * @return The price of the BBQChicken pizza, based on the selected size.
     */
    @Override
    public double price() {
        double basePrice = SMALL;
        if (getSize() == Size.MEDIUM) {
            basePrice = MEDIUM;
        } else if (getSize() == Size.LARGE) {
            basePrice = LARGE;
        }
        return basePrice;
    }
}
