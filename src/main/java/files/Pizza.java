package files;

import java.util.ArrayList;

public abstract class Pizza {

    private ArrayList<Topping> toppings; //Topping is a Enum class
    private Crust crust; //Crust is a Enum class
    private Size size; //Size is a Enum class
    private PizzaStyle style;

    public abstract double price();

    // Constructor to initialize Pizza with style, size, crust, and toppings
    public Pizza(PizzaStyle style, Size size, Crust crust, ArrayList<Topping> toppings) {
        this.style = style;
        this.size = size;
        this.crust = crust;
        this.toppings = toppings;
    }

    public PizzaStyle getStyle(){
        return style;
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

    public void setSize(Size size) { 
        this.size = size; 
    }

    public void setCrust(Crust crust) {
        this.crust = crust;
    }

    public void setToppings(ArrayList<Topping> toppings) {
        this.toppings = toppings;
    }
    
    public void setStyle(PizzaStyle style) {
        this.style = style;
    }
}
