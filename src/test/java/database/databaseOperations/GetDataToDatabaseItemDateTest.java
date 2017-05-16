package database.databaseOperations;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ibrhm on 13.05.2017.
 */
//O gün ki indirime giren verilerin database den çekilmesinin testi
public class GetDataToDatabaseItemDateTest extends TestCase {

    List<String> item = new ArrayList<String>();
    @Test
    public void testGetDataToDatabaseItemDate()
    {
        GetDataToDatabaseItemDate getDataToDatabaseItemDate =  new GetDataToDatabaseItemDate();
        getDataToDatabaseItemDate.getDataToDatabaseItemData();
        item = getDataToDatabaseItemDate.item;
        if (item.size()<14)
        {
            assertEquals("url",item.get(4));
            assertEquals("http//Defacto.com.tr/vyakabasic/051258",item.get(5));
            assertEquals("16/5/2017",item.get(8));
            assertEquals("17.99 TL",item.get(10));
            assertEquals("",item.get(12));
        }else
            {
                assertEquals("url",item.get(4));
                assertEquals("http//Defacto.com.tr/vyakabasic/051258",item.get(5));
                assertEquals("15/5/2017",item.get(8));
                assertEquals("17.99 TL",item.get(10));
                assertEquals("",item.get(12));
            }
    }
}

