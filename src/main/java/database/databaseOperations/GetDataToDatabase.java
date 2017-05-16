package database.databaseOperations;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * Created by ibrhm on 9.05.2017.
 */
public class
GetDataToDatabase {

    MongoClient mongoClient = null;
    int x=0;
    //Tüm verileri consola yazdırır
    public void getData()
    {
        //db bağlantısı
        mongoClient = new MongoClient( "localhost" , 27017 );
        MongoDatabase database = mongoClient.getDatabase("DefactoDB");

        FindIterable<Document> iterable = database.getCollection("Records").find();

        iterable.forEach(new Block<Document>(){
            public void apply(final Document document){
                //db den gelen veriyi consola yazdırır
              System.out.println(document.toJson().toString());
              //kaç veri olduğunu saydırmak için
              x++;
              System.out.println(x);

            }
        });

    }
}
