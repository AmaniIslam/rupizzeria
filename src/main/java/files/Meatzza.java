package files;

import java.util.ArrayList;

public class Meatzza extends Pizza {

    private final double SMALL = 17.99;
    private final double MEDIUM = 19.99;
    private final double LARGE = 21.99;
    
    public Meatzza(Size size) {
        super(size, Crust.STUFFED, createToppings());
    }

    private static ArrayList<Topping> createToppings() {
        ArrayList<Topping> toppings = new ArrayList<>();
        toppings.add(Topping.SAUSAGE);
        toppings.add(Topping.PEPPERONI);
        toppings.add(Topping.BEEF);
        toppings.add(Topping.HAM);
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
