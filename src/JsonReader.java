import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

class JsonReader {
    public static Map<String, List<String>> readConfig(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File configFile = new File(filePath);
        return mapper.readValue(configFile, Map.class);
    }

    public static List<String> readBasket(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File configFile = new File(filePath);
        return mapper.readValue(configFile, List.class);
    }
}