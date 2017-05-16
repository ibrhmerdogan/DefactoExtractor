package parse;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import junit.framework.TestCase;
import org.bson.Document;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

/**
 * Created by ibrhm on 13.05.2017.
 */
//Documentin urlli parse edilme testi
public class ParseDocumentUrlTest extends TestCase {
    String url = "http://www.defacto.com.tr/sporcu-atlet-666565";
    SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
    String date = sdf.format(new Date());
    ParseDocumentUrl parseDocumentUrl = new ParseDocumentUrl();
    List<String> item = new ArrayList<String>();
    Document trialDocument = new Document();
    MongoClient mongoClient = null;
    @Test
    public void testParseDoucumentUrl()
    {
        mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase database = mongoClient.getDatabase("DefactoDB");

        FindIterable<Document> iterable = database.getCollection("Records").find(eq("item.url", url));

        iterable.forEach(new Block<Document>() {
            public void apply(final Document document) {

                trialDocument = document;

            }
        });
        item = parseDocumentUrl.parseDocument(trialDocument);
        assertEquals(item.get(4).toString(),"url");
        assertEquals(item.get(5),"http://www.defacto.com.tr/sporcu-atlet-666565");
        assertEquals(item.get(7),"date");
        assertEquals(item.get(8),"12/5/2017");
        assertEquals(item.get(9),"endPrice");
        assertEquals(item.get(10),"19.99 TL");
        assertEquals(item.get(11),"firstPrice");
        assertEquals(item.get(12),"35.99 TL");

    }

}