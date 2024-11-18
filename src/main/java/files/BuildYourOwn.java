package files;

public class BuildYourOwn extends Pizza {
    public BuildYourOwn(Size size, List<Topping> toppings) {
        super(size, Crust.PAN, toppings);
    }

    @Override
    public double price() {
        double basePrice = 8.99;
        if (getSize() == Size.MEDIUM) {
            basePrice = 10.99;
        } else if (getSize() == Size.LARGE) {
            basePrice = 12.99;
        }
        return basePrice + getToppings().size() * 1.69; // Each topping adds $1.69
    }
}
