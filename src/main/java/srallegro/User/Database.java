package srallegro.User;
import com.opencsv.CSVWriter;
import srallegro.Auction.Auction;

import java.io.*;
import java.util.*;


public class Database {
    public static Map<String, User> usersByName = new HashMap<>();
    public static Set<Auction> allAuctions = new HashSet<>();


    public static void addUser(User user) throws IOException {
        String userNick = user.getNick();
        usersByName.put(userNick, user);
        SaveUserOnDisk.writeCsvFile("databaseUser.csv", user);

    }
}
