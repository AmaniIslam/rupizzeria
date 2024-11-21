package files;

import java.util.ArrayList;

/**
 * A factory interface for creating various types of pizzas. This interface defines methods for
 * creating specific pizza types based on their style and size, as well as custom pizzas with
 * user-defined toppings.
 *
 * @author Amani Islam
 * @author Aryan Rejo
 */
public interface PizzaFactory {

    /**
     * Creates a Deluxe pizza based on the specified style and size.
     *
     * @param style The style of the pizza (e.g., Chicago, New York).
     * @param size The size of the pizza (e.g., small, medium, large).
     * @return A Deluxe pizza of the specified style and size.
     */
    Pizza createDeluxe(PizzaStyle style, Size size);

    /**
     * Creates a Meatzza pizza based on the specified style and size.
     *
     * @param style The style of the pizza (e.g., Chicago, New York).
     * @param size The size of the pizza (e.g., small, medium, large).
     * @return A Meatzza pizza of the specified style and size.
     */
    Pizza createMeatzza(PizzaStyle style, Size size);

    /**
     * Creates a BBQ Chicken pizza based on the specified style and size.
     *
     * @param style The style of the pizza (e.g., Chicago, New York).
     * @param size The size of the pizza (e.g., small, medium, large).
     * @return A BBQ Chicken pizza of the specified style and size.
     */
    Pizza createBBQChicken(PizzaStyle style, Size size);

    /**
     * Creates a Build Your Own pizza based on the specified style, size, and list of toppings.
     *
     * @param style The style of the pizza (e.g., Chicago, New York).
     * @param size The size of the pizza (e.g., small, medium, large).
     * @param toppings A list of toppings to be included on the pizza.
     * @return A custom Build Your Own pizza with the specified size, style, and toppings.
     */
    Pizza createBuildYourOwn(PizzaStyle style, Size size, ArrayList<Topping> toppings);
}
