package database.databaseOperations;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import parse.ParseDocumentUrl;
import parse.WriteDocument;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

/**
 * Created by ibrhm on 11.05.2017.
 */
//Bugüm indirimde olan ürünlerin görüntülendiği class
public class GetDataToDatabaseItemDate {
    //Date formatı
    SimpleDateFormat format = new SimpleDateFormat("dd/M/yyyy");
    String date = format.format(new Date());
    MongoClient mongoClient = null;
    //test için gerekli documentin string yapılmış hali
    public String testDocument;
    //databse den gelen documentin parse edildikten sonra urun bilgilerini tutar
    List<String> item = new ArrayList<String>();
    public void getDataToDatabaseItemData()
    {

         try
         {
             //DB bağlantısı
            mongoClient = new MongoClient("localhost", 27017);
            MongoDatabase database = mongoClient.getDatabase("DefactoDB");
            //bugunun tarihi verilerek db ye istek atılıyor
            FindIterable<Document> iterable = database.getCollection("Records").find(eq("changeDatePrice.date", date));
            iterable.forEach(new Block<Document>() {
                public void apply(final Document document) {
                    //db den gelen documentin parse edildiği class
                    ParseDocumentUrl parseDocument = new ParseDocumentUrl();
                    //test için gerekli documentin string yapılmış hali
                    testDocument = document.toJson().toString();
                    //documentin parse edilmiş hali item
                    item = parseDocument.parseDocument(document);
                    //parse edilen documentin consola yazılma asamsası
                    WriteDocument writeDocument = new WriteDocument();
                    writeDocument.writeDocument(item);

                }
            });
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
