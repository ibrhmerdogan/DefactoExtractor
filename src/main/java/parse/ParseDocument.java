package parse;

import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ibrhm on 9.05.2017.
 */
public class ParseDocument {
    //Json için de gerekli olan verilerin bulunduğu kısımları kapsayan karekterler
    char aChar = '[';
    char aCharr = ']';
    char aCharrr = '\"';
    //parse edeilen document sonunda veri özellikleri  yazılan liste
    List<String> item = new ArrayList<String>();
    //ürün özlliklerini tutar tek tek
    String propertyItem = "";
    //Sadece ürün özelliklerinin parse edildiği class
    public List<String> parseDocument(Document getdata)
    {
        //Gelen documenti string yapar
        String data = getdata.toJson().toString();
        //String parse edilir
        for(int i = 0;i<data.length(); i++)
        {
            if(data.charAt(i) == aChar)
            {
                i++;
                while (data.charAt(i)!= aCharr)
                {
                     if(data.charAt(i)==aCharrr)
                        {
                          i++;

                         while (data.charAt(i)!=aCharrr && data.charAt(i)!= aCharr)
                             {
                                //Belirli özellikleri saglayan strin parcası kaydedilir
                                 propertyItem +=data.charAt(i);
                                  i++;

                            }
                            //ürün özellikleri item a atanır
                         item.add(propertyItem);

                        }
                        //üst üste yazmaması için temizlenir
                        propertyItem = "";
                        i++;
                }


            }

        }
        //ürün özelliklerini tutan liste geri döndurul
        return item;
    }
}
