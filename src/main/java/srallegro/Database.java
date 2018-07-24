package srallegro;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class Database {
    Map<String, User> usersByName = new HashMap<>();
    public static Set<Auction> allAuctions = new HashSet<>();
    public static Map<String, Set<Auction>> auctionsByCategories = new HashMap<>();


    protected void addUser(User user) {
        String  userNick = user.getNick();
        usersByName.put(userNick, user);
    }
}
