package main;

import database.databaseOperations.GetDataToDAtabaseUrl;
import database.databaseOperations.GetDataToDatabaseItemDate;
import tread.ThreadClass;

/**
 * Created by ibrhm on 2.05.2017.
 */

public class Main {

    public static void main(String [ ] args)
    {
        String url = "";
        //Scanner scanner = new Scanner(System.in);
        GetDataToDAtabaseUrl getDataToDAtabaseUrl =  new GetDataToDAtabaseUrl();
        GetDataToDatabaseItemDate getDataToDatabaseDate =  new GetDataToDatabaseItemDate();
        //url =scanner.nextLine();
        //girilen  url den urun özelliklerine ulasılır
        //getDataToDAtabaseUrl.getDataToDatabaseItemData(url);
        //Bugun indirimde olan ürünler
        getDataToDatabaseDate.getDataToDatabaseItemData();
        //Execuotor
        //ayarlanabilir thread
        //Socket hatası olduğu için kapalı
       /* ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        for (int i = 1; i <= 50; i++) {
            executorService.submit(new ThreadClass(i,i+1));
        }
        executorService.shutdown();*/

        //24 satte bir ürünlerin çekilip kontrol edilmesi
  /*      Timer myTimer=new Timer();
        TimerTask gorev =new TimerTask() {

            @Override
            public void run() {
                Thread thread1 = new Thread(new ThreadClass(1,51));
                thread1.start();
            }
        };

        myTimer.schedule(gorev,86400000);
*/
        Thread thread1 = new Thread(new ThreadClass(1,51));
    //    thread1.start();
    }

}
