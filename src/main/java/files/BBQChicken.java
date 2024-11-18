package files;

public class BBQChicken extends Pizza {
    public BBQChicken(Size size) {
        super(size, Crust.PAN, createToppings());
    }

    private static List<Topping> createToppings() {
        List<Topping> toppings = new List<>();
        toppings.add(Topping.BBQ_CHICKEN);
        toppings.add(Topping.GREEN_PEPPER);
        toppings.add(Topping.PROVOLONE);
        toppings.add(Topping.CHEDDAR);
        return toppings;
    }

    @Override
    public double price() {
        double basePrice = 14.99;
        if (getSize() == Size.MEDIUM) {
            basePrice = 16.99;
        } else if (getSize() == Size.LARGE) {
            basePrice = 19.99;
        }
        return basePrice;
    }
}
