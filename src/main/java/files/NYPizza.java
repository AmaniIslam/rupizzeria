package files;

import java.util.ArrayList;

/**
 * Factory class for creating New York-style pizzas. Implements the PizzaFactory interface.
 * Provides methods for creating Deluxe, Meatzza, BBQ Chicken, and Build Your Own pizzas.
 *
 * @author Amani Islam
 * @author Aryan Rejo
 */
public class NYPizza implements PizzaFactory {

    /**
     * Creates a New York-style Deluxe pizza with the specified style and size.
     *
     * @param style The style of the pizza (e.g., New York).
     * @param size The size of the pizza (e.g., Small, Medium, Large).
     * @return A Deluxe pizza of New York style.
     */
    @Override
    public Pizza createDeluxe(PizzaStyle style, Size size) {
        return new Deluxe(style, size); // Create a New York-style Deluxe pizza with the given size
    }

    /**
     * Creates a New York-style Meatzza pizza with the specified style and size.
     *
     * @param style The style of the pizza (e.g., New York).
     * @param size The size of the pizza (e.g., Small, Medium, Large).
     * @return A Meatzza pizza of New York style.
     */
    @Override
    public Pizza createMeatzza(PizzaStyle style, Size size) {
        return new Meatzza(style, size); // Create a New York-style Meatzza pizza with the given size
    }

    /**
     * Creates a New York-style BBQ Chicken pizza with the specified style and size.
     *
     * @param style The style of the pizza (e.g., New York).
     * @param size The size of the pizza (e.g., Small, Medium, Large).
     * @return A BBQ Chicken pizza of New York style.
     */
    @Override
    public Pizza createBBQChicken(PizzaStyle style, Size size) {
        return new BBQChicken(style, size); // Create a New York-style BBQ Chicken pizza with the given size
    }

    /**
     * Creates a Build Your Own pizza with the specified style, size, and toppings.
     *
     * @param style The style of the pizza (e.g., New York).
     * @param size The size of the pizza (e.g., Small, Medium, Large).
     * @param toppings The list of toppings to add to the pizza.
     * @return A Build Your Own pizza of New York style.
     */
    @Override
    public Pizza createBuildYourOwn(PizzaStyle style, Size size, ArrayList<Topping> toppings) {
        return new BuildYourOwn(style, size, toppings); // Create a Build Your Own pizza with size and toppings
    }
}
