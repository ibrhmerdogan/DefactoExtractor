package database.databaseOperations;

import com.mongodb.*;

/**
 * Created by ibrhm on 9.05.2017.
 */
public class DeleteDB {
    //Bütün erileri siler
    public void deleteDB(){
        MongoClient mongoClient = null;
        try {
            mongoClient = new MongoClient( "localhost" , 27017 );
            DB db = mongoClient.getDB( "DefactoDB" );
            System.out.println("Connect to database successfully");
            DBCollection collection = db.getCollection("Records");
            WriteResult result = collection.remove(new BasicDBObject());
            System.out.println(result.toString());


        } catch (Exception e) {
            System.out.println("An Error Occured! Error is:"+e);
        }finally {
            mongoClient.close();
        }

    }
}
