package database.databaseOperations;

import junit.framework.TestCase;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ibrhm on 13.05.2017.
 */
//Girilern url nin sonun da dönen verilerin testi
public class GetDataToDAtabaseUrlTest extends TestCase {

    SimpleDateFormat format = new SimpleDateFormat("dd/M/yyyy");
    String date = format.format(new Date());
    String URL = "http://www.defacto.com.tr/desenli-kisa-bluz-360083";
    List<String> item = new ArrayList<String>();
    @Test
    public void testGetDataToDAtabaseUrl()
    {
        GetDataToDAtabaseUrl getDataToDAtabaseUrl =  new GetDataToDAtabaseUrl();
        getDataToDAtabaseUrl.getDataToDatabaseItemData(URL);
        item = getDataToDAtabaseUrl.item;
        assertEquals("date",item.get(0));
        assertEquals("12/5/2017",item.get(1));
        assertEquals("endPrice",item.get(2));
        assertEquals("8.99 TL",item.get(3));
        assertEquals("firstPrice",item.get(4));
        assertEquals("17.99 TL",item.get(5));


    }

}