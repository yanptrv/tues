import com.home.Kris.*;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Product cucumber = new Product("cucumber", 2, Unit.KILOGRAM);
        Product tomatoes = new Product("tomatoes", 3, Unit.KILOGRAM);
        Storage storage = new Storage();
        storage.addProduct(cucumber, 10);
        storage.addProduct(cucumber, 10);
        Map<Product, Double> shopskaSalad = new HashMap<>();
        shopskaSalad.put(cucumber, 2.0);
        shopskaSalad.put(tomatoes, 3.0);

        Dish salad = new Dish("shopska", shopskaSalad, 2);
        System.out.println(salad.getSalePrice());
        Map<Dish, Double> orderTable1 = new HashMap<>();
        orderTable1.put(salad, 2.0);
        Order table1 = new Order(orderTable1);
        System.out.println(table1.getId());
        Order table2 = new Order(orderTable1);
        System.out.println(table2.getId());

    }
}
