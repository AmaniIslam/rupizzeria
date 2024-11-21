package files;

import java.util.ArrayList;

public class BuildYourOwn extends Pizza {

    private final double TOPPING_PRICE = 1.69;
    private final double SMALL = 8.99;
    private final double MEDIUM = 10.99;
    private final double LARGE = 12.99;
    private ArrayList<Topping> toppings;

    public BuildYourOwn(PizzaStyle style, Size size, ArrayList<Topping> toppings) {
        super(style, size, getCrustBasedOnStyle(style), toppings);
        this.toppings = toppings;
    }

    public static Crust getCrustBasedOnStyle(PizzaStyle style) {
        if (style == PizzaStyle.CHICAGO){
            return Crust.PAN;
        } else {
            return Crust.HAND_TOSSED;
        }
    }

    @Override
    public double price() {
        double basePrice = SMALL;
        if (getSize() == Size.MEDIUM) {
            basePrice = MEDIUM;
        } else if (getSize() == Size.LARGE) {
            basePrice = LARGE;
        }
        if (getToppings() != null){
            basePrice += getToppings().size() * TOPPING_PRICE;
        }
        return basePrice; // Each topping adds $1.69
    }

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
