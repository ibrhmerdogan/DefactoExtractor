package tread;

import readUrl.ReadUrl;

/**
 * Created by ibrhm on 12.05.2017.
 */
public class ThreadClass implements Runnable {
    //CAlıştırılacak olan ReadUrl clasının girdileri
    int pageNumber;
    int endPageNumber;
    ReadUrl readUrl = new ReadUrl();

    public ThreadClass(int pageNumber, int endPageNumber) {
        this.pageNumber = pageNumber;
        this.endPageNumber = endPageNumber;
    }

    @Override
    public void run() {
        try {
            //her thread da calıştığın da calısacak alan
            readUrl.getDataToUrl(pageNumber, endPageNumber);

        } catch (Exception e)
        {
            e.printStackTrace();
        }


    }
}