package parse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ibrhm on 13.05.2017.
 */
public class ParseString {
    String word = "";
    char aChar = '\'';
    List<String> item = new ArrayList<String>();
    //Gelen strin dataı parse eder
    public List<String> parseString(String data)
    {
        for (int i = 0; i < data.length(); i++) {
        if (data.charAt(i) == aChar) {
            i++;
            while (data.charAt(i) != aChar) {
                word += data.charAt(i);
                i++;
            }
            item.add(word);
            // System.out.println(propertyItem);
            word = "";
        }
    }


        return  item;
    }

}
