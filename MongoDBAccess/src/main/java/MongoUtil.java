import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/5/21
 */
public class MongoUtil {
    private static String connectionString = System.getProperty("mongodb.url", "mongodb://127.0.0.1:27017");

    public static MongoClient getClient(String connectionString) {
        if (connectionString == null || connectionString.isEmpty()) {
            return getClient();
        }
        return MongoClients.create(connectionString);
    }

    public static MongoClient getClient() {
        return MongoClients.create(connectionString);
    }
}
