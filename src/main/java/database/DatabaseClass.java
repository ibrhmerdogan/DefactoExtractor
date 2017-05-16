package database;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.List;

/**
 * Created by ibrhm on 8.05.2017.
 */
public class DatabaseClass {

    public void database(String url,List<Document> document)
    {
        MongoClient mongoClient = null;
        MongoDatabase database;
            try {
                //DB bağlantısı
               mongoClient = new MongoClient("localhost", 27017);
                database = mongoClient.getDatabase("DefactoDB");

                //Gelen document db yekaydedilir
               database.getCollection("Records").insertOne(
                       new Document("item",
                               new Document()
                                       .append("url", url)
                       )
                               .append("changeDatePrice",

                                               document
                               )
               );
           } catch (Exception e)
           {
               System.err.println("Bir Hata Meydana Geldi!");
               System.out.println("Hata" + e);
           }
           finally
           {
               //Cilent kapatılır
               mongoClient.close();
           }

    }
}
