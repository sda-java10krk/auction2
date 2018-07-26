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


    private static Map<String, Category> allCategoriesByName = new HashMap<>();
    private static Map <Integer, Auction> allAuctionsByNumber = new HashMap<>();
    private static Map<String, srallegro.user.User> allUsersByNickname = new HashMap<>();


    //regarding Categories
    public static void addCategoryToAllCategories (Category category) {
        allCategoriesByName.put(category.getName(), category);
    }

    public static Category getCategoryByName (String catname) {
        try {
            Category category = allCategoriesByName.get(catname);
            return category;
        } catch (NullPointerException npe) {
            System.out.println("Nie ma takiej kategorii");
        }
        return null;
    }

    // regarding Auctions
    public static void addAuctionToAllAuctions (Auction auction) {
        allAuctionsByNumber.put(auction.getAuctionNumber(), auction);
    }

    public static Auction getAuctionByNumber (Integer number) {
        try {
            Auction auction = allAuctionsByNumber.get(number);
            return auction;
        } catch (NullPointerException npe) {
            System.out.println("Nie ma takiej aukcji");
        }
        return null;
    }

    public static Map<Integer, Auction> getAllAuctionsByNumber() {
        return allAuctionsByNumber;
    }

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
