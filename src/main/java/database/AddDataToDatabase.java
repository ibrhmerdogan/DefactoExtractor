package database;

import com.google.gson.JsonArray;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import database.databaseOperations.DeleteOneItem;
import org.bson.Document;
import parse.URLDetectorOnDatabase;
import parse.ParseDocument;
import parse.ParseString;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

/**
 * Created by ibrhm on 4.05.2017.
 */
public class AddDataToDatabase {

    public JsonArray convertJson;

     Document doc =new Document();
     String price="";
     String priceEnd="";
    List<Document> itemDocument = new ArrayList<Document>();
    int index = 0;
    int counter=0;
    MongoClient mongoClient = null;

    DatabaseClass databaseClass = new DatabaseClass();
    public void addData(String data, String url)
    {
        //tarih formatı
        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
        String date = sdf.format(new Date());
        //data parse edildikten sonra item özellikleri listesi
        List<String> item = new ArrayList<String>();
        ParseString parseString =  new ParseString();
        //data parse edildi
        item = parseString.parseString(data);
        //classa gelen url nin databasede daha önce olup olmadığını kontrol ediyor
        URLDetectorOnDatabase parserData = new URLDetectorOnDatabase();
        Boolean get = parserData.getData(url);
        //database de url yoksa false döner
       if (!get) {
           try {
               //itemDocument birden fazla islem yapıldığın da farklı ürünleri üstüste yazmaması için temizlenir
                     itemDocument.clear();
                     //url si database de kayıtlı olmayan urun ozellikleri itemDocumente kaydedilir
                     itemDocument.add(new Document()
                       .append("date", date)
                       .append("endPrice", item.get(6))
                       .append("firstPrice", item.get(8)));
                    //url ve özellikleri gönderilen ürün database kaydedilir
                    databaseClass.database(url, itemDocument);
                   //data lar üst üste yazamamk için tekrar temizlenir
                    itemDocument.clear();
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
       }//Gelen url database varsa price karsılastırılması yapılmak üzere
       else {
           try
           {
               //data lar üst üste yazamamk için tekrar temizlenir
               itemDocument.clear();
           //DB bağlantısı
           mongoClient= new MongoClient("localhost", 27017);
           MongoDatabase database = mongoClient.getDatabase("DefactoDB");

           FindIterable<Document> iterable = database.getCollection("Records").find(eq("item.url", url));

           iterable.forEach(new Block<Document>() {
               public void apply(Document document) {
                       doc = document;
               }


           });

           ParseDocument parseDocument = new ParseDocument();
           List<String> itemParse = new ArrayList<String>();
           //gelen document parse edilir
           itemParse = parseDocument.parseDocument(doc);
           int size = (itemParse.size() / 5);
           for (int i = 0; i <size;  i++) {
               //Gelen parse edilen document yeniden document halıne getirilir
               itemDocument.add(new Document()
                       .append(itemParse.get(counter), itemParse.get(counter + 1))
                       .append(itemParse.get(counter + 2), itemParse.get(counter + 3))
                       .append(itemParse.get(counter + 4), itemParse.get(counter + 5)));
               //parse edilen document de price yeni gelen cei ile karsılastırılmak üzere yeni değisken eatanır
               price = itemParse.get(counter + 3);
               priceEnd = itemParse.get(counter + 5);
               //itemParse index artırımı
               counter = counter + 6;


           }
            //data da var olan price ile yni gelen price karsılastırılır
           if (!price.equals(item.get(6)) ) {
               //price farklı olduğunda veriler tekrar documente atılır
               itemDocument.add(new Document()
                       .append("date", date.toString())
                       .append("endPrice", item.get(6))
                       .append("firstPrice", item.get(8)));

               //database tekrar kayır edilir
               reRecord(url, itemDocument);
               itemDocument.clear();

           }
           //bir sonraki gelen veri için itemParse indexsinde kullanılan counter 0 yapılır
           counter = 0;
       }catch (Exception e)
           {
               e.printStackTrace();
           } finally {
               //cilient kapatılır
               mongoClient.close();
           }
              }

    }


    public void reRecord(String url,List<Document> itemDocument)
    {
        //kaydedilmek üzere database gelen ürün databse de varsa ve fiyat ta bi değisiklik varsa silinip yeni degerler eklenerek tekrar kaydedilir
        DeleteOneItem deleteOneItem =  new DeleteOneItem();
        deleteOneItem.delete(url);
        Document document = new Document();

        databaseClass.database(url,itemDocument);
        itemDocument.clear();
    }
}
