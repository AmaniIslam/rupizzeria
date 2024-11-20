package files;

import java.util.ArrayList;

public class NYPizza implements PizzaFactory {

    @Override
    public Pizza createDeluxe(Size size) {
        return new Deluxe(size); // Create a New York-style Deluxe pizza with the given size
    }

    @Override
    public Pizza createMeatzza(Size size) {
        return new Meatzza(size); // Create a New York-style Meatzza pizza with the given size
    }

    @Override
    public Pizza createBBQChicken(Size size) {
        return new BBQChicken(size); // Create a New York-style BBQ Chicken pizza with the given size
    }

    @Override
    public Pizza createBuildYourOwn(Size size, ArrayList<Topping> toppings) {
        return new BuildYourOwn(size, toppings); // Create a Build Your Own pizza with size and toppings
    }
}
