package files;

import java.util.ArrayList;

public class Order {

    private int number;  // Order number
    private List<Pizza> pizzas;  // List of pizzas in this order

    public Order(int number) {
        this.number = number;
        this.pizzas = new List<>();
    }

    public int getNumber() {
        return number;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public void addPizza(Pizza pizza) {
        pizzas.add(pizza);
    }

    public void removePizza(Pizza pizza) {
        pizzas.remove(pizza);
    }

    public double getTotalPrice() {
        double total = 0;
        for (Pizza pizza : pizzas) {
            total += pizza.price();
        }
        return total;
    }
}
