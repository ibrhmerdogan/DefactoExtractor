package parse;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

/**
 * Created by ibrhm on 9.05.2017.
 */
public class URLDetectorOnDatabase {
    MongoClient mongoClient = null;
    Boolean bool=false;
    //GElen ürün URL sinin database olup olmadığını kontrol eder
    public Boolean getData(String url) {

        try {
            //Db bağlantısı
            mongoClient = new MongoClient("localhost", 27017);
            MongoDatabase database = mongoClient.getDatabase("DefactoDB");

            FindIterable<Document> iterable = database.getCollection("Records").find(eq("item.url", url));

            iterable.forEach(new Block<Document>() {
                public void apply(final Document document) {
                    //gelen url database de varsa
                    bool = true;
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //client kapatılır
            mongoClient.close();
        }
        //url database varsa true döner yoksa false
        return bool;
    }
}
