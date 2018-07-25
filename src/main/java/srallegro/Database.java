package srallegro;
import com.opencsv.CSVWriter;

import java.io.*;
import java.util.*;


public class Database {
    public static Map<String, User> usersByName = new HashMap<>();
    public static Set<Auction> allAuctions = new HashSet<>();


    public static void addUser(User user) throws IOException {
        String  userNick = user.getNick();
        usersByName.put(userNick, user);
        //CSVWriter csvWriter = new CSVWriter(new FileWriter("databaseUser.csv"));


    }


}
