package app.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import app.Object.MenuItem;

// data source=DESKTOP-LDD5UPA\MSSQLSERVER13;initial catalog=master;trusted_connection=true

public class Mongo {
    private static String dburl = "mongodb+srv://tan:Tan04022004@devcafecluster.tdduqcb.mongodb.net/?retryWrites=true&w=majority&appName=DevCafeCluster";
    public static MongoDatabase database;

    // private static List<Document> menuItems = Arrays.asList(
    // new Document("name", "Cappuccino Assassino")
    // .append("description", "A Cappuccino served in an assassin way")
    // .append("price", 5.0),

    // new Document("name", "Espresso Inferno")
    // .append("description", "An espresso shot that burns like fire")
    // .append("price", 4.5),

    // new Document("name", "Latte Vendetta")
    // .append("description", "Sweet milk with a hint of revenge")
    // .append("price", 5.5),

    // new Document("name", "Mocha Ambush")
    // .append("description", "Chocolate surprise with a coffee punch")
    // .append("price", 6.0),

    // new Document("name", "Macchiato Stealth")
    // .append("description", "A stealthy blend of espresso and foam")
    // .append("price", 4.8),

    // new Document("name", "Americano Escape")
    // .append("description", "Classic black coffee brewed to disappear")
    // .append("price", 3.8),

    // new Document("name", "Flat White Phantom")
    // .append("description", "Smooth and mysterious white coffee")
    // .append("price", 5.2),

    // new Document("name", "Cold Brew Sniper")
    // .append("description", "Cold brew with precision flavor")
    // .append("price", 4.9),

    // new Document("name", "Affogato Mystery")
    // .append("description", "Ice cream drowned in hot espresso")
    // .append("price", 6.5),

    // new Document("name", "Irish Whisper")
    // .append("description", "A coffee with a secret Irish touch")
    // .append("price", 7.0));

    public static void getConnection() {
        try (MongoClient mongoClient = MongoClients.create(dburl)) {
            database = mongoClient.getDatabase("DevCafe");
            System.out.print("Connect mongoDB successfully!");
        }
    }
}
