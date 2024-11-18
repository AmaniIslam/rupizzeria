package files;

public abstract class Pizza {

    private List<Topping> toppings; //Topping is a Enum class
    private Crust crust; //Crust is a Enum class
    private Size size; //Size is a Enum class

    public abstract double price();

    // Constructor to initialize Pizza with size, crust, and toppings
    public Pizza(Size size, Crust crust, List<Topping> toppings) {
        this.size = size;
        this.crust = crust;
        this.toppings = toppings;
    }

    public Size getSize() {
        return size;
    }

    public Crust getCrust() {
        return crust;
    }

    public List<Topping> getToppings() {
        return toppings;
    }
}
