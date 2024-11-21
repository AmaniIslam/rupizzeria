package files;

import java.util.ArrayList;

/**
 * Represents a "Build Your Own" pizza with customizable toppings and size.
 * The price is calculated based on the pizza size and the number of toppings.
 *
 * @author Amani Islam
 * @author Aryan Rejo
 */
public class BuildYourOwn extends Pizza {

    /** Price for each topping added. */
    private final double TOPPING_PRICE = 1.69;

    /** Price for a small pizza. */
    private final double SMALL = 8.99;

    /** Price for a medium pizza. */
    private final double MEDIUM = 10.99;

    /** Price for a large pizza. */
    private final double LARGE = 12.99;

    /**
     * Constructs a Build Your Own pizza with the specified style, size, and toppings.
     *
     * @param style The style of the pizza (e.g., New York or Chicago).
     * @param size The size of the pizza (Small, Medium, or Large).
     * @param toppings The list of toppings for the pizza.
     */
    public BuildYourOwn(PizzaStyle style, Size size, ArrayList<Topping> toppings) {
        super(style, size, getCrustBasedOnStyle(style), toppings);
    }

    /**
     * Determines the crust type based on the pizza style.
     *
     * @param style The style of the pizza.
     * @return The crust type for the pizza.
     */
    public static Crust getCrustBasedOnStyle(PizzaStyle style) {
        if (style == PizzaStyle.CHICAGO) {
            return Crust.PAN;  // Chicago-style pizza uses a pan crust
        } else {
            return Crust.HAND_TOSSED;  // New York-style pizza uses a hand-tossed crust
        }
    }

    /**
     * Calculates the price of the pizza based on its size and the number of toppings.
     *
     * @return The total price of the pizza.
     */
    @Override
    public double price() {
        double basePrice = SMALL;
        if (getSize() == Size.MEDIUM) {
            basePrice = MEDIUM;
        } else if (getSize() == Size.LARGE) {
            basePrice = LARGE;
        }

        if (getToppings() != null) {
            basePrice += getToppings().size() * TOPPING_PRICE;  // Add price for each topping
        }

        return basePrice;
    }

    /**
     * Main method for testing the Build Your Own pizza class.
     * This method runs several test cases to check the price calculation.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // Test Case 1: Test price with no toppings, small pizza size
        ArrayList<Topping> toppings = new ArrayList<>();
        BuildYourOwn pizza = new BuildYourOwn(PizzaStyle.NEW_YORK, Size.SMALL, toppings);
        double result = pizza.price();
        System.out.println("Test 1 - Price with no toppings, small pizza: " + result);  // Expected: 8.99

        // Test Case 2: Test price with 1 topping, medium pizza size
        toppings.add(Topping.PEPPERONI);  // Add one topping
        pizza = new BuildYourOwn(PizzaStyle.NEW_YORK, Size.MEDIUM, toppings);
        result = pizza.price();
        System.out.println("Test 2 - Price with 1 topping, medium pizza: " + result);  // Expected: 12.68

        // Test Case 3: Test price with multiple toppings, large pizza size
        toppings.add(Topping.MUSHROOM);  // Add two toppings
        toppings.add(Topping.ONION);    // Add three toppings
        pizza = new BuildYourOwn(PizzaStyle.NEW_YORK, Size.LARGE, toppings);
        result = pizza.price();
        System.out.println("Test 3 - Price with 3 toppings, large pizza: " + result);  // Expected: 18.06

        // Test Case 4: Test price with no toppings, large pizza size
        toppings.clear();  // Clear the toppings
        pizza = new BuildYourOwn(PizzaStyle.NEW_YORK, Size.LARGE, toppings);
        result = pizza.price();
        System.out.println("Test 4 - Price with no toppings, large pizza: " + result);  // Expected: 12.99

        // Test Case 5: Test price with 7 toppings, small pizza size
        toppings.clear();
        toppings.add(Topping.PEPPERONI);
        toppings.add(Topping.MUSHROOM);
        toppings.add(Topping.ONION);
        toppings.add(Topping.PROVOLONE);
        toppings.add(Topping.BBQ_CHICKEN);
        toppings.add(Topping.SAUSAGE);
        toppings.add(Topping.GREEN_PEPPER);  // Add seven toppings
        pizza = new BuildYourOwn(PizzaStyle.NEW_YORK, Size.SMALL, toppings);
        result = pizza.price();
        System.out.println("Test 5 - Price with 7 toppings, small pizza: " + result);  // Expected: 20.82
    }
}
