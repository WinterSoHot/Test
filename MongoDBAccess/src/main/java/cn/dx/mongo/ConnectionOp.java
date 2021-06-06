package cn.dx.mongo;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.function.Consumer;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/5/21
 */
public class ConnectionOp {
    public static void main(String[] args) {
        try (MongoClient client = MongoUtil.getClient()) {
            System.out.println("数据库信息");
            ArrayList<Document> dbs =
                    client.listDatabases().into(new ArrayList<>());
            dbs.forEach(System.out::println);

            System.out.println("tesetdb:集合信息");
            MongoDatabase database = client.getDatabase("tesetdb");
            ArrayList<String> collectionsName = database.listCollectionNames().into(new ArrayList<>());
            collectionsName.forEach(System.out::println);

            FindIterable<Document>
                    books = database.getCollection("cn.dx.mongo.Book").find();
            books.forEach((Consumer<Document>) document -> System.out.println(document.toJson()));

        }
    }
}
