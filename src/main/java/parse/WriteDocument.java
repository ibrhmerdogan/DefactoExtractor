package parse;

import java.util.List;

/**
 * Created by ibrhm on 13.05.2017.
 */
//Gelen ürün listesinin consola yazdıran class
public class WriteDocument {
    //konsola yazdırımda fiyat indirimini kontrol etmek için
    String endPrice ="";
    public void writeDocument(List<String> item)
    {
        //gelen list index artırımı için
        int counter=0;
      try
      {


        if(item.size()<14)
        {
            //size 14 ten kucukse bu item içinde url bilgisi ve sadece bitane document bulunduğu anlamına geli
            // item parse edilir ve endPrice alınır
            endPrice = parse(item);
        }
        else
        {
            int size = item.size();
            //item.size 14 ten butuk olması item içinde 1 den fazla document olduğu anlamına gelir
            //item size 14 ten büyükse for döngüsü için 13 azaltırlır
            //ilk basta item de database den gelen id url gibi veriler var daha sonra document verileri vardır
            //Document verileri 7.indeksten sonra 6 sarli polarak sıralanır sizedan 13 cıkarıp 6 ya bölme sebebi budur
            size = size-13;
            counter=13;
            for(int i=0;i<(size/6);i++)
            {
                //en sonki Endprice alınır karsılastırma yapımı için
                endPrice = item.get(counter + 5);
                counter += 6;


            }

        }
        counter =0;
        if(item.size()<14)
        {
            //size 14 ten kucukse bu item içinde url bilgisi ve sadece bitane document bulunduğu anlamına gelir
            //En sonki end price veri yazdırıken yapılacak olan kısıtlma için writeDatya gönderilir
            writeData(endPrice,item);


        }
        else {
            //item.size 14 ten butuk olması item içinde 1 den fazla document olduğu anlamına gelir
            writeData(endPrice, item);
            int size = item.size();
            size = size - 13;
            counter = 13;
            //son fiyatalar karsılaştırılı ona göre consola veşr basılır
            if (!endPrice.isEmpty()) {
                for (int i = 0; i < (size / 6); i++)
                {
                    System.out.println(item.get(counter) + ":" + item.get(counter + 1));
                    System.out.println(item.get(counter + 2) + ":" + item.get(counter + 3));
                    System.out.println(item.get(counter + 4) + ":" + item.get(counter + 5));
                    counter += 6;
                }

            }

        }
      }catch (Exception e)
      {e.printStackTrace();}
    }
    //ALınan item deki ürünün son fiyatı döndurulur
    public String parse(List<String> item)
    {
        String endPriceOne ="";
        int counter =0;
        for (int i = 0; i < (item.size() / 12); i++)
        {
            if (!item.get(counter + 12).isEmpty())
            {
                endPriceOne = item.get(counter + 12);

            }

        }
        return endPriceOne;
    }
    //item içindeki url ve ilk document verileri  consola yazılır
    public void writeData(String endPrice1,List<String> item)
    {
        int counter =0;
        for (int i = 0; i < (item.size() / 12); i++)
        {
            //item size 14 ten buyukse bu birden fazla document olğunu gösterir
            //birden fazla document bulundugun da
            // item.get deki ürün özellik indeksleri değisir bu yüzden sade ilk 13 indeks consola yazılır
            if(i==0)
            {
                //son fiyat karsılaştırılması
                if (!endPrice1.isEmpty())
                {
                    System.out.println(item.get(counter + 4) + ":" + item.get(counter + 5));
                    System.out.println(item.get(counter + 7) + ":" + item.get(counter + 8));
                    System.out.println(item.get(counter + 9) + ":" + item.get(counter + 10));
                    System.out.println(item.get(counter + 11) + ":" + item.get(counter + 12));

                }
            }

        }
    }
}
