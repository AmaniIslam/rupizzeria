package files;

public interface PizzaFactory {

    Pizza createDeluxe(Size size);

    Pizza createMeatzza(Size size);

    Pizza createBBQChicken(Size size);

    Pizza createBuildYourOwn(Size size, List<Topping> toppings);
}

// public class ChicagoPizza implements PizzaFactory {
// }

// public class NYPizza implements PizzaFactory {
// }
