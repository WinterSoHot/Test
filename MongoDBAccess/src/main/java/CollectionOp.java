import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.Arrays;
import java.util.Random;
import java.util.function.Consumer;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/5/21
 */
public class CollectionOp {
    public static void main(String[] args) {
        try (MongoClient client = MongoUtil.getClient()) {
            MongoDatabase database = client.getDatabase("tesetdb");
            MongoCollection<Document> studentCollection = database.getCollection("student");
            Random rand = new Random();
            Document student = new Document("_id", new ObjectId());
            student.append("student_id", 10000d)
                    .append("class_id", 1d)
                    .append("scores", Arrays.asList(new Document("type", "exam").append("score", rand.nextDouble() * 100),
                            new Document("type", "quiz").append("score", rand.nextDouble() * 100),
                            new Document("type", "homework").append("score", rand.nextDouble() * 100),
                            new Document("type", "homework").append("score", rand.nextDouble() * 100)));
            studentCollection.insertOne(student);
            FindIterable<Document> documents = studentCollection.find();
            documents.forEach(new Consumer<Document>() {
                @Override
                public void accept(Document document) {
                    System.out.println(document);
                    System.out.println(document.toJson());
                }
            });
        }
    }
}
