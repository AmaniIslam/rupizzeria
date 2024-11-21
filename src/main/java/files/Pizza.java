package files;

import java.util.ArrayList;

/**
 * Represents a pizza with its style, size, crust, and toppings. The class is abstract and serves as a
 * base class for different pizza types. Provides methods to access and modify the pizza's properties,
 * as well as an abstract method to calculate the price of the pizza.
 *
 * @author Amani Islam
 * @author Aryan Rejo
 */
public abstract class Pizza {

    private ArrayList<Topping> toppings; // Toppings for the pizza
    private Crust crust; // Crust type for the pizza
    private Size size; // Size of the pizza
    private PizzaStyle style; // Style of the pizza (e.g., Chicago, New York)

    /**
     * Abstract method to calculate the price of the pizza based on its size, crust, and toppings.
     * Subclasses must implement this method to define the price logic.
     *
     * @return The price of the pizza.
     */
    public abstract double price();

    /**
     * Constructs a Pizza with the specified style, size, crust, and toppings.
     *
     * @param style The style of the pizza (e.g., Chicago, New York).
     * @param size The size of the pizza (e.g., small, medium, large).
     * @param crust The crust type of the pizza (e.g., pan, thin).
     * @param toppings The list of toppings for the pizza.
     */
    public Pizza(PizzaStyle style, Size size, Crust crust, ArrayList<Topping> toppings) {
        this.style = style;
        this.size = size;
        this.crust = crust;
        this.toppings = toppings;
    }

    /**
     * Gets the style of the pizza.
     *
     * @return The style of the pizza.
     */
    public PizzaStyle getStyle() {
        return style;
    }

    /**
     * Gets the size of the pizza.
     *
     * @return The size of the pizza.
     */
    public Size getSize() {
        return size;
    }

    /**
     * Gets the crust type of the pizza.
     *
     * @return The crust of the pizza.
     */
    public Crust getCrust() {
        return crust;
    }

    /**
     * Gets the list of toppings on the pizza.
     *
     * @return The list of toppings.
     */
    public ArrayList<Topping> getToppings() {
        return toppings;
    }

    /**
     * Sets the size of the pizza.
     *
     * @param size The new size of the pizza.
     */
    public void setSize(Size size) {
        this.size = size;
    }

    /**
     * Sets the crust type of the pizza.
     *
     * @param crust The new crust type.
     */
    public void setCrust(Crust crust) {
        this.crust = crust;
    }

    /**
     * Sets the list of toppings for the pizza.
     *
     * @param toppings The new list of toppings.
     */
    public void setToppings(ArrayList<Topping> toppings) {
        this.toppings = toppings;
    }

    /**
     * Sets the style of the pizza.
     *
     * @param style The new style of the pizza.
     */
    public void setStyle(PizzaStyle style) {
        this.style = style;
    }
}
