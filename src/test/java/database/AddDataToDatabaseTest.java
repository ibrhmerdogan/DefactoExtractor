package database;

import database.databaseOperations.GetDataToDAtabaseUrl;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ibrhm on 15.05.2017.
 */
//Database veri eklemenin testi
public class AddDataToDatabaseTest extends TestCase {
    String data ="onProductClick({ Id: 'G7698AZ17SPGR210', Name: 'V Yaka Basic Body',Category: 'Kadın', Variant: 'Gri', Position: '100', Gender:'Kadın',SalePrice:'17.99 TL',Discount:'True',ListPrice:'',PriceRange:'3', Badge:'' });";
    String url ="http//Defacto.com.tr/vyakabasic/051258";
    AddDataToDatabase addDataToDatabase = new AddDataToDatabase();
    GetDataToDAtabaseUrl getDataToDAtabaseUrl = new GetDataToDAtabaseUrl();
    List<String> item = new ArrayList<String>();
    @Test
    public void testAddDataToDatabase()
    {
     addDataToDatabase.addData(data,url);
     getDataToDAtabaseUrl.getDataToDatabaseItemData(url);
     item = getDataToDAtabaseUrl.item;
     assertEquals("date",item.get(0));
     assertEquals("15/5/2017",item.get(1));
     assertEquals("endPrice",item.get(2));
     assertEquals("17.99 TL",item.get(3));
     assertEquals("firstPrice",item.get(4));
     assertEquals("",item.get(5));
    }

}