package files;

import java.util.ArrayList;

public class BuildYourOwn extends Pizza {

    private final double TOPPING_PRICE = 1.69;
    private final double SMALL = 8.99;
    private final double MEDIUM = 10.99;
    private final double LARGE = 12.99;
    private ArrayList<Topping> toppings;



    public BuildYourOwn(Size size, ArrayList<Topping> toppings) {
        super(size, Crust.PAN, toppings);
        this.toppings = toppings;
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
}
