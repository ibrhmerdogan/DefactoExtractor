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
 * Created by ibrhm on 12.05.2017.
 */
//Document parse testi
public class ParseDocumentTest extends TestCase {

    String url = "http://www.defacto.com.tr/trend-bluz-701331";
    SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
    String date = sdf.format(new Date());
     ParseDocument parseDocument = new ParseDocument();
     List<String> item = new ArrayList<String>();
     Document trialDocument = new Document();
     MongoClient mongoClient = null;
     @Test
    public void testparseDocumen()
    {    mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase database = mongoClient.getDatabase("DefactoDB");

        FindIterable<Document> iterable = database.getCollection("Records").find(eq("item.url", url));

        iterable.forEach(new Block<Document>() {
            public void apply(final Document document) {

                trialDocument = document;

            }
        });

        item = parseDocument.parseDocument(trialDocument);
        assertEquals(item.get(0).toString(),"date");
        assertEquals(item.get(1),"12/5/2017");
        assertEquals(item.get(2),"endPrice");
        assertEquals(item.get(3),"19.99 TL");
        assertEquals(item.get(4),"firstPrice");
        assertEquals(item.get(5),"");

    }



}