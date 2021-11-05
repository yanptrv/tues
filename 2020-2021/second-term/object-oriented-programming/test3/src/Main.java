import com.home.Kris.*;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {
        Restaurant victoria = new Restaurant("Victoria");

        Product cucumber = new Product("cucumber");
        Product tomato = new Product("tomato");
        Product lettuce = new Product("lettuce");
        Product chicker = new Product("chicken");

        Map<Product, Integer> prodcutsShopskaSalad = new HashMap<>();
        prodcutsShopskaSalad.put(cucumber, 2);
        prodcutsShopskaSalad.put(tomato, 3);
        prodcutsShopskaSalad.put(lettuce, 1);
        Dish shopskaSalad = new Dish("Shopska Salad", prodcutsShopskaSalad, 120);

        Map<Product, Integer> productsCaeserSalad = new HashMap<>();
        productsCaeserSalad.put(chicker, 1);
        productsCaeserSalad.put(tomato, 1);
        productsCaeserSalad.put(lettuce, 2);
        Dish caeserSalad = new Dish("Caeser Salad", productsCaeserSalad, 20);

        Chef chef = new Chef("Leo", victoria);
        Helper helper = new Helper("Kris",victoria);
        Cashier cashier = new Cashier("Lenna", victoria);

        victoria.addDish(caeserSalad);
        victoria.addDish(shopskaSalad);


    }
}
