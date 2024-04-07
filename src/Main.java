import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.err.println("Usage: java Main config.json basket.json");
            System.exit(1);
        }
        String configPath = args[0];
        String basketPath = args[1];

        List<String> items;
        try {
            items = JsonReader.readBasket(basketPath);
        }
        catch (IOException e){
            System.out.println("Unable to read " + basketPath);
            return;
        }

        try {
            BasketSplitter splitter = new BasketSplitter(configPath);

            Map<String, List<String>> delivery = splitter.split(items);

            for (Map.Entry<String, List<String>> entry : delivery.entrySet()) {
                String deliveryOption = entry.getKey();
                List<String> products = entry.getValue();
                System.out.println("Delivery option: " + deliveryOption);
                System.out.println("Products size: " + products.size());
                System.out.println("Products: " + products);
            }
        }
        catch (IOException e){
            System.out.println("Unable to read " + configPath);
            return;
        }
    }
}
