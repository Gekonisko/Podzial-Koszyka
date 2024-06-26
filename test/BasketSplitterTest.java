import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

public class BasketSplitterTest {

    @Test
    public void ShouldSplit() throws IOException {
        BasketSplitter splitter = new BasketSplitter("config.json"); // Assuming config.json exists
        List<String> items = Arrays.asList(
                "Cocoa Butter",
                "Tart - Raisin And Pecan",
                "Table Cloth 54x72 White",
                "Flower - Daisies",
                "Fond - Chocolate",
                "Cookies - Englishbay Wht"
        );

        Map<String, List<String>> output = new HashMap<>()
        {{
            put("Pick-up point", Arrays.asList(
                    "Fond - Chocolate"));
            put("Courier", Arrays.asList(
                    "Cocoa Butter", "Tart - Raisin And Pecan", "Table Cloth 54x72 White",  "Flower - Daisies", "Cookies - Englishbay Wht"));
        }};

        Map<String, List<String>> deliverySplit = splitter.split(items);

        assertEquals(output.size(), deliverySplit.size());
        assertEquals(output, deliverySplit);
    }

    @Test
    public void ShouldGetDeliveryGroups() throws IOException {
        BasketSplitter splitter = new BasketSplitter("config.json");
        List<String> items = Arrays.asList(
                "Cocoa Butter",
                "Tart - Raisin And Pecan",
                "Table Cloth 54x72 White",
                "Flower - Daisies",
                "Fond - Chocolate",
                "Cookies - Englishbay Wht"
        );

        Map<String, List<String>> output = new HashMap<>()
        {{
            put("Pick-up point", Arrays.asList(
                    "Flower - Daisies", "Fond - Chocolate", "Cookies - Englishbay Wht"));
            put("Parcel locker", Arrays.asList(
                    "Cocoa Butter", "Table Cloth 54x72 White", "Cookies - Englishbay Wht"));
            put("In-store pick-up", Arrays.asList(
                    "Cocoa Butter", "Flower - Daisies"));
            put("Same day delivery", Arrays.asList(
                    "Cocoa Butter", "Flower - Daisies"));
            put("Courier", Arrays.asList(
                    "Cocoa Butter", "Tart - Raisin And Pecan", "Table Cloth 54x72 White",
                    "Flower - Daisies", "Cookies - Englishbay Wht"));
            put("Mailbox delivery", Arrays.asList(
                    "Cocoa Butter", "Tart - Raisin And Pecan", "Table Cloth 54x72 White", "Fond - Chocolate"));
            put("Next day shipping", Arrays.asList(
                    "Cocoa Butter", "Table Cloth 54x72 White"));
            put("Express Collection", Arrays.asList(
                    "Tart - Raisin And Pecan", "Flower - Daisies", "Fond - Chocolate", "Cookies - Englishbay Wht"));
        }};
        Map<String, List<String>> deliveryGroups = splitter.GetDeliveryGroups(items);

        assertNotNull(deliveryGroups);
        assertEquals(8, deliveryGroups.size());
        assertEquals(output, deliveryGroups);
    }

    @Test
    public void ShouldGetTheLargestDelivery() throws IOException {
        BasketSplitter splitter = new BasketSplitter("config.json");
        Map<String, List<String>> options = new HashMap<>();
        options.put("Option1", Arrays.asList("Product1", "Product2", "Product3"));
        options.put("Option2", Arrays.asList("Product4", "Product5"));
        options.put("Option3", Arrays.asList("Product6", "Product7", "Product8", "Product9"));

        Pair<String, List<String>> largestDelivery = splitter.GetTheLargestDelivery(options);

        assertEquals("Option3", largestDelivery.getValue0());
        assertEquals(Arrays.asList("Product6", "Product7", "Product8", "Product9"), largestDelivery.getValue1());
    }

    @Test
    public void ShouldGetTheLargestDelivery_EmptyInput() throws IOException {
        BasketSplitter splitter = new BasketSplitter("config.json");
        Map<String, List<String>> options = new HashMap<>();

        Pair<String, List<String>> largestDelivery = splitter.GetTheLargestDelivery(options);

        assertEquals("", largestDelivery.getValue0());
        assertEquals(0, largestDelivery.getValue1().size());
    }
}