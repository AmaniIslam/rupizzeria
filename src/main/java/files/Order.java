package files;

import java.util.ArrayList;

/**
 * Represents an order containing a list of pizzas. Provides methods for adding and removing pizzas,
 * calculating the subtotal, tax, and total price of the order.
 *
 * @author Amani Islam
 * @author Aryan Rejo
 */
public class Order {

    private int number; // The order number
    private ArrayList<Pizza> pizzas; // The list of pizzas in the order
    private final double TAX_RATE = 0.0625; // The tax rate applied to the order

    /**
     * Constructs an Order with a given order number.
     *
     * @param number The unique number for this order.
     */
    public Order(int number) {
        this.number = number;
        this.pizzas = new ArrayList<>();
    }

    /**
     * Gets the number of the order.
     *
     * @return The order number.
     */
    public int getNumber() {
        return number;
    }

    /**
     * Gets the list of pizzas in the order.
     *
     * @return The list of pizzas.
     */
    public ArrayList<Pizza> getPizzas() {
        return pizzas;
    }

    /**
     * Adds a pizza to the order.
     *
     * @param pizza The pizza to add.
     */
    public void addPizza(Pizza pizza) {
        pizzas.add(pizza);
    }

    /**
     * Removes a pizza from the order.
     *
     * @param pizza The pizza to remove.
     */
    public void removePizza(Pizza pizza) {
        pizzas.remove(pizza);
    }

    /**
     * Calculates the subtotal for the order, which is the sum of the prices of all pizzas.
     *
     * @return The subtotal of the order.
     */
    public double getSubtotal() {
        double subtotal = 0;
        for (Pizza pizza : pizzas) {
            subtotal += pizza.price();
        }
        return subtotal;
    }

    /**
     * Calculates the tax for the order based on the subtotal.
     *
     * @return The tax amount for the order.
     */
    public double getTax() {
        return getSubtotal() * TAX_RATE;
    }

    /**
     * Calculates the total price for the order, including tax.
     *
     * @return The total price of the order.
     */
    public double getTotal() {
        return getSubtotal() + getTax();
    }

    /**
     * Returns the total price of the order.
     * This method is an alias for {@link #getTotal()}.
     *
     * @return The total price of the order.
     */
    public double getTotalPrice() {
        return getTotal();
    }
}
