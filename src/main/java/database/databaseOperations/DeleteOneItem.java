package database.databaseOperations;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * Created by ibrhm on 10.05.2017.
 */
public class DeleteOneItem {

    //Tek bir url ile database den veri silme
    public void delete(String url)
    {
        MongoClient mongoClient = null;
        try {
            //DB bağlantısı
            mongoClient = new MongoClient("localhost", 27017);
            MongoDatabase database = mongoClient.getDatabase("DefactoDB");
            //Verinin db den silinmeisi
            database.getCollection("Records").deleteOne(new Document("item.url",url));

        } catch (Exception e) {
            System.out.println("An Error Occured! Error is:" + e);
        } finally {
            //Client kapatılır
            mongoClient.close();
        }
    }
}
