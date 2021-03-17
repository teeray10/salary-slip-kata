import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MongoTest {
    MongoClient mongoClient;
    MongoDatabase db;
    MongoCollection<Document> mongoCollection;

    // UTILITIES
    private Document getFirstDoc() {
        List<Document> docs = mongoCollection.find().into(new ArrayList<>());
        return docs.get(0);
    }

    private void printDocs() {
        List<Document> docs = mongoCollection.find().into(new ArrayList<>());
        for (Document doc : docs)
            System.out.println(doc);
    }

    @Before
    public void setup() {
        mongoClient = new MongoClient();
        db = mongoClient.getDatabase("testDb");
        mongoCollection = db.getCollection("testCollection");
        System.out.println("--------------- \n---------------" + db);
    }

    @Test
    public void createMongoClient() throws Exception {
        assertNotNull(mongoClient);
    }

    @Test
    public void getDB() throws Exception {
        assertNotNull(db);
    }

    @Test
    public void getCollection() throws Exception {
        assertNotNull(mongoCollection);
    }

    @Test
    public void insertDocument() throws Exception {
        Document doc = new Document("_id", 1).append("name", "taylor");
        mongoCollection.insertOne(doc);
        assertEquals(1, mongoCollection.countDocuments());
    }

    @Test
    public void getDocument() throws Exception {
        Document doc = new Document("_id", 1).append("name", "taylor");

        mongoCollection.insertOne(doc);
        Document docRetrieved = mongoCollection.find().first();
        assertEquals("taylor",docRetrieved.get("name"));
    }

    @Test
    public void updateDocument() throws Exception {
        Document docBeforeUpdate = new Document("_id", "1").append("name", "taylor");
        Document docAfterUpdate = new Document("name", "ben");

        mongoCollection.insertOne(docBeforeUpdate);
        UpdateResult updateResult = mongoCollection.updateOne(Filters.eq("_id", "1"), new Document("$set",docAfterUpdate));
        Assert.assertEquals(1, updateResult.getModifiedCount());
    }

    @Test
    public void deleteDocument() throws Exception {
        Document doc = new Document("_id", 1).append("name", "taylor");

        mongoCollection.insertOne(doc);
        DeleteResult deleteResult = mongoCollection.deleteOne(Document.parse("{_id:1}"));
        assertEquals(1,deleteResult.getDeletedCount());
    }

    @After
    public void teardown() {
        mongoCollection.drop();
        db.drop();
        mongoClient.close();
    }
}
