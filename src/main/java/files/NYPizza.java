package files;

import java.util.ArrayList;

public class NYPizza implements PizzaFactory {

    @Override
    public Pizza createDeluxe(PizzaStyle style, Size size) {
        return new Deluxe(style, size); // Create a New York-style Deluxe pizza with the given size
    }

    @Override
    public Pizza createMeatzza(PizzaStyle style, Size size) {
        return new Meatzza(style, size); // Create a New York-style Meatzza pizza with the given size
    }

    @Override
    public Pizza createBBQChicken(PizzaStyle style, Size size) {
        return new BBQChicken(style, size); // Create a New York-style BBQ Chicken pizza with the given size
    }

    @Override
    public Pizza createBuildYourOwn(PizzaStyle style, Size size, ArrayList<Topping> toppings) {
        return new BuildYourOwn(style, size, toppings); // Create a Build Your Own pizza with size and toppings
    }
}
