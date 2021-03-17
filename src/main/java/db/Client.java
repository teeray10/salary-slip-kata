package db;

import java.util.ArrayList;
import java.util.List;

public class Client {
    public static void main(String[] args) {
        Mongo mongo = new Mongo("myDB", "myCollection");
        mongo.dropCollection();
//        mongo.insertDocument("poo","R11000");
//        mongo.sortByAndPrint("name", 1);
//        mongo.printUnsorted();
    }
}
