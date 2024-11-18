package files;

public interface PizzaFactory {

    Pizza createDeluxe();

    Pizza createMeatzza();

    Pizza createBBQChicken();

    Pizza createBuildYourOwn();
}

public class ChicagoPizza implements PizzaFactory {
}

public class NYPizza implements PizzaFactory {
}
