package files;

import java.util.ArrayList;

/**
 * Represents a Meatzza pizza with a specified style and size.
 * This class manages the toppings and price calculation for a Meatzza pizza.
 *
 * @author Amani Islam
 * @author Aryan Rejo
 */
public class Meatzza extends Pizza {

    /** Price for a small Meatzza pizza. */
    private final double SMALL = 17.99;

    /** Price for a medium Meatzza pizza. */
    private final double MEDIUM = 19.99;

    /** Price for a large Meatzza pizza. */
    private final double LARGE = 21.99;

    /**
     * Constructs a Meatzza pizza with the specified style and size.
     *
     * @param style The style of the pizza (e.g., Chicago, New York).
     * @param size The size of the pizza (e.g., Small, Medium, Large).
     */
    public Meatzza(PizzaStyle style, Size size) {
        super(style, size, getCrustBasedOnStyle(style), createToppings());
    }

    /**
     * Returns the crust type based on the pizza style.
     *
     * @param style The style of the pizza.
     * @return The crust type for the specified style.
     */
    public static Crust getCrustBasedOnStyle(PizzaStyle style) {
        if (style == PizzaStyle.CHICAGO){
            return Crust.STUFFED;
        } else {
            return Crust.HAND_TOSSED;
        }
    }

    /**
     * Creates the toppings for the Meatzza pizza.
     *
     * @return A list of toppings for the Meatzza pizza.
     */
    private static ArrayList<Topping> createToppings() {
        ArrayList<Topping> toppings = new ArrayList<>();
        toppings.add(Topping.SAUSAGE);
        toppings.add(Topping.PEPPERONI);
        toppings.add(Topping.BEEF);
        toppings.add(Topping.HAM);
        return toppings;
    }

    /**
     * Calculates the price of the Meatzza pizza based on its size.
     *
     * @return The price of the pizza.
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
