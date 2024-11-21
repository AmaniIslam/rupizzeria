package files;

import java.util.ArrayList;

public class Order {

    private int number;  // Order number
    private ArrayList<Pizza> pizzas;  // List of pizzas in this order
    private final double TAX_RATE = 0.625; // Example: 7% tax rate

    public Order(int number) {
        this.number = number;
        this.pizzas = new ArrayList<>();
    }

    public int getNumber() {
        return number;
    }

    public ArrayList<Pizza> getPizzas() {
        return pizzas;
    }

    public void addPizza(Pizza pizza) {
        pizzas.add(pizza);
    }

    public void removePizza(Pizza pizza) {
        pizzas.remove(pizza);
    }

    // Get the total price of the order (subtotal only)
    public double getSubtotal() {
        double subtotal = 0;
        for (Pizza pizza : pizzas) {
            subtotal += pizza.price();
        }
        return subtotal;
    }

    // Calculate tax based on subtotal
    public double getTax() {
        return getSubtotal() * TAX_RATE;
    }

    // Calculate the total price (subtotal + tax)
    public double getTotal() {
        return getSubtotal() + getTax();
    }

    // Get the total price of the order
    public double getTotalPrice() {
        return getTotal();  // You can still use this if you prefer it to return the full total
    }
}