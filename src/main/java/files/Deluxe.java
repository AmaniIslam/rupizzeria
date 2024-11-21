package files;

import java.util.ArrayList;

/**
 * Represents a Deluxe pizza with a specified style and size.
 * This class manages the toppings and price calculation for a Deluxe pizza.
 *
 * @author Amani Islam
 * @author Aryan Rejo
 */
public class Deluxe extends Pizza {

    /** Price for a small Deluxe pizza. */
    private final double SMALL = 16.99;

    /** Price for a medium Deluxe pizza. */
    private final double MEDIUM = 18.99;

    /** Price for a large Deluxe pizza. */
    private final double LARGE = 20.99;

    /**
     * Constructs a Deluxe pizza with the specified style and size.
     *
     * @param style The style of the pizza (e.g., Chicago, New York).
     * @param size The size of the pizza (e.g., Small, Medium, Large).
     */
    public Deluxe(PizzaStyle style, Size size) {
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
            return Crust.DEEP_DISH;
        } else {
            return Crust.BROOKLYN;
        }
    }

    /**
     * Creates the toppings for the Deluxe pizza.
     *
     * @return A list of toppings for the Deluxe pizza.
     */
    private static ArrayList<Topping> createToppings() {
        ArrayList<Topping> toppings = new ArrayList<>();
        toppings.add(Topping.SAUSAGE);
        toppings.add(Topping.PEPPERONI);
        toppings.add(Topping.GREEN_PEPPER);
        toppings.add(Topping.ONION);
        toppings.add(Topping.MUSHROOM);
        return toppings;
    }

    /**
     * Calculates the price of the Deluxe pizza based on its size.
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
