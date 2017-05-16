package database.databaseOperations;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import parse.ParseDocument;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

/**
 * Created by ibrhm on 11.05.2017.
 */
public class GetDataToDAtabaseUrl {

    MongoClient mongoClient = null;
    Document setDocument = new Document();
    //url ye göre gelen verinin tutulduğu list
    public List<String> item = new ArrayList<String>();
    public void getDataToDatabaseItemData(String url)
    {
        try {
            ParseDocument parseDocument = new ParseDocument();
            //DB bağlantısı
            mongoClient = new MongoClient("localhost", 27017);
            MongoDatabase database = mongoClient.getDatabase("DefactoDB");

            FindIterable<Document> iterable = database.getCollection("Records").find(eq("item.url", url));

            iterable.forEach(new Block<Document>() {
                public void apply(final Document document) {
                    //forEach aynı veride birden fazla döndüğü için sadece Document ataması yapıldı
                    setDocument = document;

                }
            });
            //document parse edildi
            item = parseDocument.parseDocument(setDocument);
            //Database dek i Json için de birden fazla document varsa bunu item.size/6(json içindeki tek documentte bulunna veri özelliği saıyısı) ile test ederiz
           //counter item indexsleri ilerletebilmek için
            int counter = 0;
            for (int i = 0; i < (item.size() / 6); i++) {
                System.out.println(item.get(counter) + ":" + item.get(counter + 1));
                System.out.println(item.get(counter + 2) + ":" + item.get(counter + 3));
                System.out.println(item.get(counter + 4) + ":" + item.get(counter + 5));
                counter += 6;
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally {
            mongoClient.close();
        }
    }
}
