package files;

import java.util.ArrayList;

public interface PizzaFactory {

    Pizza createDeluxe(PizzaStyle style, Size size);

    Pizza createMeatzza(PizzaStyle style, Size size);

    Pizza createBBQChicken(PizzaStyle style, Size size);

    Pizza createBuildYourOwn(PizzaStyle style, Size size, ArrayList<Topping> toppings);
}