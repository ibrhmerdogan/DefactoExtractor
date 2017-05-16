package parse;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ibrhm on 13.05.2017.
 */
//Sayfa parse edildikten sonraki gelen tek ürün özelliği oarse edilme testi
public class ParseStringTest extends TestCase {

    List<String> item = new ArrayList<String>();
    String data ="onProductClick({ Id: 'G6107AZ17SMBK28', Name: 'Skinny Denim Pantolon',Category: 'Kadın', Variant: 'Siyah', Position: '1', Gender:'Kadın',SalePrice:'39.99 TL',Discount:'True',ListPrice:'',PriceRange:'2', Badge:'' });";
    @Test
    public void testParseString()
    {
        ParseString parseString =  new ParseString();
        item = parseString.parseString(data);
        assertEquals("G6107AZ17SMBK28",item.get(0));
        assertEquals("Skinny Denim Pantolon",item.get(1));
        assertEquals("Kadın",item.get(2));
        assertEquals("39.99 TL",item.get(6));
        assertEquals("",item.get(8));

    }

}