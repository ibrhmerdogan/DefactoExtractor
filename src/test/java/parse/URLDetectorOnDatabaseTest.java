package parse;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by ibrhm on 12.05.2017.
 */
//Url si verilen urunun databasede olup olmadığını test eder
public class URLDetectorOnDatabaseTest extends TestCase {

    String url="http://www.defacto.com.tr/trend-bluz-701331";
    @Test
    public void testgetUrl()
    {
        URLDetectorOnDatabase URLDetectorOnDatabase = new URLDetectorOnDatabase();
        Boolean data = true;
        assertEquals(URLDetectorOnDatabase.getData(url),data);

    }
    String urlFalse="http://www.defactor.com.tr/trend-bluz-701331";
    @Test
    public void testgetNotUrl()
    {
        URLDetectorOnDatabase URLDetectorOnDatabase = new URLDetectorOnDatabase();
        Boolean data = false;
        assertEquals(URLDetectorOnDatabase.getData(urlFalse),data);

    }


}