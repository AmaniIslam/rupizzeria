package files;

import java.util.ArrayList;

public class Deluxe extends Pizza {
    private final double SMALL = 16.99;
    private final double MEDIUM = 18.99;
    private final double LARGE = 20.99;

    public Deluxe(PizzaStyle style, Size size) {
        super(style, size, getCrustBasedOnStyle(style), createToppings());
    }

    public static Crust getCrustBasedOnStyle(PizzaStyle style) {
        if (style == PizzaStyle.CHICAGO){
            return Crust.DEEP_DISH;
        } else {
            return Crust.BROOKLYN;
        }
    }

    private static ArrayList<Topping> createToppings() {
        ArrayList<Topping> toppings = new ArrayList<>();
        toppings.add(Topping.SAUSAGE);
        toppings.add(Topping.PEPPERONI);
        toppings.add(Topping.GREEN_PEPPER);
        toppings.add(Topping.ONION);
        toppings.add(Topping.MUSHROOM);
        return toppings;
    }

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
