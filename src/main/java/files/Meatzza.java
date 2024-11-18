package files;

public class Meatzza extends Pizza {
    
    public Meatzza(Size size) {
        super(size, Crust.STUFFED, createToppings());
    }

    private static List<Topping> createToppings() {
        List<Topping> toppings = new List<>();
        toppings.add(Topping.SAUSAGE);
        toppings.add(Topping.PEPPERONI);
        toppings.add(Topping.BEEF);
        toppings.add(Topping.HAM);
        return toppings;
    }

    @Override
    public double price() {
        double basePrice = 17.99;
        if (getSize() == Size.MEDIUM) {
            basePrice = 19.99;
        } else if (getSize() == Size.LARGE) {
            basePrice = 21.99;
        }
        return basePrice;
    }
}
