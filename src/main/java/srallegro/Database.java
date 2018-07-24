package srallegro;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class Database {
    Map<String, User> usersByName = new HashMap<>();
    public static Set<Auction> allAuctions = new HashSet<>();


    protected void addUser(User user) throws FileNotFoundException {
        String  userNick = user.getNick();
        usersByName.put(userNick, user);
        PrintWriter saveDatabase = new PrintWriter("DatabaseUsers.txt");
    }

}
