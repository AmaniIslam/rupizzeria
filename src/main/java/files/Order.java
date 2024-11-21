package files;

import java.util.ArrayList;

public class Order {

    private int number;
    private ArrayList<Pizza> pizzas;
    private final double TAX_RATE = 0.0625;
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

    public double getSubtotal() {
        double subtotal = 0;
        for (Pizza pizza : pizzas) {
            subtotal += pizza.price();
        }
        return subtotal;
    }

    public double getTax() {
        return getSubtotal() * TAX_RATE;
    }

    public double getTotal() {
        return getSubtotal() + getTax();
    }

    public double getTotalPrice() {
        return getTotal();
    }
}