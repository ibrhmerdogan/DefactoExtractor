package parse;

import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ibrhm on 11.05.2017.
 */
public class ParseDocumentUrl {
    char aChar = '\"';
    //özelliklrin tutulduğu liste
    List<String> item = new ArrayList<String>();
    String propertyItem = "";
    //Ürün özelliklerine ek olarak ürün url sini de kaydeden parse class
    public List<String> parseDocument(Document getdata)
    {
        //Gelen document stringe cevrilir
        String data = getdata.toJson().toString();
        for(int i = 0;i<data.length(); i++)
        {
            if(data.charAt(i)==aChar)
            {
                i++;
                while (data.charAt(i) != aChar)
                {
                    propertyItem += data.charAt(i);
                    i++;
                }
                //item a veri kaydedelir
                item.add(propertyItem);
                //verinin üst üste yazılmamaı için
                propertyItem = "";
            }
        }

        return item;
    }
}
