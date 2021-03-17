package db;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class Mongo {
    MongoClient mongoClient;
    MongoDatabase db;
    MongoCollection<Document> mongoCollection;
    MongoCollection<Document> counterCollection;

    public Mongo(String db, String collection) {
        mongoClient = new MongoClient();
        this.db = mongoClient.getDatabase(db);
        mongoCollection = this.db.getCollection(collection);
        counterCollection = this.db.getCollection("counter");
    }

    public void sortByAndPrint(String column, int direction) {
        MongoCursor itr = mongoCollection.find().sort(new BasicDBObject(column, direction)).iterator();

        while (itr.hasNext())
            System.out.println(itr.next());
    }

    public void printUnsorted(){
        List<Document> list = mongoCollection.find().into(new ArrayList<>());

        for (Document doc : list)
            System.out.println(doc.toJson());
    }

    public void insertDocument(String name, String salary) {
        updateCounterCollection();
        Document document = new Document("_id", Long.toString(counterCollection.countDocuments()))
                .append("name", name)
                .append("salary", salary);
        mongoCollection.insertOne(document);
    }

    private void updateCounterCollection() {
        counterCollection.insertOne(new Document("counter", ""));
    }

    public void dropCollection() {
        mongoCollection.drop();
        counterCollection.drop();
    }
}
