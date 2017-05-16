package readUrl;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by ibrhm on 13.05.2017.
 */
public class ReadUrlTest extends TestCase {

    //String dataItem ="onProductClick({ Id: 'G7408AZ17SMIN18', Name: 'Denim Salopet Elbise',Category: 'Kadın', Variant: 'Çivit Mavisi', Position: '1', Gender:'Kadın',SalePrice:'49.99 TL',Discount:'True',ListPrice:'',PriceRange:'9', Badge:'' });";
   // String URI = "http://www.defacto.com.tr/denim-salopet-elbise-725684";
    @Test
    public void testReadUrl()
    {
        ReadUrl readUrl =  new ReadUrl();
        readUrl.getDataToUrl(1,2);
        String bigData = readUrl.bigData;
        String URI = readUrl.testURI;
        String dataItem = readUrl.testData;

        assertEquals(bigData.contains(dataItem),true);
        assertEquals(bigData.contains(URI),true);


    }

}