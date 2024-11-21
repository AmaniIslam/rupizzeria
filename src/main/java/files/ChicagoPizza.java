package files;

import java.util.ArrayList;

/**
 * Represents a factory for creating different types of Chicago-style pizzas.
 * Implements the PizzaFactory interface to create specific pizza types, such as Deluxe,
 * Meatzza, BBQ Chicken, and Build Your Own pizzas.
 *
 * @author Amani Islam
 * @author Aryan Rejo
 */
public class ChicagoPizza implements PizzaFactory {

    /**
     * Creates a Deluxe pizza with the specified style and size.
     *
     * @param style The style of the pizza (e.g., regular or Chicago).
     * @param size The size of the pizza (small, medium, or large).
     * @return A Deluxe pizza object created with the specified style and size.
     */
    @Override
    public Pizza createDeluxe(PizzaStyle style, Size size) {
        return new Deluxe(style, size); // Create a Deluxe pizza with the given size
    }

    /**
     * Creates a Meatzza pizza with the specified style and size.
     *
     * @param style The style of the pizza (e.g., regular or Chicago).
     * @param size The size of the pizza (small, medium, or large).
     * @return A Meatzza pizza object created with the specified style and size.
     */
    @Override
    public Pizza createMeatzza(PizzaStyle style, Size size) {
        return new Meatzza(style, size); // Create a Meatzza pizza with the given size
    }

    /**
     * Creates a BBQ Chicken pizza with the specified style and size.
     *
     * @param style The style of the pizza (e.g., regular or Chicago).
     * @param size The size of the pizza (small, medium, or large).
     * @return A BBQ Chicken pizza object created with the specified style and size.
     */
    @Override
    public Pizza createBBQChicken(PizzaStyle style, Size size) {
        return new BBQChicken(style, size); // Create a BBQ Chicken pizza with the given size
    }

    /**
     * Creates a Build Your Own pizza with the specified style, size, and toppings.
     *
     * @param style The style of the pizza (e.g., regular or Chicago).
     * @param size The size of the pizza (small, medium, or large).
     * @param toppings The list of toppings to be added to the pizza.
     * @return A Build Your Own pizza object created with the specified style, size, and toppings.
     */
    @Override
    public Pizza createBuildYourOwn(PizzaStyle style, Size size, ArrayList<Topping> toppings) {
        return new BuildYourOwn(style, size, toppings); // Create a Build Your Own pizza with size and toppings
    }
}
