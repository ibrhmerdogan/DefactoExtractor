package readUrl;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import database.AddDataToDatabase;

import java.io.IOException;

/**
 * Created by ibrhm on 25.04.2017.
 */
public class ReadUrl {
    //TEst için gerekli url + data yı tutan değişlken
    public String bigData ="";
    //URL responsundan dönen veriyi tutar
    public Document doc;
    //Test için o anki veriyi tutur
    public String testData ="";
    //Test için o anki URI yi tutur
    public String testURI ="";
    //Gelen veriyi tutar
    public String data;
    //gelen ury tututar
    public String data2;
    int x = 0;
//verilen deger aralığında ki sayfa verilerini ceken class
    public void getDataToUrl(int pageNumber , int endPageNumber)
    {
        //verilerin database kaydedildiği class
        AddDataToDatabase addDataToDatabase = new AddDataToDatabase();

               try {
            // katogori kadin url responce için
            String url="http://www.defacto.com.tr/kadin";
            //her ürün için gereken ana uri
            String URI = "http://www.defacto.com.tr";
                   while (pageNumber<endPageNumber)
                {
                    //her sayfaya gitmek için url birleştirme
                  url = url+"?page="+pageNumber;
                  Connection.Response response = Jsoup.connect(url).timeout(30000).execute();
                  doc=response.parse();
                  //onProductClick ve href birlikte almak için cssQuery a tamamen alındı
                  Elements links = doc.select("a");
                    for (Element link : links)
                    {
                        //href i olan oncilickler alındı
                        data = link.attr("onclick");
                        if(data.contains("onProductClick")) {
                             data2 = link.attr("href");
                             URI +=data2;
                            if(!data.isEmpty())
                            {
                                //data parse edilip URI ile birlikte json a eklemek için
                                 addDataToDatabase.addData(data,URI);

                                bigData += data;
                                bigData +=URI;
                            }
                            //test için tuutla veriler
                             testData = data;
                             testURI = URI;
                             //verilerin üst üste yazılmaması için
                             data = "";
                             data2 ="";
                             //URI nin ilk kısmı üzerine her seferinde veri yazıldığı için ilk halıne yeniden getirilir
                             URI="http://www.defacto.com.tr";
                        }
                    }
                    //url üstüne veri yazıldığı için tekrar eski halıne getirilir
                    url = "http://www.defacto.com.tr/kadin";

                    //gelen ilk sayfa numarası okunduktan sonra diğer sayfaya gecmek için artırılır
                    pageNumber++;
                 }

            }catch(IOException e)
                 {
                    e.printStackTrace();
                 }

    }


}
