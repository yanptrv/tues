import com.Home.Kris.Product;

public class Main {
    public static void main(String[] args) {
        Product product = new Product("Lettuce", 2, "asd");
            try {
                product.getUnit().isEmpty();
                System.out.println(product.getUnit().get());
            } catch (Exception e) {
                try {
                    throw new Exception("Unit is null can't return its value");
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        System.out.println(product.getName());
    }
}
