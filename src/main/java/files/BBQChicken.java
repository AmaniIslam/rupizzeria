package files;

import java.util.ArrayList;

public class BBQChicken extends Pizza {

    private final double SMALL = 14.99;
    private final double MEDIUM = 16.99;
    private final double LARGE = 19.99;

    public BBQChicken(Size size) {
        super(size, Crust.PAN, createToppings());
    }

    private static ArrayList<Topping> createToppings() {
        ArrayList<Topping> toppings = new ArrayList<>();
        toppings.add(Topping.BBQ_CHICKEN);
        toppings.add(Topping.GREEN_PEPPER);
        toppings.add(Topping.PROVOLONE);
        toppings.add(Topping.CHEDDAR);
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
