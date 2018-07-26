package srallegro.user;
import srallegro.Category;
import srallegro.auction.Auction;

import java.io.*;
import java.util.*;


public class Database {

    private static Database instance;

    private Database(){}

    public static synchronized Database getInstance() {
        if(instance == null) {
            instance = new Database();
        }
        return instance;
    }

    private static Map<String, srallegro.user.User> allUsersByNickname = new HashMap<>();
    private static Map<String, Category> allCategoriesByName = new HashMap<>();
    private static Map <String, Auction> allAuctionsByNumber = new HashMap<>();



    //regarding Users
    public static Map<String, srallegro.user.User> getAllUsersByNickname() {
        return allUsersByNickname;
    }

    public static void addUserToAllUsers (srallegro.user.User user) {
        allUsersByNickname.put(user.getNick(), user);
    }

    public static User getUserByNickname (String nickname) {
        try {
            User user = allUsersByNickname.get(nickname);
            return user;
        } catch (NullPointerException npe) {
            System.out.println("Nie ma takiego użytkownika");
        }
        return null;
    }




    public static void addUser(User user) throws IOException {
        Database database = Database.getInstance();
        String userNick = user.getNick();
        addUserToAllUsers(user);
        SaveUserOnDisk.writeCsvFile("databaseUser.csv", user);
    }
}
