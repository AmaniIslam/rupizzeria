package files;

public class Deluxe extends Pizza {

    public Deluxe(Size size) {
        super(size, Crust.DEEP_DISH, createToppings());
    }

    private static List<Topping> createToppings() {
        List<Topping> toppings = new List<>();
        toppings.add(Topping.SAUSAGE);
        toppings.add(Topping.PEPPERONI);
        toppings.add(Topping.GREEN_PEPPER);
        toppings.add(Topping.ONION);
        toppings.add(Topping.MUSHROOM);
        return toppings;
    }

    @Override
    public double price() {
        double basePrice = 16.99;
        if (getSize() == Size.MEDIUM) {
            basePrice = 18.99;
        } else if (getSize() == Size.LARGE) {
            basePrice = 20.99;
        }
        return basePrice;
    }

}
