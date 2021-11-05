import com.home.Kris.Shoe;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Set<Shoe> shoes = new LinkedHashSet<>();

        shoes.add(new Shoe(2,"green","Adidas"));
        shoes.add(new Shoe(1,"red","Nike"));
        shoes.add(new Shoe(1,"red","Nike"));
        System.out.println("Linked hash set: ");
        for (Shoe i : shoes) {
            System.out.println(i);
        }

        Map<Shoe, Shoe> shoes2 = new LinkedHashMap<>();

        Shoe s1 = new Shoe(48,"Nike","Black");
        Shoe s2 = new Shoe(48,"Nike","Black");
        Shoe s3 = new Shoe(48,"Nike","Green");
        shoes2.put(s1, s1);
        shoes2.put(s2, s2);
        shoes2.put(s3, s3);
        System.out.println("Hash map:");
        for(Map.Entry<Shoe, Shoe> s: shoes2.entrySet()) {
            System.out.println(s.getValue());
        }
    }
}
