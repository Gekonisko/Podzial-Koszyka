import org.javatuples.Pair;

import java.io.IOException;
import java.util.*;

public class BasketSplitter {
    private final Map<String, List<String>> config;

    public BasketSplitter(String absolutePathToConfigFile) throws IOException {
        this.config = JsonReader.readConfig(absolutePathToConfigFile);
    }

    public Map<String, List<String>> split(List<String> items) {
        List<String> basket = new ArrayList<>(items);
        Map<String, List<String>> deliverySplit = new HashMap<>();

        while (basket.size() > 0)
        {
            Map<String, List<String>> deliveryOptions = GetDeliveryGroups(basket);
            Pair<String, List<String>> largestDelivery = GetTheLargestDelivery(deliveryOptions);

            if(largestDelivery.getValue1().size() == 0)
                return deliverySplit;

            deliverySplit.put(largestDelivery.getValue0(), largestDelivery.getValue1());

            for (String deliveryItem : largestDelivery.getValue1()) {
                for (int i = 0; i < basket.size(); i++) {
                    if(deliveryItem.contains(basket.get(i)))
                    {
                        basket.remove(i);
                        break;
                    }
                }
            }
        }

        return deliverySplit;
    }

    public Pair<String, List<String>> GetTheLargestDelivery(Map<String, List<String>> options)
    {
        Pair<String, List<String>> largestDelivery = new Pair<>("", Arrays.asList());
        int maxSize = 0;
        for (Map.Entry<String, List<String>> entry : options.entrySet()) {
            List<String> delivery = entry.getValue();

            if(delivery.size() > maxSize)
            {
                largestDelivery = new Pair<>(entry.getKey(), entry.getValue());
                maxSize = delivery.size();
            }
        }
        return largestDelivery;
    }

    public Map<String, List<String>> GetDeliveryGroups(List<String> items) {
        Map<String, List<String>> deliveryGroups = new HashMap<>();

        for (String item : items) {
            for (Map.Entry<String, List<String>> entry : config.entrySet()) {
                String productName = entry.getKey();
                List<String> deliveryOptions = entry.getValue();
                if (item.contains(productName)) {
                    for (String option : deliveryOptions) {
                        deliveryGroups.computeIfAbsent(option, k -> new ArrayList<>()).add(item);
                    }
                    break;
                }
            }
        }

        return deliveryGroups;
    }
}
