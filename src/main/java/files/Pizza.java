package files;

import java.util.ArrayList;

public abstract class Pizza {

    private ArrayList<Topping> toppings; //Topping is a Enum class
    private Crust crust; //Crust is a Enum class
    private Size size; //Size is a Enum class

    public abstract double price();

    // Constructor to initialize Pizza with size, crust, and toppings
    public Pizza(Size size, Crust crust, ArrayList<Topping> toppings) {
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

    public ArrayList<Topping> getToppings() {
        return toppings;
    }
}
